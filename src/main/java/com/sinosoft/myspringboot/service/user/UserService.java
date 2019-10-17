package com.sinosoft.myspringboot.service.user;

import com.sinosoft.myspringboot.pojo.user.User;

public interface UserService {
	void saveUser(User user);
	String queryPageUser(User user);
}
