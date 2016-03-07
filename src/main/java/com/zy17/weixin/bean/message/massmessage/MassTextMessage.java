/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.message.massmessage;

import java.util.HashMap;
import java.util.Map;

public class MassTextMessage extends MassMessage{

	private Map<String, String> text;

	public MassTextMessage(String content) {
		super();
		text = new HashMap<String, String>();
		text.put("content",content);
		super.msgtype = "text";
	}

	public Map<String, String> getText() {
		return text;
	}

	public void setText(Map<String, String> text) {
		this.text = text;
	}


}
