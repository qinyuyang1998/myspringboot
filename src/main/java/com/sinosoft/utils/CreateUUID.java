package com.sinosoft.utils;

import java.util.UUID;

/**
 * Create GUID
 * 
 * @author guoxueliang
 *
 */
public class CreateUUID {
	public static final String GenerateGUID() {
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString().replace("-", "");
		return uid;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(GenerateGUID());
	}
}
