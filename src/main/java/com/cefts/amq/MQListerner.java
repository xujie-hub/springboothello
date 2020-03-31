package com.cefts.amq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MQListerner implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("我接受consumer:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
