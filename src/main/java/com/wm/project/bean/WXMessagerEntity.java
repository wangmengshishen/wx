package com.wm.project.bean;

public class WXMessagerEntity {
private String xml;
private String ToUserName;
private String FromUserName;
private String MsgType;
private String Content;
private String MsgId;
private long CreateTime;
public String getXml() {
	return xml;
}
public void setXml(String xml) {
	this.xml = xml;
}
public String getToUserName() {
	return ToUserName;
}
public void setToUserName(String toUserName) {
	ToUserName = toUserName;
}
public String getFromUserName() {
	return FromUserName;
}
public void setFromUserName(String fromUserName) {
	FromUserName = fromUserName;
}
public String getMsgType() {
	return MsgType;
}
public void setMsgType(String msgType) {
	MsgType = msgType;
}
public String getContent() {
	return Content;
}
public void setContent(String content) {
	Content = content;
}
public String getMsgId() {
	return MsgId;
}
public void setMsgId(String msgId) {
	MsgId = msgId;
}
public long getCreateTime() {
	return CreateTime;
}
public void setCreateTime(long createTime) {
	CreateTime = createTime;
}

}
