package com.sinosoft.myspringboot.dao.main.upload;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.myspringboot.pojo.upload.CommFile;
import com.sinosoft.myspringboot.pojo.common.File;
import com.sinosoft.myspringboot.pojo.common.UpdateFile;

/**
 * 
 * @className FilesUploadDao
 * @Description
 * @author guoxueliang
 * @Date 2018年4月22日下午2:49:07
 */
@Mapper
public interface FilesUploadDao {
    Integer insert(CommFile record);

    void insertSelective(CommFile record);

    // 根据eventId和flag属性删除附件
    int delByEventIdAndFlag(Map<String, Object> map);

    // 根据Id获取附件信息
    List<Map<String, Object>> getFileById(Map<String, Object> map);

    /**
     * 根据event_id,flag查询附件
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByEventIdAndFlag(Map<String, Object> map);

    /**
     * 查询附件后插入
     * 
     * @param map
     */
    void insertForSelect(Map<String, Object> map);

    void deleteByPrimaryKey(File file);

    /**
     * 根据foreign_id查询附件
     * 
     * @param arg
     * @return
     */
    List<Map<String, Object>> selectByForeignId(Map<String, Object> arg);

    /**
     * 附件下载时使用，根据附件id查询附件信息
     * 
     * @param arg
     * @return
     */
    Map<String, Object> selectForDownload(Map<String, Object> arg);

    /**
     * 根据foreign_id List查询附件
     * 
     * @param arg
     *            {foreignIdList:foreign_id组成的list}
     * @return
     */
    List<Map<String, Object>> selectByForeignIds(Map<String, Object> arg);

    /**
     * 根据主键修改
     * 
     * @param list
     */
    void update(UpdateFile updateFile);
}