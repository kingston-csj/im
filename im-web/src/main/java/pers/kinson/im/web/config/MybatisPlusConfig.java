package pers.kinson.im.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("${mybatis-plus.mapperScanPath}") // 指定 mapper 接口所在包
public class MybatisPlusConfig {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

}