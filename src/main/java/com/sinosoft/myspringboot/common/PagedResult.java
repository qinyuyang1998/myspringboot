package com.sinosoft.myspringboot.common;

import java.util.List;
/**
 * 
 * @ClassName:  PagedResult   
 * @Description: 分页实体类
 * @author: WuDi
 * @date:   2017年3月23日13:10:30
 *   
 * @param <T>
 */
import java.util.Map;
public class PagedResult<T> extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private List<T> dataList;//数据
	
	private List<Map<String, Object>> dataListMap;//数据2
	
	private long pageNo;//当前页
	
	private long pageSize;//条数
	
	private long total;//总条数
	
	private long pages;//总页面数目

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public List<Map<String, Object>> getDataListMap() {
		return dataListMap;
	}

	public void setDataListMap(List<Map<String, Object>> dataListMap) {
		this.dataListMap = dataListMap;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}
}
