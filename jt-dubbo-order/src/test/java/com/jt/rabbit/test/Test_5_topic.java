package com.jt.rabbit.test;

import org.junit.Before;
import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Test_5_topic {
	private Connection connection;

	/*
	 * 1,初始化链接
	 * 	链接rabbitMQ的步骤:
	 * 		定义主机的地址ip/5672/用户名和密码/虚拟主机名称
	 */
	@Before // 表示在执行@Test注解之前执行该操作
	public void init() throws Exception {
		ConnectionFactory factory= new ConnectionFactory();
		factory.setHost("192.168.186.136");
		factory.setPort(5672);
		factory.setUsername("jtadmin");
		factory.setPassword("jtadmin");
		factory.setVirtualHost("/jt");
		connection	= factory.newConnection();
	}
	//定义生产者
	@Test
	public void proverder() throws Exception{
		//获取通道
		Channel channel = connection.createChannel();
		
		//定义交换机的名称
		String exchange_name = "TOP";
		
		//创建交换机队列   
		//exchange  交换机名称
		//type 定义类型 fanout 发布订阅模式   direct-路由模式    topic-主题模式
		channel.exchangeDeclare(exchange_name, "topic");  //主题模式
		
		for (int i = 0; i < 100; i++) {
			
			String msg = "主题模式"+i;
			
			/**
			 * 参数说明:
			 * 	exchange:交换机名称
			 *  routingKey:路由key
			 *  props:参数
			 *  body:数据字节码
			 */
			//channel.basicPublish(exchange, routingKey, props, body);
			channel.basicPublish(exchange_name,"item.update.aa.bb", null, msg.getBytes());
		}
		channel.close();
		connection.close();
	}
	
	@Test
	public  void consumer1() throws Exception{
		
		//定义通道
		Channel channel = connection.createChannel();
		
		//定义交换机名称
		String exchange_name = "TOP";
		
		//定义队列名称
		String queue_name = "TOP1";
		
		//声明交换机名称以及主题模式
		channel.exchangeDeclare(exchange_name, "topic");
		
		//定义队列
		channel.queueDeclare(queue_name, false, false, false, null);
		
		//将交换机和队列进行绑定   
		//参数1.队列名称     参数2交换机名称   参数3 路由key  #号匹配多个字符
		channel.queueBind(queue_name, exchange_name, "item.#");
		
		channel.basicQos(1);  //定义消费数量  1
		
		//定义消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//将队列和消费者绑定
		channel.basicConsume(queue_name, false, consumer);  //定义手动回复
		
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//获取消息队列中的数据
			String msg = new String(delivery.getBody());
			System.out.println("item.#消费者1:"+msg);
			
			//手动回复
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);	
		}
	}
	
	@Test
	public  void consumer2() throws Exception{
		
		//定义通道
		Channel channel = connection.createChannel();
		
		//定义交换机名称
		String exchange_name = "TOP";
		
		//定义队列名称
		String queue_name = "TOP2";
		
		//声明交换机名称以及主题模式
		channel.exchangeDeclare(exchange_name, "topic");
		
		//定义队列
		channel.queueDeclare(queue_name, false, false, false, null);
		
		//将交换机和队列进行绑定   
		//参数1.队列名称     参数2交换机名称   参数3 路由key  #号匹配多个字符
		channel.queueBind(queue_name, exchange_name, "item.*");
		
		channel.basicQos(1);  //定义消费数量  1
		
		//定义消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//将队列和消费者绑定
		channel.basicConsume(queue_name, false, consumer);  //定义手动回复
		
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			//获取消息队列中的数据
			String msg = new String(delivery.getBody());
			System.out.println("item.*消费者2:"+msg);
			
			//手动回复
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);	
		}
	}

}
