/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.xmlmessage;

public class XMLVoiceMessage extends XMLMessage{

	private String mediaId;
	
	public XMLVoiceMessage(String toUserName, String fromUserName, String mediaId) {
		super(toUserName, fromUserName, "voice");
		this.mediaId = mediaId;
	}


	@Override
	public String subXML() {
		return "<Voice><MediaId><![CDATA["+mediaId+"]]></MediaId></Voice>";
	}

	
}
