package test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import test.dao.BaseTestCase;

import com.kingston.dao.UserDao;
import com.kingston.model.User;
public class UserDaoTest extends BaseTestCase {

	@Autowired
	private UserDao userDao;

	
	@Test
	public void testSelect() throws Exception {

		User user = userDao.findById(1);
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