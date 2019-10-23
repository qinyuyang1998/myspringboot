package com.sinosoft.myspringboot.dao.upload;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.myspringboot.pojo.upload.AnnexFile;

/**
 * 
 * <p>
 * Title: AnnexFileDao
 * <p>
 * 
 * <p>
 * Description: 附件dao
 * <p>
 * 
 * @author 秦玉杨
 * 
 * @date 2019年10月23日
 * 
 */
@Mapper
public interface AnnexFileDao {
	
    Integer insert(AnnexFile annexFile);
    
    AnnexFile query(AnnexFile annexFile);
}