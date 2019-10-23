package com.sinosoft.myspringboot.service.upload.Impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.sinosoft.myspringboot.constant.Constants;
import com.sinosoft.myspringboot.dao.main.upload.FilesUploadDao;
import com.sinosoft.myspringboot.pojo.upload.CommFile;
import com.sinosoft.myspringboot.service.upload.FilesUploadService;
import com.sinosoft.myspringboot.pojo.common.File;
import com.sinosoft.myspringboot.pojo.common.UpdateFile;
import com.sinosoft.utils.CreateUUID;
import com.sinosoft.utils.DateUtil;
import com.sinosoft.utils.SFTPClientUtils;

/**
 * 
 * @className FilesUploadServiceImpl
 * @Description
 * @author guoxueliang
 * @Date 2018年4月22日下午4:49:07
 */
@Service("FilesUploadService")
public class FilesUploadServiceImpl implements FilesUploadService {

    @Resource
    private FilesUploadDao filesUploadDao;
    @Value("${upload-file-url}")
    private String upload_file_url; // 文件上传基址目录

    @Value("${yjbs.ip}")
    private String url;

    // 默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 52428800;
    // 默认上传的地址
    public static String defaultBaseDir = "";

    public static final Logger logger = Logger.getLogger(FilesUploadServiceImpl.class);

    /**
     * 附件上传方法
     * 
     * @param request
     *            http请求，其中可能带有附件
     * @param response
     *            http响应
     * @param map
     *            map中可以使用的参数{fdObjectid:"业务主键",flag:"业务标识,
     *            用于标识哪块业务使用的附件",filepath:"文件相对基址目录的路径，用于创建子目录"}
     */

    @Transactional
    public List<Map<String, Object>> upload(HttpServletRequest request, HttpServletResponse response,
            Map<String, Object> map) {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List<Map<String, Object>> result = new ArrayList<>();
//        if (resolver.isMultipart(request)) {

            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            /**
             * 文件上传相对基址路径，用于将不同功能模块的附件上传的不同的路径
             */
            // String relativePath = (String) map.get(CommFile.FILEPATH);
            // relativePath = relativePath == null ? "" : relativePath;
            String relativePath = upload_file_url + "/" + DateUtil.getFileFolderDate();
            // String fdObjectid = (String) map.get(CommFile.FDOBJECTID);
            String flag = (String) map.get(CommFile.FLAG);
            // 取得request中的所有文件名
            List<MultipartFile> files = multiRequest.getFiles("file");
            logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~@@@@@@@@@@@@@@@@@@@附件个数" + files.size());
            try {
                for (int i = 0; i < files.size(); i++) {
                    MultipartFile file = files.get(i);
//                    if (!file.isEmpty()) {
                        // 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
                        // 文件真实名字
                        String filename = file.getOriginalFilename();
                        logger.debug("原始文件名->" + filename);
                        // 文件保存名字中的唯一标识符
                        String fileId = CreateUUID.GenerateGUID();
                        // 保存文件信息
                        String name = filename.substring(0, filename.lastIndexOf('.'));
                        // 文件扩展名
                        String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
                        // 文件保存名称
                        String saveName = name + "-" + fileId + "." + fileExt;
                        // String fullName = Paths.get(upload_file_url,
                        // relativePath, saveName).toString();
                        boolean fullName = SFTPClientUtils.upload(upload_file_url, DateUtil.getFileFolderDate(),
                                file.getInputStream(), saveName);
                        logger.info("文件保存的全路径 -> " + fullName);
                        /*
                         * desc = getAbsoluteFile(new
                         * Boolean(fullName).toString()); //
                         * MultipartFile自带的解析方法 file.transferTo(desc);
                         */
                        // 保存文件信息
                        CommFile commFile = new CommFile();
                        // 主表主键 改成自增
                        commFile.setFdObjectid(fileId);
                        commFile.setName(name);
                        commFile.setFileext(fileExt);
                        commFile.setSavename(saveName);
                        commFile.setFilepath(relativePath);
//                        commFile.setForeignId(map.get("foreignId").toString());
                        commFile.setIsDel("0");
                        commFile.setFlag(flag);
                        String fileType = "";
                        if (fileExt.matches("jpg|jpeg|gif|png|bmp")) {
                            fileType = "1";
                        } else {
                            fileType = "2";
                        }
                        commFile.setFileType(fileType);
                        commFile.setUpdatedate(new Date());
                        //*int num = filesUploadDao.insert(commFile);
                      //*System.out.println("!!!!!!!!!!!!!num=" + num);
                        Map<String, Object> fileInfo = new HashMap<>();
                        fileInfo.put(CommFile.FDOBJECTID, fileId);
                        fileInfo.put(Constants.URL, url);
                        fileInfo.put(Constants.ORIGIN_NAME, filename);
                        fileInfo.put(Constants.SAVE_NAME, saveName);
                        fileInfo.put(Constants.FILE_PATH, relativePath);
                        fileInfo.put(Constants.FILE_FLAG, flag);
                        fileInfo.put(Constants.FILE_TYPE, fileType);
                        result.add(fileInfo);
                    }
//                }
            } catch (Exception e) {
                e.printStackTrace();

            }
//        }
        return result;
    }

