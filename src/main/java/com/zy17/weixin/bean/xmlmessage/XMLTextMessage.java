/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.xmlmessage;

public class XMLTextMessage extends XMLMessage{

	private String content;
	
	public XMLTextMessage(String toUserName, String fromUserName, String content) {
		super(toUserName, fromUserName, "text");
		this.content = content;
	}


	@Override
	public String subXML() {
		return "<Content><![CDATA["+content+"]]></Content>";
	}

	
}
