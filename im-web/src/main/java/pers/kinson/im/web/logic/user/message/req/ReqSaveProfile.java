package pers.kinson.im.web.logic.user.message.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqSaveProfile {

    private Long Id;

    private String name;

    private String remark;

    private String avatar;
}
