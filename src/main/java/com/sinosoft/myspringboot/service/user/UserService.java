package com.sinosoft.myspringboot.service.user;

import com.sinosoft.myspringboot.pojo.user.User;

public interface UserService {
	/**
	 * 保存User
	 * @param user
	 */
	void saveUser(User user);
	/**
	 * 分页查询
	 * @param user
	 * @return
	 */
	String queryPageUser(User user);
}
