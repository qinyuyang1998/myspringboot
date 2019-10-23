package com.sinosoft.myspringboot.pojo.upload;

import java.util.Date;

/**
 * 
 * @className CommFile 附件实体类
 * @Description
 * @author guoxueliang
 * @Date 2018年4月22日下午2:49:07
 */
public class CommFile {

	public static final String FDOBJECTID = "fdObjectid";

	public static final String NAME = "name";

	public static final String FILEEXT = "fileext";

	public static final String SAVENAME = "savename";

	public static final String FILEPATH = "filepath";

	public static final String FOREIGNID = "foreignId";

	public static final String REMARK = "remark";

	public static final String UPDATEDATE = "updatedate";

	public static final String ISDEL = "isDel";

	public static final String FLAG = "flag";

	public static final String FILETYPE = "fileType";

	private String fdObjectid;

	private String name;

	private String fileext;

	private String savename;

	private String filepath;

	private String foreignId;

	private String remark;

	private Date updatedate;

	private String isDel;

	private String flag;

	private String fileType;

	public String getFdObjectid() {
		return fdObjectid;
	}

	public void setFdObjectid(String fdObjectid) {
		this.fdObjectid = fdObjectid == null ? null : fdObjectid.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext == null ? null : fileext.trim();
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename == null ? null : savename.trim();
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath == null ? null : filepath.trim();
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId == null ? null : foreignId.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel == null ? null : isDel.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType == null ? null : fileType.trim();
	}
}