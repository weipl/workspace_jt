package cn.tedu.mybatis.service;

import java.util.List;

import javax.websocket.server.PathParam;

import cn.tedu.mybatis.pojo.User;

public interface UserService {
	public List<User> find();
	
	public User get(@PathParam("id") Integer id);
}
