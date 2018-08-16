package com.jt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jt.common.vo.SysResult;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;

@Service	//标识身份
public class CartServiceImpl implements CartService{
	@Autowired	//注入接口
	private CartMapper cartMapper;
	
	//我的购物车
	public List<Cart> myCart(Integer userId){
		//设置where条件,新的的方式QBC.新的查询方式.把查询条件封装到QBC对象中
		EntityWrapper<Cart> ew = new EntityWrapper<Cart>();
		//我的?	where user_id=?
		//第一个参数就是拼接到where条件,参数的名词是数据库的字段
		//{0}占位符,序号从0开始编
		ew.where("user_id={0}", userId);
		return cartMapper.selectList(ew);
	}
	
	//保存产品到购物车
	public SysResult save(Cart cart) {
		try {
			//查询某个用户的某个商品:count(*)0-1/查询这个对象
			Cart param = new Cart();
			param.setUserId(cart.getUserId());
			param.setItemId(cart.getItemId());
			
			//获取某个用户的某个商品的信息
			Cart oldCart = cartMapper.selectOne(param);
			
			//判断条件
			if(oldCart == null) {//表示此用户的此商品不存在
				//新增
				cartMapper.insert(cart);
				return SysResult.ok();
			
			}else {
				//修改数量
				cart.setNum(oldCart.getNum() + cart.getNum());
				EntityWrapper<Cart> ew  = new EntityWrapper<Cart>();
				ew.where("user_id={0}",cart.getUserId());
				ew.and("item_id={0}",cart.getItemId());
				
				cartMapper.update(cart, ew);
				return SysResult.build(202, "此用户的商品已经存在");
			}
		} catch (Exception e) {	
			return SysResult.build(201, "保存商品到购物车失败");
		}
	}
	
	//修改商品数量(数量就变成连接提供值)
	public void updateNum(Cart cart) {
		EntityWrapper<Cart> ew  = new EntityWrapper<Cart>();
		ew.where("user_id={0}",cart.getUserId());
		ew.and("item_id={0}",cart.getItemId());
		
		cartMapper.update(cart, ew);	
	}
	
	//修改商品数量(数量就变成连接提供值)
	public void deleteNum(Integer userId,Integer itemId) {
		EntityWrapper<Cart> ew  = new EntityWrapper<Cart>();
		ew.where("user_id={0}",userId);
		ew.and("item_id={0}",itemId);
		
		cartMapper.delete(ew);	
	}
}
