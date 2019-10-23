package com.sinosoft.utils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

/** 
 * @ClassName: SFTPClientUtils 
 * @Description: SFTP工具類
 * @author: liuch
 * @date: 2018年11月6日 下午5:51:37  
 */
@Component
@ConfigurationProperties(prefix = "sftp")
public class SFTPClientUtils {
	private static int downloadSleep;
	private static int downloadRetry;
	private static int uploadSleep;
	private static int uploadRettry;
	private static Logger logger = LoggerFactory.getLogger(SFTPClientUtils.class);
        /**
        * 文件上传
     * 将文件对象上传到sftp作为文件。文件完整路径=basePath+directory
     * 目录不存在则会上传文件夹
     * @param basePath 服务器的基础路径
     * @param directory 上传到该目录    
     * @param sftpFileName sftp端文件名    
     * @param file    文件对象    
     */
     public synchronized static boolean upload(String basePath,String directory, InputStream fileIs,String fileName){    
     	boolean result = false;
     	Integer i = 0;
          while(!result){
          	ChannelSftp sftp = SFTPConnectionFactory.makeConnection();
          	try {     
                    sftp.cd(basePath);
                    sftp.cd(directory);    
               } catch (SftpException e) {    
               	logger.info("sftp文件上传，目录不存在开始创建");
                    String [] dirs=directory.split("/");
                    String tempPath=basePath;
                    for(String dir:dirs){
                         if(null== dir || "".equals(dir)) continue;
                         tempPath+="/"+dir;
                         try{    
                              sftp.cd(tempPath);
                         }catch(SftpException ex){
                              try {
     						sftp.mkdir(tempPath);
     						sftp.cd(tempPath);
     					} catch (SftpException e1) {
     						logger.error("sftp文件上传，目录创建失败，错误信息:"+e1.getMessage()+ex.getMessage());
     					}
                         }
                    }
               }    
               try {
     			sftp.put(fileIs , fileName);
     			if(i>0){
					logger.info("sftp重试文件上传成功，ftp路径:"+basePath+directory+",文件名称:"+fileName);
				}else{
					logger.info("sftp文件上传成功，ftp路径为"+basePath+directory+",文件名称:"+fileName);
				}
     			result = true;
     		} catch (Exception e) {
     			i++;
     			logger.error("sftp文件上传失败，重试中。。。第"+i+"次，错误信息"+e.getMessage());
				if(i>uploadRettry){
					logger.error("sftp文件上传失败，超过重试次数结束重试，错误信息"+e.getMessage());
					return result;
				}
     			try {
					TimeUnit.MILLISECONDS.sleep(uploadSleep);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
     		}
              
          }
     	
          return result;
     }    
     /**
     * 下载文件。
     * @param directory 下载目录    
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     */     
     public synchronized static InputStream download(String directory, String downloadFile){
//     	Integer i = 0;
//     	while(!result){
     		ChannelSftp sftp = SFTPConnectionFactory.makeConnection();
     		if (directory != null && !"".equals(directory)) {
     			try {
     				sftp.cd(directory);
     			} catch (SftpException e) {
     				logger.error("sftp文件下载，目录不存在，错误信息"+e.getMessage());
     			}
     		}
     		String src = directory + "/" + downloadFile;
     		try {
				return sftp.get(src);
			} catch (SftpException e) {
				e.printStackTrace();
				return null;
			}
//     		FileOutputStream fileOutputStream = null;
//     		try {
//     			fileOutputStream = new FileOutputStream(file);
//     		} catch (FileNotFoundException e1) {
//     			logger.error("sftp文件下载失败，本地目录不存在"+e1.getMessage());
//     		}
//     		try {
//     			sftp.get(downloadFile, fileOutputStream);
//			        if(i>0){
//			            	logger.info("sftp文件重试下载成功,sftp地址:"+directory+",本地文件地址:"+saveFile);	
//			            }else{
//			            	logger.info("sftp文件下载成功,sftp地址:"+directory+",本地文件地址:"+saveFile);
//			            }
//     			result = true;
//     		} catch (SftpException e1) {
//     			i++;
//				logger.error("sftp文件下载失败，重试中。。。第"+i+"次，错误信息"+e1.getMessage());
//				if(i>downloadRetry){
//					logger.error("ftp文件下载失败，超过重试次数结束重试，错误信息"+e1.getMessage());
//					return result;
//				}
//				try {
//					TimeUnit.MILLISECONDS.sleep(downloadSleep);
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//     		}finally {
//     			try {
//     				fileOutputStream.close();
//     			} catch (IOException e) {
//     				
//     				e.printStackTrace();
//     			}
//     		}
//     	}
//          return result;
     }
     /**
     * 删除文件
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     */    
    public synchronized static boolean delete(String directory, String deleteFile){    
    	boolean result = false;
    	ChannelSftp sftp = SFTPConnectionFactory.makeConnection();
    	try {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	} catch (SftpException e) {
		e.printStackTrace();
	}    
         result = true;
         return result;
    }
	public static int getDownloadSleep() {
		return downloadSleep;
	}
	public static void setDownloadSleep(int downloadSleep) {
		SFTPClientUtils.downloadSleep = downloadSleep;
	}
	public static int getDownloadRetry() {
		return downloadRetry;
	}
	public static void setDownloadRetry(int downloadRetry) {
		SFTPClientUtils.downloadRetry = downloadRetry;
	}
	public static int getUploadSleep() {
		return uploadSleep;
	}
	public static void setUploadSleep(int uploadSleep) {
		SFTPClientUtils.uploadSleep = uploadSleep;
	}
	public static int getUploadRettry() {
		return uploadRettry;
	}
	public static void setUploadRettry(int uploadRettry) {
		SFTPClientUtils.uploadRettry = uploadRettry;
	}
}