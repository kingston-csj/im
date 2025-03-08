package pers.kinson.im.web.data.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.kinson.business.entity.User;

@Repository
public interface UserDao extends BaseMapper<User> {

}
