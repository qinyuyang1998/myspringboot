package com.sinosoft.myspringboot.service.upload;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinosoft.myspringboot.pojo.upload.AnnexFile;

/**
 * 
 * <p>
 * Title: AnnexFileUploadService
 * <p>
 * 
 * <p>
 * Description: 附件Service
 * <p>
 * 
 * @author 秦玉杨
 * 
 * @date 2019年10月23日
 * 
 */
public interface AnnexFileUploadService {
	
    /**
     * @param request
     * @param response
     * @param map
     * @return
     * 上传附件
     */
    List<Map<String, Object>> upload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map);
    
    /**
     * @param request
     * @param response
     * @param fdObjectid
     * @throws Exception
     * 下载附件
     */
    void download(HttpServletRequest request, HttpServletResponse response, String fdObjectid) throws Exception;
    
    /**
     * @param annexFile
     * @return
     * 查询附件
     */
    AnnexFile query(AnnexFile annexFile);
}
