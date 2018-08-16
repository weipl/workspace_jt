package com.jt.web.pojo;

import com.jt.common.po.BasePojo;
public class ItemDesc extends BasePojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long itemId;
	private String itemDesc;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	
}
