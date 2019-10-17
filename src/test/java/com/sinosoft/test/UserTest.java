package com.sinosoft.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sinosoft.MyApplication;
import com.sinosoft.myspringboot.dao.user.UserDAO;
import com.sinosoft.myspringboot.pojo.user.User;
import com.sinosoft.myspringboot.service.user.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyApplication.class)
@SpringBootTest
@WebAppConfiguration
public class UserTest {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserService userService;
	/**
     * 开始分割线
     */
    @Before
    public void beforeTest() {
    	System.out.println("beforeTest=================================");
    }
    /**
     * 结束分割线
     */
    @After
    public void afterTest() {
    	System.out.println("afterTest=================================");
    }
    @Test
    public void testSaveUser(){
    	User user=new User();
    	user.setUserName("张三2");
    	user.setUserAge(15);
    	userService.saveUser(user);
    }
    
    @Test
    public void testQueryAllUser(){
    	User user=new User();
    	user.setLimit(5);
    	user.setPageNumber(15);
    	String result=userService.queryPageUser(user);
    	System.out.println(result);
    }
}
