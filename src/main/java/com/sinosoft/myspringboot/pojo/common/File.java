package com.sinosoft.myspringboot.pojo.common;

import java.util.Date;

/**
 * 
 * <p>
 * Title: File
 * </p>
 * 
 * <p>
 * Description: 附件
 * </p>
 * 
 * @author 郭雪亮
 * 
 * @date 2019年7月15日
 * 
 */
public class File {
    private String fdObjectid;
    private String name;
    private String fileext;
    private String saveName;
    private String filePath;
    private String remark;
    private Date updateDate;
    private String isDel;
    private String flag;
    private String fileType;
    private String foreignId;

    public String getFdObjectid() {
        return fdObjectid;
    }

    public void setFdObjectid(String fdObjectid) {
        this.fdObjectid = fdObjectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileext() {
        return fileext;
    }

    public void setFileext(String fileext) {
        this.fileext = fileext;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }
}
