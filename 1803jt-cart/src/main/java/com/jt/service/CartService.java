package com.jt.service;

import java.util.List;

import com.jt.common.vo.SysResult;
import com.jt.pojo.Cart;

public interface CartService {
	public List<Cart> myCart(Integer userId);
	public SysResult save(Cart cart);
	public void updateNum(Cart cart);
	public void deleteNum(Integer userId,Integer itemId);
}
