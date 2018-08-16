package com.jt.rabbit.test;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;




public class Test_1_simple {
	private Connection connection;
	/*
	 * 1,初始化链接
	 * 	链接rabbitMQ的步骤:
	 * 		定义主机的地址ip/5672/用户名和密码/虚拟主机名称
	 */
	@Before // 表示在执行@Test注解之前执行该操作
	public void init() throws IOException {
		ConnectionFactory factory= new ConnectionFactory();
		factory.setHost("192.168.186.136");
		factory.setPort(5672);
		factory.setUsername("jtadmin");
		factory.setPassword("jtadmin");
		factory.setVirtualHost("/jt");
		connection	= factory.newConnection();
	}
	
	@Test
	public void test01() {
		System.out.println("检测链接是否正常"+connection.getPort());
	}
	@Test
	public void provader() throws IOException {
		/**
		 * 1,定义通道
		 * 主要作用是:创建队列/消费者,发布消息 
		 */
		Channel channel = connection.createChannel();
		
		//2,定义消息队列
		String queueName = "simple";
		/**
		 * queue:度咧名称
		 * durabl:是否持久化
		 * exclusive:是否生成者独有false
		 * autoDelete 当队列中的消息没有处理完成后,队列自动删除
		 * argument 是否需要传递其他参数 null
		 */
		channel.queueDeclare(queueName,true,false,false,null);
		
		//3,定义队列的小时,rpc默认时候U8
		String msg = "我是简单模式";
		
		//4,发送消息
		/**
		 * 	exchange:交换机名称,如果没有交换机则为""串
		 *  routingKey: 路由key,如果没有路由key则使用队列的名称代替
		 *  props :表示消息队列的配置文件
		 *  props:表示消息内容的二进制数组
		 */
		channel.basicPublish("", queueName, null, msg.getBytes());
		
		channel.close();
		connection.close();
		
		System.out.println("消息队列执行成功");
	}
	
	@Test
	public void comsumer() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		//定义队列 
		Channel channel = connection.createChannel();
		
		String queueName = "simple";
		
		channel.queueDeclare(queueName, true, false, false, null);
		//定义消费者队列
		
		QueueingConsumer consumer = new QueueingConsumer(channel);
		/**
		 * queue:队列
		 * callback:回调给消费者
		 * autoACK:发送确认消费 true ,自动回复,  false
		 */
		//将消费者和队列进行绑定
		channel.basicConsume(queueName, true,consumer);
		//让消费者主动去获取队列中消息配置监听器
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String msg  = new String(delivery.getBody());
			System.out.println("成功获取消息:"+msg);
		}
	}
}
