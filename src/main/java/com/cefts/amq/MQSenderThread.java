package com.cefts.amq;

import javax.jms.JMSException;

public class MQSenderThread extends Thread{
    public void run(){
        MQSendMessage sendMessage = new MQSendMessage();
        try {
            int i=1;
            while (true){
                sendMessage.initialize();
                sendMessage.sendMessage(EnumMessageType.TEXT,"消息producer"+i+"号");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        MQSenderThread mqSenderThread = new MQSenderThread();
        mqSenderThread.start();
        MQConsumer mqConsumer = new MQConsumer();
        mqConsumer.messageRecive();
    }
}
