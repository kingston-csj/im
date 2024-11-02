package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.kinson.im.chat.data.model.User;
import org.springframework.stereotype.Repository;
import pers.kinson.im.chat.logic.user.bo.UserProfile;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao extends BaseMapper<User> {

    int getMaxId();

    User findByName(String nickName);

    List<UserProfile> selectIdAndNameList();

}
