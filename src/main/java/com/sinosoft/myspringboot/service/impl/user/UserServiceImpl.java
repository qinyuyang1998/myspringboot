package com.sinosoft.myspringboot.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.sinosoft.myspringboot.common.PagedResult;
import com.sinosoft.myspringboot.dao.user.UserDAO;
import com.sinosoft.myspringboot.pojo.user.User;
import com.sinosoft.myspringboot.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDAO;

	@Override
	public void saveUser(User user) {
		this.userDAO.saveUser(user);
	}

	@Override
	public String queryPageUser(User user) {
		Gson gson = new Gson();
		PagedResult<User> result = new PagedResult<User>();
		int pageSize = user.getLimit(); /** 页面数据条数 */
		int pageNumber = user.getPageNumber() / pageSize + 1; /** 页码 */
		Page<User> page = PageHelper.startPage(pageNumber, pageSize, "user_id asc"); /** 分页 */
		//Page<User> page = PageHelper.startPage(pageNumber, pageSize, "user_age desc"); /** 分页 */
		List<User> userList = userDAO.queryAllUser();
        result.setDataList(userList);
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        return gson.toJson(result);
	}
}
