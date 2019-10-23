package com.sinosoft.myspringboot.pojo.common;

/**
 * 
 * <p>
 * Title: UpdateFile
 * </p>
 * 
 * <p>
 * Description: 修改附件
 * </p>
 * 
 * @author 郭雪亮
 * 
 * @date 2019年7月15日
 * 
 */
public class UpdateFile {
    private String eventId;
    private String files;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
