package com.wm.project.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wm.project.bean.SysUser;
import com.wm.project.service.SenderService;

@Controller
@RequestMapping("/sysUser")
public class TestUserController {
	@Resource
	private SenderService senderService;
	@RequestMapping("/userPage.html")
public String getUserPage(SysUser user){
	senderService.addQueue(user);
	return "userPage";
}
	@RequestMapping("/getResponseInfo.json")
	public void getResponseInfo(HttpServletRequest request,HttpServletResponse response){
		   Map<String, String> map = new HashMap<String, String>();
		    Enumeration headerNames = request.getHeaderNames();
		    while (headerNames.hasMoreElements()) {
		        String key = (String) headerNames.nextElement();
		        String value = request.getHeader(key);
		        map.put(key, value);
		    }
		    System.out.println(map);
		try {
			response.getWriter().write(map.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