    public static String getCode(int passLength) {
        StringBuffer buffer = null;
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        buffer = new StringBuffer("0123456789");
        int range = buffer.length();
        for (int i = 0; i < passLength; ++i) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    public static final String extractFilename(MultipartFile file, String baseDir) throws UnsupportedEncodingException {
        String filename = file.getOriginalFilename();
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
            filename = filename.substring(slashIndex + 1);
        }
        filename = baseDir + "/" + filename;
        return filename;
    }

    public static final String extractUploadDir(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /*
     * public void download(HttpServletRequest request, HttpServletResponse
     * response, String fileId) throws Exception {
     * response.setCharacterEncoding("UTF-8"); logger.info("附件id->" + fileId);
     * request.setCharacterEncoding("UTF-8"); Map<String, Object> arg = new
     * HashMap<>(); arg.put(CommFile.FDOBJECTID, fileId); Map<String, Object>
     * fileRecord = filesUploadDao.selectForDownload(arg); String fileSaveName =
     * (String) fileRecord.get("saveName"); String filePath = (String)
     * fileRecord.get("filePath"); filePath = filePath == null ? "" : filePath;
     * String name = (String) fileRecord.get(Constants.NAME); String fileExt =
     * (String) fileRecord.get(Constants.EXT); String originName =
     * (StringUtils.isEmpty(name) ? "" : name) + "." +
     * (StringUtils.isEmpty(fileExt) ? "" : fileExt); logger.info("附件名原始名称->" +
     * originName); logger.info("附件相对路径->" + filePath); logger.info("附件保存名称->" +
     * fileSaveName); String mimeType = getMIMEType(originName);// 猜测文件的MIMEType
     * // 设置文件ContentType类型，这样设置，会自动判断下载文件类型 boolean isImageOrPdf = false; if
     * (!StringUtils.isEmpty(mimeType) &&
     * mimeType.matches("(image/.*)|(application/pdf)")) { isImageOrPdf = true;
     * } else { mimeType = "multipart/form-data"; }
     * response.setContentType(mimeType); BufferedInputStream bis = null;
     * BufferedOutputStream bos = null; String downLoadPath =
     * Paths.get(upload_file_url, filePath, fileSaveName).toString();
     * logger.info("附件下载名称->" + originName); logger.info("附件下载路径->" +
     * downLoadPath); long fileLength = new File(downLoadPath).length(); //
     * 设置文件下载头 String downloadHeader = "attachment"; if (isImageOrPdf) {
     * downloadHeader = "inline"; } response.setHeader("Content-disposition",
     * downloadHeader + "; filename=" + new
     * String(originName.getBytes("gb2312"), "ISO8859-1"));
     * 
     * // 设置文件长度 response.setHeader("Content-Length",
     * String.valueOf(fileLength)); bis = new BufferedInputStream(new
     * FileInputStream(downLoadPath)); bos = new
     * BufferedOutputStream(response.getOutputStream()); byte[] buff = new
     * byte[1024 * 1024]; int bytesRead; while (-1 != (bytesRead =
     * bis.read(buff, 0, buff.length))) { bos.flush(); bos.write(buff, 0,
     * bytesRead); } bis.close(); bos.close();
     * 
     * }
     */
    public void download(HttpServletRequest request, HttpServletResponse response, String fileId) throws Exception {
        response.setCharacterEncoding("UTF-8");
        logger.info("附件id->" + fileId);
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> arg = new HashMap<>();
        arg.put(CommFile.FDOBJECTID, fileId);
        Map<String, Object> fileRecord = filesUploadDao.selectForDownload(arg);
        String fileSaveName = (String) fileRecord.get("saveName");
        String filePath = (String) fileRecord.get("filePath");
        filePath = filePath == null ? "" : filePath;
        String name = (String) fileRecord.get(Constants.NAME);
        String fileExt = (String) fileRecord.get(Constants.EXT);
        String originName = (StringUtils.isEmpty(name) ? "" : name) + "."
                + (StringUtils.isEmpty(fileExt) ? "" : fileExt);
        logger.info("附件名原始名称->" + originName);
        logger.info("附件相对路径->" + filePath);
        logger.info("附件保存名称->" + fileSaveName);
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        BufferedInputStream bis = null;
        InputStream fileIs = SFTPClientUtils.download(filePath, fileSaveName);
        logger.info("附件获取->" + fileIs);
        if (null != fileIs) {
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(originName.getBytes("GB2312"), "ISO-8859-1"));
            byte[] buffer = new byte[1024];
            bis = new BufferedInputStream(fileIs);
            try {
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                logger.info("附件下载名称->" + originName);
                System.out.println("下载成功");
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (null != bis) {
                    try {
                        bis.close();
                    } catch (Exception e2) {
                        // TODO: handle exception
                    }
                }
            }

        }

    }

