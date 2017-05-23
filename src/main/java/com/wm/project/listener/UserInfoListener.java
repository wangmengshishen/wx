package com.wm.project.listener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import com.wm.project.service.ReceiverService;
@Component("userPushListener")
public class UserInfoListener implements MessageListener {
	@Resource
	private ReceiverService receiverService;
	public void onMessage(Message message) {
	    TextMessage textMessage = (TextMessage) message;  
        try {  
            //获取数据  
            String text = textMessage.getText();  
           System.out.println("[UserPushListener.onMessage]:receive message is,"+ text);  
            if (text != null) {  
            	try {
					receiverService.createTxtFile(text);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            }  
        } catch (JMSException e) {  
            System.out.println("[UserPushListener.onMessage]:receive message occured an exception");  
        }  
	}

}
