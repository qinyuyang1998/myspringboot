package com.sinosoft.myspringboot.enums;
/**
 * 
 * Copyright: Copyright (c) 2018 Sinosoft Co.,Ltd.
 * 
 * @Description: 自定义返回提示枚举类
 *
 * @author: WuDi
 * @date: 2018年12月24日 上午11:49:43
 */
public enum ResultStatusCodeEmun {
	
	OK(200,"success"),
	SYSTEM_ERR(500,"内部错误"),
	SAVE_ERR(20104,"添加失败"),
	UPDATE_ERR(20105,"修改失败"),
	REMOVE_ERR(20106,"删除失败"),
	EXIST_NAME(20107,"该名称已存在，请更换！"),
	EXIST_CODE(20108,"该编码已存在，请更换！"),
	EXIST_VALUE(20109,"该值已存在，请更换！"),
	INVALID_PARAMETER(20110,"参数无效"),
	RANGE_EXIST_VALUE(21001,"该路段范围内已存在决策结果，请先删除后再决策！"),
	FILE_NOEXIST(21003,"要导入的文件不存在！"),
	REMOVE_TEMPLATE_FAIL(3008,"该模板在使用中，不能删除！"),
	UPDATE_TEMPLATE_FAIL(3009,"该模板在使用中，不能修改！"),
	REMOVE_RATE_FAIL(3010,"该响应频率在使用中，不能删除！"),
	UPDATE_RATE_FAIL(3011,"该响应频率在使用中，不能修改！"),
	// 状态值为9999为动态定义
	;
	
	private int code;
	private String msg;

	private ResultStatusCodeEmun(int code, String msg) {  
        this.code = code;  
        this.msg = msg;  
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
