package com.jt.sso.service;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.BaseService;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Autowired
	private UserMapper userMapper;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public boolean findCheckUser(String param, Integer type) {
		String column = null;
		switch (type) {
		case 1: 
			column = "username";break;
		case 2: 
			column = "phone";break;
		case 3: 
			column = "email";break;
		}
		
		int count = userMapper.findCheckUser(column,param);
		return count==0?false:true;
	}

	@Override
	public void saveUser(User user) {
			
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		user.setEmail(user.getPhone());
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	/**
	 * 1.将密码进行加密处理
	 * 2.根据用户名和密码查询数据库获取user对象
	 * 3.如果查询的数据为null,证明用户名或密码错误 则抛出异常
	 * 4.如果用户不为null. 则准备token秘钥,
	 * 	 和userJSON数据保存到redis中 最好设定超时时间 7天
	 * 
	 */
	@Override
	public String findUserByUP(User user) {
		String token = null;	//秘钥
		
		String md5Password = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5Password);
		//查询用户   根据对象中不为null的元素充当where条件
		User userDB = super.queryByWhere(user);
		
		if(userDB == null){
			//证明用户名密码错误
			throw new RuntimeException();
		}
		
		try {
			String userJSON = objectMapper.writeValueAsString(userDB);
			//md5（“JT_TICKET_” + System.currentTime + username）
			token = DigestUtils.md5Hex("JT_TICKET_"+System.currentTimeMillis() + user.getUsername());
			jedisCluster.setex(token, 3600 * 24 * 7, userJSON);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return token;
	}

	
}