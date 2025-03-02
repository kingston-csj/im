package pers.kinson.im.account.conifg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("${mybatis-plus.mapperScanPath}")
public class MybatisPlusConfig {

}