package pers.kinson.im.chat.data.dao;

import pers.kinson.im.chat.data.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int getMaxId();

    User findById(Long id);

    User findByName(String nickName);

    void addUser(User user);

    void delUser(Integer id);

    void updateUser(User user);
}
