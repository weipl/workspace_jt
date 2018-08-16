package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.common.vo.SysResult;
import com.jt.pojo.Cart;
import com.jt.service.CartService;

@RestController	//	表示身份
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	
	//我的购物车
	@RequestMapping("/query/{userId}")
	//@PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：URL 中的 {xxx} 占位符可以通过@PathVariable(“xxx“) 绑定到操作方法的入参中。
	public SysResult myCart(@PathVariable Integer userId) {
		try {
			List<Cart> myCart = cartService.myCart(userId);
			return SysResult.ok(myCart);
			
		} catch (Exception e) {
			return  SysResult.build(201, "查询购物车商品失败");
		}	
	}
	//保存商品到购物车，post提交，使用HttpClient它来模拟发起post请求
	@RequestMapping("/save")
	public SysResult save(Cart cart) {
		return cartService.save(cart);
	}
	@RequestMapping("/update/num/{userId}/{itemId}/{num}")
	public SysResult updateNum(Cart cart) {
		try {
			cartService.updateNum(cart);
			return SysResult.ok(cart);
		} catch (Exception e) {
			return SysResult.build(201, "商品修改失败");
		}
	}
	@RequestMapping("/delete/{userId}/{itemId}")
	public SysResult delete(@PathVariable Integer userId,@PathVariable Integer itemId) {
		try {
			cartService.deleteNum(userId, itemId);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.build(201, "商品删除失败");
		}
	}
}
