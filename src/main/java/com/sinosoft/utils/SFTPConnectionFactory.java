package com.sinosoft.utils;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/** 
 * @ClassName: SFTPConnectionFactory 
 * @Description: SFTP工厂类，用于获取SFTP的连接
 * @author: liuch
 * @date: 2018年11月6日 下午5:51:28  
 */
@Component
@ConfigurationProperties(prefix = "sftp")
public class SFTPConnectionFactory {
	private static Logger logger = LoggerFactory.getLogger(SFTPClientUtils.class);
	/** SFTP 登录用户名*/
	private static String username; 
	/** SFTP 登录密码*/
	private static String password; 
	/** 私钥 */
	private static String privateKey; 
	/** SFTP 服务器地址IP地址*/
	private static String ip; 
	/** SFTP 端口*/ 
	private static int port;
	
	/** 备机 */
	/** SFTP 登录用户名*/
	private static String bakUsername; 
	/** SFTP 登录密码*/
	private static String bakPassword; 
	/** 私钥 */
	private static String bakPrivateKey; 
	/** SFTP 服务器地址IP地址*/
	private static String bakIp; 
	/** SFTP 端口*/ 
	private static int bakPort;
	
//	private static final SFTPConnectionFactory factory = new SFTPConnectionFactory();
	private static ChannelSftp client;
	private static Session session;
	private SFTPConnectionFactory(){ }
	
//	public static SFTPConnectionFactory getInstance(){
//		return factory;
//	}
	public synchronized static ChannelSftp makeConnection(){
		
		if(client==null||session==null||!client.isConnected()||!session.isConnected()){
			try { 
				JSch jsch = new JSch(); 
				if (privateKey != null) { 
					jsch.addIdentity(privateKey);// 设置私钥 
				} 
				session = jsch.getSession(username, ip, port); 
				if (password != null) { 
					session.setPassword(password);
				} 
				Properties config = new Properties(); 
				config.put("StrictHostKeyChecking", "no"); 
				session.setConfig(config); 
				session.connect(); 
				Channel channel = session.openChannel("sftp"); 
				channel.connect(); 
				client = (ChannelSftp) channel; 
				logger.info("sftp服务器连接成功");
			} catch (JSchException e) { 
				logger.error("主机sftp登录失败，检测登录ip，端口号，用户名密码是否正确，错误信息为"+e.getMessage());
				makeBakConnection();
			}
		}
		 
		return client;
	}
	
	
	/** 
	* @Description: 连接备机 
	* @return ChannelSftp
	* @author liuch
	* @date 2018年11月15日上午10:10:40
	*/ 
	public synchronized static ChannelSftp makeBakConnection(){
		
		if(client==null||session==null||!client.isConnected()||!session.isConnected()){
			try { 
				JSch jsch = new JSch(); 
				if (bakPrivateKey != null) { 
					jsch.addIdentity(bakPrivateKey);// 设置私钥 
				} 
				session = jsch.getSession(bakUsername, bakIp, bakPort); 
				if (password != null) { 
					session.setPassword(bakPassword);
				} 
				Properties config = new Properties(); 
				config.put("StrictHostKeyChecking", "no"); 
				session.setConfig(config); 
				session.connect(); 
				Channel channel = session.openChannel("sftp"); 
				channel.connect(); 
				client = (ChannelSftp) channel; 
				logger.info("sftp备机服务器连接成功");
			} catch (JSchException e) { 
				logger.error("备机sftp登录失败，检测登录ip，端口号，用户名密码是否正确，错误信息为"+e.getMessage());
			}
		}
		 
		return client;
	}
	
	/**
	* 关闭连接 server 
	*/ 
	public void logout(){ 
		if (client != null) { 
			if (client.isConnected()) { 
				client.disconnect(); 
			} 
		} 
		if (session != null) { 
			if (session.isConnected()) { 
			session.disconnect(); 
			} 
		} 
	}
 
 
	public static String getUsername() {
		return username;
	}
 
 
	public static void setUsername(String username) {
		SFTPConnectionFactory.username = username;
	}
 
 
	public static String getPassword() {
		return password;
	}
 
 
	public static void setPassword(String password) {
		SFTPConnectionFactory.password = password;
	}
 
 
	public static String getPrivateKey() {
		return privateKey;
	}
 
 
	public static void setPrivateKey(String privateKey) {
		SFTPConnectionFactory.privateKey = privateKey;
	}
 
 
	public static String getIp() {
		return ip;
	}
 
 
	public static void setIp(String ip) {
		SFTPConnectionFactory.ip = ip;
	}
 
 
	public static int getPort() {
		return port;
	}
 
 
	public static void setPort(int port) {
		SFTPConnectionFactory.port = port;
	}

	public static String getBakUsername() {
		return bakUsername;
	}

	public static void setBakUsername(String bakUsername) {
		SFTPConnectionFactory.bakUsername = bakUsername;
	}

	public static String getBakPassword() {
		return bakPassword;
	}

	public static void setBakPassword(String bakPassword) {
		SFTPConnectionFactory.bakPassword = bakPassword;
	}

	public static String getBakPrivateKey() {
		return bakPrivateKey;
	}

	public static void setBakPrivateKey(String bakPrivateKey) {
		SFTPConnectionFactory.bakPrivateKey = bakPrivateKey;
	}

	public static String getBakIp() {
		return bakIp;
	}

	public static void setBakIp(String bakIp) {
		SFTPConnectionFactory.bakIp = bakIp;
	}

	public static int getBakPort() {
		return bakPort;
	}

	public static void setBakPort(int bakPort) {
		SFTPConnectionFactory.bakPort = bakPort;
	}
	
}