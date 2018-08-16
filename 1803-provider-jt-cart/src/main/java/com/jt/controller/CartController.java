package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.vo.SysResult;
import com.jt.pojo.Cart;
import com.jt.service.CartService;

@RestController	//标识身份
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	//我的购物车 /cart/query/{userId} RESTFul请求
	@RequestMapping("/query/{userId}")
	public SysResult myCart(@PathVariable Integer userId){
		try{
			List<Cart> cartList = cartService.myCart(userId);
			return SysResult.ok(cartList);
		}catch(Exception e){
			return SysResult.build(201, "查询我的购物车出错!");
		}
	}
	
	//保存商品到购物车，post提交，使用HttpClient它来模拟发起post请求
	@RequestMapping("/save")		///cart/save
	public SysResult save(Cart cart){
		return cartService.save(cart);
	}
	
	//修改商品数量 /cart/update/num/{userId}/{itemId}/{num}
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(Cart cart){
		try{
			cartService.updateNum(cart);
			return SysResult.ok();
		}catch(Exception e){
			return SysResult.build(201, "修改商品数量出错!");
		}
	}
	
	//删除某个用户的某个商品 /cart/delete/{userId}/{itemId}
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult delete(@PathVariable Integer userId,@PathVariable Integer itemId){
		try{
			cartService.delete(userId, itemId);
			return SysResult.ok();
		}catch(Exception e){
			return SysResult.build(201,"删除某个商品出错!");
		}
	}
}





