package pers.kinson.im.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum LoggerSystem {

    EXCEPTION,


    ;

    public Logger getLogger() {
        return LoggerFactory.getLogger(this.name());
    }


}