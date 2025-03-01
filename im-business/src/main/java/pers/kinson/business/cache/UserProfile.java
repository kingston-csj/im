package pers.kinson.business.cache;

import lombok.Data;

@Data
public class UserProfile {

    private Long id;

    private String name;
    /**
     * 在线状态
     */
    private int onlineStatus;
}
