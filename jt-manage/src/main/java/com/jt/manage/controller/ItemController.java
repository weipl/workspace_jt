package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	//要求：find/itemAll
	/*@RequestMapping("find/ItemAll")
	@ResponseBody
	public List<Item> findItem(){
		return itemService.findItemAll();
		
	}*/
	@RequestMapping("query")
	@ResponseBody
	public EasyUIResult findItemPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
		
	}
}