    @Override
    @Transactional
    public int del(Map<String, Object> map) {
        return filesUploadDao.delByEventIdAndFlag(map);
    }

    @Override
    @Transactional
    public String getFileById(Map<String, Object> map) {
        List<Map<String, Object>> list = filesUploadDao.getFileById(map);
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    public void insertFromSelect(Map<String, Object> arg) {

        List<Map<String, Object>> files = filesUploadDao.selectByEventIdAndFlag(arg);
        for (int i = 0; i < files.size(); i++) {
            Map<String, Object> map = files.get(i);
            map.put(CommFile.FDOBJECTID, CreateUUID.GenerateGUID());
            map.put(CommFile.ISDEL, "0");
            map.put(CommFile.FOREIGNID, arg.get("newReserveplanId"));
            map.put("updatedate", new Date());
            filesUploadDao.insertForSelect(map);
        }
    }

    @Override
    @Transactional
    public void deleteByPrimaryKey(File file) {
        filesUploadDao.deleteByPrimaryKey(file);
    }

    @Override
    public List<Map<String, Object>> selectByForeignId(Map<String, Object> arg) {
        List<Map<String, Object>> list = filesUploadDao.selectByForeignId(arg);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> file = list.get(i);
            String name = (String) file.get(Constants.NAME);
            String fileExt = (String) file.get(Constants.EXT);
            String originName = (StringUtils.isEmpty(name) ? "" : name) + "."
                    + (StringUtils.isEmpty(fileExt) ? "" : fileExt);
            file.put("originName", originName);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.sinosoft.upload.service.FilesUploadService#delByEventIdAndFlag(java.
     * util.Map)
     */
    @Override
    public int delByEventIdAndFlag(Map<String, Object> map) {
        return filesUploadDao.delByEventIdAndFlag(map);
    }

    /**
     * 根据主键修改
     */
    @Transactional
    @Override
    public void update(UpdateFile updateFile) {
        filesUploadDao.update(updateFile);
    }
}
