package com.johj.common.model;

public class PageBean {

	// 分页开始位置
	private Integer offset;
	// 每页多少行
	private Integer limit;

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
