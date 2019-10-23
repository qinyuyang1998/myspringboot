package com.sinosoft.myspringboot.controller.upload;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sinosoft.myspringboot.constant.Constants;
import com.sinosoft.myspringboot.service.upload.FilesUploadService;
import com.sinosoft.myspringboot.enums.ResultStatusCodeEmun;
import com.sinosoft.myspringboot.pojo.common.File;
import com.sinosoft.myspringboot.pojo.common.UpdateFile;
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
public class FileUploadController {

    @Autowired
    FilesUploadService fileUploadService;

    public static final Logger logger = Logger.getLogger(FileUploadController.class);

    @ResponseBody
    @RequestMapping(value = "/attachment1/upload", method = RequestMethod.POST)
    public ResponseMsg upload(@RequestParam Map<String, Object> record, HttpServletRequest req,
            HttpServletResponse rsp) {
        try {
            List<Map<String, Object>> files = fileUploadService.upload(req, rsp, record);
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
     * 根据主键查询记录
     * 
     * @param fdObjectid
     * @return
     */
    @RequestMapping(value = "/attachment/download", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @RequestParam("fi") String fileId) {
        try {
            logger.info("附件下载开始");
            fileUploadService.download(request, response, fileId);
            logger.info("附件->" + fileId + "->下载成功");
        } catch (Exception e) {
            logger.error("附件->" + fileId + "->下载失败", e);
        }
    }

    /**
     * 根据主键删除一条记录
     * 
     * @param fdObjectid
     * @return
     */
    @RequestMapping(value = "/attachment/deleteOne", method = RequestMethod.POST)
    public ResponseMsg deleteByPrimaryKey(@RequestBody File file) {
        try {
            fileUploadService.deleteByPrimaryKey(file);
            logger.info(Constants.DEL_SUCCESS);
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), null);
        } catch (Exception e) {
            logger.error(Constants.DEL_FAIL, e);
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }

    }

    /**
     * 根据主键修改一条记录
     * 
     * @param fdObjectid
     * @return
     */
    @RequestMapping(value = "/attachment/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseMsg updateByPrimaryKey(@RequestBody UpdateFile updateFile) {
        try {
            fileUploadService.update(updateFile);
            logger.info(Constants.UPDATE_SUCCESS);
            return new ResponseMsg(ResultStatusCodeEmun.OK.getCode(), ResultStatusCodeEmun.OK.getMsg(), null);
        } catch (Exception e) {
            logger.error(Constants.UPDATE_FAIL, e);
            return new ResponseMsg(ResultStatusCodeEmun.SYSTEM_ERR.getCode(), ResultStatusCodeEmun.SYSTEM_ERR.getMsg(),
                    null);
        }

    }
}
