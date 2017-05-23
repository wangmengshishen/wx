package com.wm.project.serviceImpl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.wm.project.bean.SysUser;
import com.wm.project.service.SenderService;
@Service
public class SenderServiceImpl implements SenderService {
	@Autowired
	private JmsTemplate jmsTemplate;

	/**
	 * 这里是根据MQ配置文件定义的queue来注入的，也就是这里将会把不同的内容推送到不同的queue中
	 */
	@Autowired
	@Qualifier("userServiceQueue")
	private Destination destination;

	public void addQueue(final SysUser user) {
		jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(user.toString());  
            }  
        });  
    }             
}
