package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.service.DubboOrderService;
import com.jt.dubbo.service.DubboSearchService;

//ʵ��ȫ�ļ���
@Controller
public class SearchController {
	@Autowired
	private DubboSearchService searchService;
	@RequestMapping("/search")
	public String findItemByKey(@RequestParam("q")String keyWord,Model model){
		try {
			String key = 
					new String(keyWord.getBytes("ISO-8859-1"),"UTF-8");
			//ʵ����Ʒ��ѯ ����ȫ�ļ���ϵͳ
			List<Item>  itemLists = searchService.findItemByKey(key);
			model.addAttribute("query", key);
			model.addAttribute("itemList", itemLists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��ת��ȫ�ļ���ҳ��
		return "search";
	}

}
