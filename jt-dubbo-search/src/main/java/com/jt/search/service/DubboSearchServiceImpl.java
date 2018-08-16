package com.jt.search.service;

import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.dubbo.pojo.Item;
import com.jt.dubbo.service.DubboSearchService;

public class DubboSearchServiceImpl implements DubboSearchService {
	
	@Autowired
	private HttpSolrServer httpSolrServer;
	
	//实现全文检索
	@Override
	public List<Item> findItemByKey(String key) {
		List<Item> itemList = null;
		try {
			SolrQuery query = new SolrQuery(key);
			query.setStart(0);	//暂时写死  0条记录开始
			query.setRows(20);	//每页显示多少条记录
			QueryResponse response = httpSolrServer.query(query);
			
			//将document数据 为item对象赋值
			itemList = response.getBeans(Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
}
