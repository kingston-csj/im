package pers.kinson.im.chat.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.business.cache.UserProfile;
import pers.kinson.business.entity.User;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {

    int getMaxId();

    User findByName(String nickName);

    List<UserProfile> selectIdAndNameList();

}
