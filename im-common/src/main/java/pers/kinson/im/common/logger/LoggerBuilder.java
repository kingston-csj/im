package pers.kinson.im.common.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.ScanException;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LoggerBuilder {

    private static final Map<String, Logger> container = new HashMap<>();
    private static final String LOG_PATH = "./logs";

    public static Logger getLogger(String name) {
        Logger logger = container.get(name);
        if (logger != null) {
            return logger;
        }
        synchronized (LoggerBuilder.class) {
            logger = container.get(name);
            if (logger != null) {
                return logger;
            }
            logger = build(name);
            container.put(name, logger);
        }
        return logger;
    }

    @SuppressWarnings("unchecked")
    private static Logger build(String name) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger logger = context.getLogger(name);
        RollingFileAppender appender = new RollingFileAppender();
        appender.setContext(context);
        appender.setName(name);
        appender.setAppend(true);
        appender.setPrudent(false);
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        String fp = null;
        String fileName = name.toLowerCase();
        try {
            fp = OptionHelper.substVars(LoggerBuilder.LOG_PATH + "/" + fileName + "/" + fileName + ".log.%d{yyyy-MM-dd}", context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        policy.setFileNamePattern(fp);
        policy.setMaxHistory(15);
        policy.setParent(appender);
        policy.setContext(context);
        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%m%n");
        encoder.start();

        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();

        //不渗透到根日志
        logger.setAdditive(false);
        logger.setLevel(Level.INFO);
        logger.addAppender(appender);
        return logger;
    }

}