package pers.kinson.im.testdao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import pers.kinson.im.chat.data.dao.UserDao;
import pers.kinson.im.chat.data.model.User;

public class TestUserDao extends BaseTestCase {

	@Autowired
	private UserDao userDao;

	@Test
	public void testSelect() throws Exception {

		User user = userDao.findById(1L);
		System.out.println(user.getUserName());
	}

	@Rollback(true)
	@Test
	public void testAdd() {
		User user = new User();
		user.setUserId(2);
		user.setUserName("Tom");
		user.setPassword("sdf");
		userDao.addUser(user);

	}

	@Test
	@Rollback(false)
	public void testDel() {
		userDao.delUser(29);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setUserId(1);
		user.setUserName("Mary");
		userDao.updateUser(user);
	}

}