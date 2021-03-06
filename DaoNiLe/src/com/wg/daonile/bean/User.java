package com.wg.daonile.bean;

/**
 * 用户实体
 * 
 * @author ke.wei.quan
 * @date 2015年4月1日
 *
 */
public class User {
	// 用户名
	private String userName;
	// 用户电话号码
	private String phoneNum;
	// 用户头像
	private String userAvatar;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

}
