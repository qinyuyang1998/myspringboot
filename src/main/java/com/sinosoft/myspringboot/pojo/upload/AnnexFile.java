package com.sinosoft.myspringboot.pojo.upload;

import java.util.Date;

/**
 * 
 * <p>
 * Title: AnnexFile
 * <p>
 * 
 * <p>
 * Description: 附件实体类
 * <p>
 * 
 * @author 秦玉杨
 * 
 * @date 2019年10月23日
 * 
 */
public class AnnexFile {

	// 主键
	public static final String FDOBJECTID = "fdObjectid";
	// 业务类型
	public static final String BUSINESSTYPE = "businessType";
	// 数据ID
	public static final String DATAID = "dataId";
	// 旧名称
	public static final String OLDNAME = "oldName";
	// 新名称
	public static final String NEWNAME = "newName";
	// 路径
	public static final String PATH = "path";
	// 创建人
	public static final String CREATEPERSON = "createPerson";
	// 创建时间
	// 这里就是String，目的是返String类型的数据
	public static final String CREATEDATE = "createDate";
	// 删除标识
	public static final String ISDEL = "isDel";
	
	private String fdObjectid;
	private String businessType;
	private String dataId;
	private String oldName;
	private String newName;
	private String path;
	private String createPerson;
	private Date createDate;
	private String isDel;
	public String getFdObjectid() {
		return fdObjectid;
	}
	public void setFdObjectid(String fdObjectid) {
		this.fdObjectid = fdObjectid;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	@Override
	public String toString() {
		return "AnnexFile [fdObjectid=" + fdObjectid + ", businessType="
				+ businessType + ", dataId=" + dataId + ", oldName=" + oldName
				+ ", newName=" + newName + ", path=" + path + ", createPerson="
				+ createPerson + ", createDate=" + createDate + ", isDel="
				+ isDel + "]";
	}
	
}
