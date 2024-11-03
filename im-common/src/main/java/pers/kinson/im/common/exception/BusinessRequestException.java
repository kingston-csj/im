package pers.kinson.im.common.exception;

import lombok.Getter;
import lombok.Setter;
import pers.kinson.im.common.constants.I18nConstants;

/**
 * 业务请求执行异常
 */
@Getter
@Setter
public class BusinessRequestException extends RuntimeException {

    /**
     * 异常状态吗{@link I18nConstants}
     */
    private int errorCode;

    public BusinessRequestException(int errorCode) {
        this.errorCode = errorCode;
    }

}