package pers.kinson.im.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.kinson.im.common.HttpResult;
import pers.kinson.im.common.constants.I18nConstants;
import pers.kinson.im.common.exception.BusinessRequestException;
import pers.kinson.im.common.logger.LoggerUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用异常切面，方法签名规则如下：
 * 方法应该以 handle 开头，接受异常作为参数。
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler({BusinessRequestException.class})
    public HttpResult handleBusinessException(HttpServletRequest request, BusinessRequestException e) {
        return HttpResult.fail(e.getErrorCode());
    }

    @ExceptionHandler(BindException.class)
    public HttpResult handleBindException(BindException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',参数校验失败'{}'", requestURI, e.getMessage());
        return HttpResult.fail(I18nConstants.COMMON_ILLEGAL_PARAMS);
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public HttpResult handleException(HttpServletRequest request, Exception e) {
        LoggerUtil.error("error:" + request.getRequestURI(), e);
        return HttpResult.fail(I18nConstants.COMMON_INTERNAL_ERROR);
    }

}
