package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

public interface ItemMapper extends SysMapper<Item>{

	public List<Item> findItemAll();
	
	@Select("select * from tb_item\r\n" + 
			"  	order by updated limit #{start},#{rows}")
	public List<Item> findItemPage(@Param("start")int start,
									@Param("rows")Integer rows);

}
