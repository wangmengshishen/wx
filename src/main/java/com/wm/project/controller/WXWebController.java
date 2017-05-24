package com.wm.project.controller;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tapestry.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thoughtworks.xstream.XStream;
import com.wm.project.bean.IntelligentWordEntity;
import com.wm.project.bean.WXConfig;
import com.wm.project.bean.WXMessagerEntity;
import com.wm.project.common.HttpRequestUtils;

@Controller
@RequestMapping("/wx")
public class WXWebController {
	private static final String token = "WM666";
	private static final String appid="wx153064fecbd5acb3";
	private static final String secret="d4624c36b6795d1d99dcf0547af5443d";

	@RequestMapping("/validate.json")
	public void validateWxToken(String signature, String timestamp,
			String nonce, String echostr,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
	/*	List<String> list = new ArrayList<String>();
		list.add(nonce);
		list.add(timestamp);
		list.add(token);
		Collections.sort(list);
		String str = DigestUtils
				.shaHex(list.get(0) + list.get(1) + list.get(2));
		if (signature.equalsIgnoreCase(str)) {
			response.getWriter().write(echostr);
		}*/
		System.out.println(request.getRequestURI());
		System.out.println(request.getQueryString());
		InputStream ins=request.getInputStream();
		String xml=new String(IOUtils.toByteArray(ins),"UTF-8");
		XStream xstream = new XStream();
		Field[] fields=WXMessagerEntity.class.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			   xstream.alias(fields[i].getName(),WXMessagerEntity.class);
		}
		WXMessagerEntity messager=(WXMessagerEntity)xstream.fromXML(xml);
		String toUSer=messager.getToUserName();
		messager.setToUserName(messager.getFromUserName());
		messager.setFromUserName(toUSer);
		String fromMessage=messager.getContent();
		if("1".equals(fromMessage)){
			messager.setContent("你好！");	
		}else if("2".equals(fromMessage)){
			messager.setContent("谢谢！");			
		}else if("3".equals(fromMessage)){
			messager.setContent("再见！");			
		}else if("4".equals(fromMessage)){
			//
			IntelligentWordEntity intelligent=new IntelligentWordEntity();
			intelligent.setCity("北京");
			intelligent.setQuery("查一下明天从北京到上海的南航机票");
			intelligent.setCategory("flight,hotel");
			intelligent.setAppid(appid);
			intelligent.setUid(request.getParameter("openid"));
			String result=intelligentSemantic(intelligent,request);
			messager.setContent(result);			
			//
		}
		else{
			messager.setContent("1:你好！,2:谢谢!,3:再见！,4:天气查询");	
		}
	
		messager.setMsgId("");
		messager.setCreateTime(System.currentTimeMillis()/1000);
	    xstream = new XStream();
	    xstream.alias("xml", WXMessagerEntity.class); 
		xml=xstream.toXML(messager);
		response.getWriter().write(xml);
	}
	@RequestMapping("/getWXUserInfo.json")
	public void getWXUserInfo(String code,HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		System.out.println(code);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String url="https://api.weixin.qq.com/sns/oauth2/access_token";
		String params="appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		String result=HttpRequestUtils.sendGet(url, params, null);
		JSONObject json=new JSONObject(result);
		String token=json.getString("access_token");
		String openId=json.getString("openid");
		String result1=HttpRequestUtils.sendGet("https://api.weixin.qq.com/sns/userinfo", "access_token="+token+"&openid="+openId+"&lang=en", null);
         response.getWriter().write(result1);
	}
	public String getAccess_Token(HttpServletRequest request) throws Exception{
		Map<String,Object>_token=(Map<String,Object>)request.getSession().getServletContext().getAttribute("token");
		if(_token==null||_token.size()==0||(System.currentTimeMillis()/1000>(Long)_token.get("expire"))){
				String url="https://api.weixin.qq.com/cgi-bin/token";
				String prarms="grant_type=client_credential&appid="+appid+"&secret="+secret;
				String result=HttpRequestUtils.sendGet(url, prarms, null);
				JSONObject json=new JSONObject(result);
				Map<String,Object>token=new HashMap<String,Object>();
				result=json.getString("access_token");
				int expire=json.getInt("expires_in");
				token.put("token", result);
				token.put("expire",System.currentTimeMillis()/1000+expire/2);
				request.getSession().getServletContext().setAttribute("token",token);	
				return result;
		}
		return (String)_token.get("token");
	}
	public  String intelligentSemantic(IntelligentWordEntity intelligent,HttpServletRequest request) throws Exception{
		String access_token=getAccess_Token(request);
		String url="https://api.weixin.qq.com/semantic/semproxy/search?access_token="+access_token;
		//String params=net.sf.json.JSONObject.fromObject(intelligent).toString();
		//String result=HttpRequestUtils.sendPost(url, params, null);
		//return result;
		return null;
	}
	@RequestMapping("/userAuth.html")
	public String userAuth(HttpServletResponse response,Model model) throws Exception{
		String url="https://open.weixin.qq.com/connect/oauth2/authorize?";
		String params="appid="+appid+"&redirect_uri="+URLEncoder.encode("http://wm666.imwork.net/wx/getWXUserInfo.json")
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		//String result=HttpRequestUtils.sendGet(url, params, null);
		System.out.println(url+params);
		model.addAttribute("a", url+params);
		return "wx";
	}
	@RequestMapping("/jssdk.html")
	public String jsSDKPAge(HttpServletResponse response,HttpServletRequest request,Model model) throws Exception{
		WXConfig config=new WXConfig();
		config.setAppId(appid);
		config.setDebug(false);
		config.setNonceStr("Wm3WZYTPz0wzccnW");
		config.setTimestamp(System.currentTimeMillis()/1000);
		config.setJsApiList(new String[]{"onMenuShareQQ","onMenuShareWeibo"});
		String ticket=getTicket(request);
		config.setSignature(this.getJSSDKsingature(config, "http://wm666.imwork.net/wx/jssdk.html", ticket));
		model.addAttribute("wxConfig", config);
		return "jssdk";
	}
	public String getTicket(HttpServletRequest request) throws Exception{
		String token=this.getAccess_Token(request);
		String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi";
		String result=HttpRequestUtils.sendGet(url, "", null);
		JSONObject json=new JSONObject(result);
		result=json.getString("ticket");
		return result;
	}
	public String getJSSDKsingature(WXConfig config,String url,String ticket){
		String sinStr="jsapi_ticket="+ticket+"&noncestr="+config.getNonceStr()+"&timestamp="+config.getTimestamp()+"&url="+url;
		sinStr=DigestUtils.shaHex(sinStr);
		return sinStr;
	}
public static void main(String[] args) throws Exception {
	//System.out.println(WXWebController.getOauthCode());
}
}
