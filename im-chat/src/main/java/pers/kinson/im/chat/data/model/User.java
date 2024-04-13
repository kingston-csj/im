package pers.kinson.im.chat.data.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import pers.kinson.im.chat.base.Constants;

@TableName(value = "user")
public class User {

	@TableId()
	private long userId;
	/** 性别{@link Constants#SEX_OF_BOY} */
	private byte sex;
	/** 用户名字 */
	private String userName;
	/** 密码 */
	private String password;
	/** 个性签名　*/
	private String signature;

	public long getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

}
