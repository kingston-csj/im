package pers.kinson.im.infrastructure.security;

/**
 * 访问范围常量类
 **/
public interface Scope {

    /**
     * 来自浏览器的访问
     */
    String BROWSER = "BROWSER";
    /**
     * 来自微服务内部的访问
     */
    String SERVICE = "SERVICE";

}
