package com.sinosoft.myspringboot.service.upload;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sinosoft.myspringboot.pojo.common.File;
import com.sinosoft.myspringboot.pojo.common.UpdateFile;

/**
 * 
 * @className FilesUploadService
 * @Description
 * @author guoxueliang
 * @Date 2018年4月22日下午4:48:51
 */
public interface FilesUploadService {

    // mainId 业务表主键
    List<Map<String, Object>> upload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map);

    // fileName 文件名
    // fileSaveName 文件保存名
    void download(HttpServletRequest request, HttpServletResponse response, String fileId) throws Exception;

    // 删除附件
    int del(Map<String, Object> map);

    // 根据Id获取附件信息
    String getFileById(Map<String, Object> map);

    // 插入记录(预案修订使用)
    void insertFromSelect(Map<String, Object> arg);

    // 根据主键删除
    void deleteByPrimaryKey(File file);

    /**
     * 根据foreign_id查询附件
     * 
     * @param arg
     * @return
     */
    List<Map<String, Object>> selectByForeignId(Map<String, Object> arg);

    // 根据eventId和flag属性删除附件
    int delByEventIdAndFlag(Map<String, Object> map);

    /**
     * 根据主键修改附件
     * 
     * @param responseEvent
     * @return
     */
    void update(UpdateFile updateFile);
}
