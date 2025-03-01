package pers.kinson.im.web.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableConfigurationProperties(value = {CacheProperties.class})
@Slf4j
public class RedisConfig {

    private static StringRedisSerializer stringRedisSerializer;

    private static Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer;

    static {
        //定义string类型序列化对象
        stringRedisSerializer = new StringRedisSerializer();
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        //LocalDateTime序列化异常
        om.registerModule(new JavaTimeModule());
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om, Object.class);
    }

    @Autowired
    private Environment env;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(@Autowired RedisProperties redisProperties) {
        RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        redisConf.setHostName(redisProperties.getHost());
        redisConf.setPort(redisProperties.getPort());
        return new LettuceConnectionFactory(redisConf);
    }

    @Bean //Redis序列化配置
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //配置连接工程
        template.setConnectionFactory(factory);
        //redis key序列化方式
        template.setKeySerializer(stringRedisSerializer);
        //redis value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //hashmap  key序列化
        template.setHashKeySerializer(stringRedisSerializer);
        //hashmap value序列化
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    //Cache配置
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory, @Autowired CacheProperties cacheProperties) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //缓存key
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                //缓存组件value
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .computePrefixWith(cacheName -> cacheName + ":");

        CacheProperties.Redis redisProps = cacheProperties.getRedis();
        if (redisProps.getTimeToLive() != null) {
            //过期时间
            config = config.entryTtl(redisProps.getTimeToLive());
        }
        if (!redisProps.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }

        return RedisCacheManager.builder(factory).cacheDefaults(config).build();
    }


}