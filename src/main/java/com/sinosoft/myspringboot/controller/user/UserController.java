package com.sinosoft.myspringboot.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinosoft.myspringboot.enums.ResultStatusCodeEmun;
import com.sinosoft.myspringboot.pojo.user.User;
import com.sinosoft.myspringboot.service.user.UserService;
import com.sinosoft.utils.ResponseMsg;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/hello",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ResponseMsg hello() {
        try {
        	System.out.println("hello...");
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), null);
        } catch (Exception e) {
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }
	}

	@RequestMapping(value="/save",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResponseMsg saveUser(@RequestBody User user) {
        try {
        	System.out.println(user);
    		userService.saveUser(user);
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), null);
        } catch (Exception e) {
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }
	}
	
	@RequestMapping(value="/queryPageUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	public ResponseMsg queryPageUser(@RequestBody User user) {
        try {
    		String result = userService.queryPageUser(user);
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), result);
        } catch (Exception e) {
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }
	}
	
	
}
