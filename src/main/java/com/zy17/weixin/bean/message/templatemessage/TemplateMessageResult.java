/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.message.templatemessage;

import com.zy17.weixin.bean.BaseResult;

public class TemplateMessageResult extends BaseResult{

	private Long msgid;

	public Long getMsgid() {
		return msgid;
	}

	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}


}
