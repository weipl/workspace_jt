package cn.tedu.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.tedu.mybatis.pojo.User;

public interface UserMapper {
	//<select id="find" resultType="User"></select>
	public List<User> find();
	
	//<select id="find" resultType="User">
	@Select("select * from user where id = #{pid}")
	public User get(@Param("pid") Integer id);
	
}
