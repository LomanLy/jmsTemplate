package com.loman.filter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Administrator on 2017/4/13.
 */
public class QueueMessageListener implements MessageListener {
    public void onMessage(Message message){
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听文本信息"+tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
