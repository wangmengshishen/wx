package com.wm.project.bean;

public class WXConfig {
private boolean debug;
private String appId;
private long timestamp;
private String nonceStr;
private String signature;
private String[] jsApiList;
public boolean isDebug() {
	return debug;
}
public void setDebug(boolean debug) {
	this.debug = debug;
}
public String getAppId() {
	return appId;
}
public void setAppId(String appId) {
	this.appId = appId;
}
public long getTimestamp() {
	return timestamp;
}
public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}
public String getNonceStr() {
	return nonceStr;
}
public void setNonceStr(String nonceStr) {
	this.nonceStr = nonceStr;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public String[] getJsApiList() {
	return jsApiList;
}
public void setJsApiList(String[] jsApiList) {
	this.jsApiList = jsApiList;
}

}
