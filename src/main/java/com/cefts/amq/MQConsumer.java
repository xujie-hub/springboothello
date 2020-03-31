package com.cefts.amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class MQConsumer {
    private static String MQ_BROKETURL = "tcp://localhost:61616";
    private String QUEUE_NAME="DATA-TBA-0001";
    public void messageRecive(){
        // 连接工厂
        ConnectionFactory connectionFactory;
        // 连接实例
        Connection connection = null;
        // 收发的线程实例
        Session session;
        // 消息发送目标地址
        Destination destination;
        try {
            // 实例化连接工厂
            connectionFactory = new ActiveMQConnectionFactory(MQ_BROKETURL);
            // 获取连接实例
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建接收或发送的线程实例（消费者就不需要开启事务了）
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            // 创建队列（返回一个消息目的地）
            destination = session.createQueue(QUEUE_NAME);
            // 创建消息消费者
            MessageConsumer consumer = session.createConsumer(destination);
            //注册消息监听
            consumer.setMessageListener(new MQListerner());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
