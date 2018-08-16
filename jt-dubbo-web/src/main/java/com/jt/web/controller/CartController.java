package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.service.DubboCartService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private DubboCartService cartService;
	
	@RequestMapping("/show")
	public String findCartList(Model model) {
		Long userId = UserThreadLocal.get().getId();
		List<Cart> cartList = cartService.findCartByUserId(userId);
		model.addAttribute("cartList",cartList);
		return "cart";
	}
	
	//实现购物车新增
		@RequestMapping("/add/{itemId}")
		public String saveCart(@PathVariable Long itemId,Cart cart){
			
			Long userId = UserThreadLocal.get().getId();
			cart.setUserId(userId);
			cart.setItemId(itemId);
			cartService.saveCart(cart);
			//重定向到购物车展现页面
			return "redirect:/cart/show.html";
		}
		@RequestMapping("/update/num/{itemId}/{num}")
		@ResponseBody
		public SysResult updateCart(@PathVariable Long itemId,@PathVariable Integer num){
			try {
				Long userId =  UserThreadLocal.get().getId();
				Cart cart = new Cart();
				cart.setItemId(itemId);
				cart.setUserId(userId);
				cart.setNum(num);
				cartService.updateCartNum(cart);
				return SysResult.oK();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SysResult.build(201,"商品修改失败");
		}
		
		//实现购物车删除
		@RequestMapping("/delete/{itemId}")
		public String deleteCart(@PathVariable Long itemId){
			Long userId = UserThreadLocal.get().getId();
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setItemId(itemId);
			cartService.deleteCart(cart);
			return "redirect:/cart/show.html";
		}


}
