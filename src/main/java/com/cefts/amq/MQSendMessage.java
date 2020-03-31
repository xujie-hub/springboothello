package com.cefts.amq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

public class MQSendMessage {
    private static String MQ_URL="tcp://localhost:61616";
    private Connection conn = null;
    private Session session = null;
    private MessageProducer producer = null;
    private String QUEUE_NAME="DATA-TBA-0001";

    public void initialize() throws JMSException {
        //创建一个连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //创建连接
        conn = connectionFactory.createConnection();
        //创建接收或发送的线程实例（创建session的时候定义是否要启用事务，且事务类型是Auto_ACKNOWLEDGE也就是消费者成功在Listern中获得消息返回时，会话自动确定用户收到消息）
        session = conn.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建一个消息队列
        Destination destination= session.createQueue(QUEUE_NAME);
        //创建生产者
        producer = session.createProducer(destination);
    }

    public void sendMessage(EnumMessageType msgTypeEnum, Object msgObject){
        try {
            conn.start();
            System.out.println(msgObject.toString());
            //创建消息实体
            switch (msgTypeEnum) {
                // 发送字节消息
                case BYTES:
                    BytesMessage bytesMessage = session.createBytesMessage();
                    bytesMessage.writeBytes(msgObject.toString().getBytes());
                    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    producer.send(bytesMessage);
                    break;
                // 发送Map消息
                case MAP:
                    MapMessage mapMessage = session.createMapMessage();
                    mapMessage.setBoolean("boolean", true);
                    mapMessage.setShort("short", (short) 0);
                    mapMessage.setLong("long", 123456);
                    mapMessage.setString("MapMessage", "ActiveMQ Map Message!");
                    producer.send(mapMessage);
                    break;
                // 发送对象消息
                case OBJECT:
                    ObjectMessage objectMessage = session.createObjectMessage();
                    objectMessage.setObject((Serializable) msgObject);
                    producer.send(objectMessage);
                    break;
                // 发送流消息
                case STREAM:
                    StreamMessage streamMessage = session.createStreamMessage();
                    streamMessage.writeBoolean(false);
                    streamMessage.writeLong(1234567890);
                    producer.send((StreamMessage) streamMessage);
                    break;
                // 发送文本消息
                case TEXT:
                    TextMessage textMessage = session.createTextMessage();
                    textMessage.setText((String) msgObject);
                    producer.send(textMessage);
                    break;
                default:
                    break;
            }
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    // 关闭连接
    public void close() throws JMSException {
        if (producer != null)
            producer.close();
        if (session != null)
            session.close();
        if (conn != null)
            conn.close();
    }

}
