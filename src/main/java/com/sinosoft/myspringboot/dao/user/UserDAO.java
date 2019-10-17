package com.sinosoft.myspringboot.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.myspringboot.pojo.user.User;

@Mapper
public interface UserDAO {
	void saveUser(User user);
	
	List<User> queryAllUser();
}
