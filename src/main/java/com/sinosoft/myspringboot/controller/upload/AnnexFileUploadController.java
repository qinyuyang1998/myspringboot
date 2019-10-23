package com.sinosoft.myspringboot.controller.upload;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sinosoft.myspringboot.service.upload.AnnexFileUploadService;
import com.sinosoft.myspringboot.enums.ResultStatusCodeEmun;
import com.sinosoft.utils.ResponseMsg;

/**
 * 附件上传下载Controller
 * 
 * @author wesley
 * @since 2018年5月2日
 * @version 1.0
 *
 */
@RestController
public class AnnexFileUploadController {

    @Autowired
    AnnexFileUploadService annexFileUploadService;

    public static final Logger logger = Logger.getLogger(AnnexFileUploadController.class);
    
    /**
     * @param request
     * @param response
     * @param map
     * @return
     * 上传附件
     */
    @RequestMapping(value = "/annexFile/upload", method = RequestMethod.POST)
    public ResponseMsg upload(@RequestParam Map<String, Object> record, HttpServletRequest req,
            HttpServletResponse rsp) {
        try {
            List<Map<String, Object>> files = annexFileUploadService.upload(req, rsp, record);
            String json = JSON.toJSONString(files);
            System.out.println(json);
            logger.info("上传成功的附件 -> " + json);
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), files);
        } catch (Exception e) {
            logger.error("附件上传失败！", e);
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }
    }
    /**
     * @param request
     * @param response
     * @param fdObjectid
     * @throws Exception
     * 根据主键下载附件
     */
    @RequestMapping(value = "/annexFile/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, String fdObjectid) {
        try {
            logger.info("附件下载开始");
            annexFileUploadService.download(request, response, fdObjectid);
            logger.info("附件->" + fdObjectid + "->下载成功");
        } catch (Exception e) {
            logger.error("附件->" + fdObjectid + "->下载失败", e);
        }
    }
}
