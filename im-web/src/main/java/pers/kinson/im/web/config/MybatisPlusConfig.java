package pers.kinson.im.web.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("${mybatis-plus.mapperScanPath}")
public class MybatisPlusConfig {

}