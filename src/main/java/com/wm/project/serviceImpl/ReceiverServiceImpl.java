package com.wm.project.serviceImpl;

import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.wm.project.bean.SysUser;
import com.wm.project.service.ReceiverService;
@Service
public class ReceiverServiceImpl implements ReceiverService {
	public void createTxtFile(String text) throws Exception {
		 Thread.sleep(10000);
      FileOutputStream fos=new FileOutputStream("D://mqtxt//"+System.currentTimeMillis()+".txt");
      fos.write(text.getBytes());
      fos.close();
	}
public static void main(String[] args) throws Exception {
	SysUser user=new SysUser();
	user.setName("jack");
	user.setAge(20);
	user.setGender("F");
	ReceiverServiceImpl se=new ReceiverServiceImpl();
	se.createTxtFile(user.toString());
	
	
}
}
