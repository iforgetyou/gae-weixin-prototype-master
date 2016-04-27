/*
 * Copyright (C) 2016 , Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.qrcode;

import com.zy17.weixin.bean.BaseResult;

/**
 * 二维码 ticket
 * @author LiYi
 *
 */
public class QrcodeTicket extends BaseResult {

	private String ticket;

	private Integer expire_seconds;

	private String url;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getExpire_seconds() {
		return expire_seconds;
	}

	public void setExpire_seconds(Integer expireSeconds) {
		expire_seconds = expireSeconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
