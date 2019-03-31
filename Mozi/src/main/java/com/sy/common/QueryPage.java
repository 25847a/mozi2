package com.sy.common;

/**
 * 分页
 * 
 * @author DDM
 *
 */
public class QueryPage {
	private Integer currPage; // 查询页
	private Integer size; // 每页条数
	private Integer start; // 开始条数

	public Integer getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStart() {
		if (null != start && null != currPage) {
			start = size * currPage;
			return start;
		} else {
			return null;
		}
	}
}
