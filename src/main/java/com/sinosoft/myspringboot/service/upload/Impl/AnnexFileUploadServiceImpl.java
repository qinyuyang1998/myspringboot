package com.sinosoft.myspringboot.service.upload.Impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sinosoft.myspringboot.dao.upload.AnnexFileDao;
import com.sinosoft.myspringboot.pojo.upload.AnnexFile;
import com.sinosoft.myspringboot.service.upload.AnnexFileUploadService;
import com.sinosoft.utils.CreateUUID;
import com.sinosoft.utils.DateUtil;
import com.sinosoft.utils.SFTPClientUtils;

/**
 * 
 * <p>
 * Title: AnnexFileUploadServiceImpl
 * <p>
 * 
 * <p>
 * Description:
 * <p>
 * 
 * @author 秦玉杨
 * 
 * @date 2019年10月23日
 * 
 */
@Service
public class AnnexFileUploadServiceImpl implements AnnexFileUploadService {

	@Autowired
	AnnexFileDao annexFileDao;

	@Value("${upload-file-url}")
	private String upload_file_url; // 文件上传基址目录

	public static final Logger logger = Logger
			.getLogger(AnnexFileUploadServiceImpl.class);

	/**
	 * @param request
	 * @param response
	 * @param map
	 * @return 上传附件
	 */
	@Override
	public List<Map<String, Object>> upload(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map) {
		List<Map<String, Object>> result = new ArrayList<>();

		// 转换成多部分request
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

		// 文件上传相对基址路径，用于将不同功能模块的附件上传的不同的路径
		String relativePath = upload_file_url + "/"
				+ DateUtil.getFileFolderDate();
		// 取得request中的所有文件名
		List<MultipartFile> files = multiRequest.getFiles("file");
		logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~@@@@@@@@@@@@@@@@@@@附件个数"
				+ files.size());
		try {
			for (int i = 0; i < files.size(); i++) {
				MultipartFile file = files.get(i);
				// 文件真实名字
				String filename = file.getOriginalFilename();
				logger.debug("原始文件名->" + filename);
				// 文件保存名字中的唯一标识符
				String fdObjectid = CreateUUID.GenerateGUID();
				// 保存文件信息
				String name = filename.substring(0, filename.lastIndexOf('.'));
				// 文件扩展名
				String fileExt = filename
						.substring(filename.lastIndexOf(".") + 1);
				// 文件保存名称
				String saveName = name + "-" + fdObjectid + "." + fileExt;
				// 上传文件
				boolean fullName = SFTPClientUtils.upload(upload_file_url,
						DateUtil.getFileFolderDate(), file.getInputStream(),
						saveName);
				logger.info("文件保存的全路径 -> " + fullName);

				// 保存文件信息
				AnnexFile annexFile = new AnnexFile();
				Date date = new Date();
				annexFile.setFdObjectid(fdObjectid);
				annexFile.setBusinessType("维修上传的照片");
				annexFile.setDataId("dsasd");
				annexFile.setOldName(filename);
				annexFile.setNewName(saveName);
				annexFile.setPath(relativePath);
				annexFile.setCreatePerson("dsa");
				annexFile.setCreateDate(date);
				annexFile.setIsDel("0");
				// 把文件信息保存到数据库
				Integer insert = annexFileDao.insert(annexFile);
				logger.info("保存数据库 -> " + insert);
				// 返回文件信息
				Map<String, Object> fileInfo = new HashMap<>();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				fileInfo.put(AnnexFile.FDOBJECTID, fdObjectid);
				fileInfo.put(AnnexFile.BUSINESSTYPE, "维修上传的照片");
				fileInfo.put(AnnexFile.DATAID, "dsasd");
				fileInfo.put(AnnexFile.OLDNAME, filename);
				fileInfo.put(AnnexFile.NEWNAME, saveName);
				fileInfo.put(AnnexFile.PATH, relativePath);
				fileInfo.put(AnnexFile.CREATEPERSON, "dsa");
				fileInfo.put(AnnexFile.CREATEDATE, sdf.format(date));
				fileInfo.put(AnnexFile.ISDEL, "0");
				result.add(fileInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param request
	 * @param response
	 * @param fdObjectid
	 * @throws Exception
	 *             下载附件
	 */
	@Override
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fdObjectid) throws Exception {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		logger.info("附件id->" + fdObjectid);
		AnnexFile annexFile = new AnnexFile();
		annexFile.setFdObjectid(fdObjectid);
		AnnexFile newAnnexFile = annexFileDao.query(annexFile);
		
		String newName = newAnnexFile.getNewName();
		String oldName = newAnnexFile.getOldName();
		String path = newAnnexFile.getPath();

		path = path == null ? "" : path;

		logger.info("附件名原始名称->" + oldName);
		logger.info("附件相对路径->" + path);
		logger.info("附件保存名称->" + newName);

		// 设置文件ContentType类型，这样设置，会自动判断下载文件类型
		BufferedInputStream bis = null;
		InputStream fileIs = SFTPClientUtils.download(path, newName);
		logger.info("附件获取->" + fileIs);
		if (null != fileIs) {
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String(oldName.getBytes("GB2312"), "ISO-8859-1"));
			byte[] buffer = new byte[1024];
			bis = new BufferedInputStream(fileIs);
			try {
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
				logger.info("附件下载名称->" + oldName);
				System.out.println("下载成功");
			} catch (Exception e) {
			} finally {
				if (null != bis) {
					try {
						bis.close();
					} catch (Exception e2) {
					}
				}
			}

		}

	}
    /**
     * @param annexFile
     * @return
     * 查询附件
     */
	@Override
	public AnnexFile query(AnnexFile annexFile) {
		return annexFileDao.query(annexFile);
	}

}
