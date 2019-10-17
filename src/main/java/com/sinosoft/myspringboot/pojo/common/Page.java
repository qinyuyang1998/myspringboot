package com.sinosoft.myspringboot.pojo.common;

/**
 * 
 * <p>
 * Title: Page
 * </p>
 * 
 * <p>
 * Description: 分页
 * </p>
 * 
 * @author 郭雪亮
 * 
 * @date 2019年7月5日
 * 
 */
public class Page {
	
    private Integer pageNumber;
    private Integer limit;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
