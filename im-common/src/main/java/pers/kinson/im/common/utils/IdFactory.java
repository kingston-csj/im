package pers.kinson.im.common.utils;

import java.util.UUID;

public class IdFactory {

    public static String nextUUId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
