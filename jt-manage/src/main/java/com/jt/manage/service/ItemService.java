package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;

public interface ItemService {

	public List<Item> findItemAll();

	public EasyUIResult findItemByPage(Integer page, Integer rows);

}
