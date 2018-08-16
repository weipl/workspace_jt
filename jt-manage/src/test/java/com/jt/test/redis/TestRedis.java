package com.jt.test.redis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestRedis {
	
	/**
	 * 1.实例化jedis对象(IP:端口)
	 * 2.实现redis取值赋值操作
	 */
	@Test
	public void test01(){
		Jedis jedis = new Jedis("192.168.186.110",7000);
		jedis.set("name", "tomcat猫");
		System.out.println("获取redis数据:"+jedis.get("name"));
	}
	
	//测试redis分片 实现redis内存动态扩容
	@Test
	public void test02(){
		//定义redis池的配置文件
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(1000);
		poolConfig.setMaxIdle(200);
		poolConfig.setMinIdle(10);
		poolConfig.setTestOnBorrow(true); //链接前校验
		
		//定义jedis分片的节点信息
		List<JedisShardInfo> shards = 
				new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo("192.168.186.110", 7000));
		shards.add(new JedisShardInfo("192.168.186.110", 7001));
		shards.add(new JedisShardInfo("192.168.186.110", 7002));
		
		ShardedJedisPool jedisPool = 
				new ShardedJedisPool(poolConfig, shards);
		
		ShardedJedis shardedJedis = jedisPool.getResource();
		
		shardedJedis.set("wpl", "我是redis分片");
		System.out.println
		("获取redis信息:"+shardedJedis.get("wpl"));
	}
	
	
	//实现哨兵的测试
	//@Test
	public void test03() {
		//哨兵port不许重复
		Set<String> sentinels = new HashSet<String>();
		System.out.println(new HostAndPort("192.168.186.134", 26379));
		
		sentinels.add("192.168.186.134:26379");
		sentinels.add("192.168.186.134:26380");
		sentinels.add("192.168.186.134:26381");
		//定义哨兵连接池,从中拿出master的redis节点
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",sentinels);
		Jedis jedis = sentinelPool.getResource();
		jedis.set("name", "我是哨兵的reids");
		System.out.println("获取哨兵的数据:"+jedis.get("name"));
	}
	/**
	 * 步骤：
	 * 	1、reids的节点3主6从9个节点
	 * 	2、每个节点需要通过IP：端口的形式连接
	 * 	3、创建集群的连接对象API调用
	 */
	@Test
	public void testCluster() {
		//1、定义集群的集合
		String host = "192.168.186.134";
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort(host,7000));
		nodes.add(new HostAndPort(host,7001));
		nodes.add(new HostAndPort(host,7002));
		nodes.add(new HostAndPort(host,7003));
		nodes.add(new HostAndPort(host,7004));
		nodes.add(new HostAndPort(host,7005));
		nodes.add(new HostAndPort(host,7006));
		nodes.add(new HostAndPort(host,7007));
		nodes.add(new HostAndPort(host,7008));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("1803","集群搭建完成");
		System.out.println("获取数据:"+jedisCluster.get("1803"));
		
	}
	
	@Test
	public void testFactory(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("/spring/factory.xml");
		Calendar calendar1 = (Calendar) context.getBean("calendar1");
		Calendar calendar2 = (Calendar) context.getBean("calendar2");
		Calendar calendar3 = (Calendar) context.getBean("calendar3");
		System.out.println("一:"+calendar1.getTime());
		System.out.println("二:"+calendar2.getTime());
		System.out.println("三:"+calendar3.getTime());
	}
	
	
	
}
