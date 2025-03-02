package pers.kinson.im.account.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.business.entity.User;

@Repository
public interface UserDao extends BaseMapper<User> {

    User findByName(String nickName);

}
