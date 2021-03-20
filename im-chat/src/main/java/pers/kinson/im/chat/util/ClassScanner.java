package pers.kinson.im.chat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

/**
 * 类扫描器 使用此工具类扫描，目标不需要注入到spring容器里
 *
 * @see BeanPostProcessor
 *
 */
public class ClassScanner {

    private static Logger logger = LoggerFactory.getLogger(ClassScanner.class);

    /**
     * 默认过滤器（无实现）
     */
    private final static Predicate<Class<?>> EMPTY_FILTER = clazz -> true;

    /**
     * 扫描目录下的所有class文件
     *
     * @param scanPackage 搜索的包根路径
     * @return
     */
    public static Set<Class<?>> getClasses(String scanPackage) {
        return getClasses(scanPackage, EMPTY_FILTER);
    }

    /**
     * 返回所有的子类（不包括抽象类）
     *
     * @param scanPackage 搜索的包根路径
     * @param parent
     * @return
     */
    public static Set<Class<?>> listAllSubclasses(String scanPackage, Class<?> parent) {
        return getClasses(scanPackage, (clazz) -> {
            return parent.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers());
        });
    }

    /**
     * 返回所有带制定注解的class列表
     *
     * @param scanPackage 搜索的包根路径
     * @param annotation
     * @return
     */
    public static <A extends Annotation> Set<Class<?>> listClassesWithAnnotation(String scanPackage,
                                                                                 Class<A> annotation) {
        return getClasses(scanPackage, (clazz) -> {
            return clazz.getAnnotation(annotation) != null;
        });
    }

    /**
     * 扫描目录下的所有class文件
     *
     * @param pack   包路径
     * @param filter 自定义类过滤器
     * @return
     */
    public static Set<Class<?>> getClasses(String pack, Predicate<Class<?>> filter) {
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metaFactory = new SimpleMetadataReaderFactory(patternResolver);

        String path = ClassUtils.convertClassNameToResourcePath(pack);
        String location = ResourceUtils.CLASSPATH_URL_PREFIX + path + "/**/*.class";
        Resource[] resources;

        Set<Class<?>> result = new HashSet<>();
        try {
            resources = patternResolver.getResources(location);
            for (Resource resource : resources) {
                MetadataReader metaReader = metaFactory.getMetadataReader(resource);
                if (resource.isReadable()) {
                    String clazzName = metaReader.getClassMetadata().getClassName();
                    if (clazzName.contains("$")) {
                        // 忽略内部类
                        continue;
                    }
//					Class<?> clazz = Class.forName(clazzName);
                    Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(clazzName);
                    if (filter.test(clazz)) {
                        result.add(clazz);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        return result;
    }


}