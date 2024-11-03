package pers.kinson.im.common.logger;


import jforgame.commons.DateUtil;
import jforgame.commons.JsonUtil;

import java.util.Date;

public class LoggerUtil {

    public static void error(String message, Throwable e) {
        LoggerSystem.EXCEPTION.getLogger().error(message, e);
    }

    public static void info(LoggerFunction logger, Object... args) {
        if (args.length == 0) {
            return;
        }
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException(String.format("logger %s, args %s", logger, JsonUtil.object2String(args)));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("time|").append(System.currentTimeMillis()).append("|");
        sb.append("date|").append(DateUtil.format(new Date())).append("|");
        for (int i = 0, n = args.length; i < n; i += 2) {
            String key = (String) args[i];
            Object value = args[i + 1];
            sb.append(key).append("|")
                    .append(value).append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        logger.getLogger().info(sb.toString());
    }

}
