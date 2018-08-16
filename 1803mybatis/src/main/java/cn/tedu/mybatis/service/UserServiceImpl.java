package cn.tedu.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.mybatis.mapper.UserMapper;
import cn.tedu.mybatis.pojo.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	public List<User> find(){
		return userMapper.find();
	}

	@Override
	public User get(Integer id) {
		return userMapper.get(id);
	}
}
