/*
 * Copyright (C) 2016 , Inc. All Rights Reserved.
 */
package com.zy17.weixin.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.zy17.weixin.bean.ticket.Ticket;
import com.zy17.weixin.client.LocalHttpClient;

/**
 * JSAPI ticket
 * @author LiYi
 *
 */
public class TicketAPI extends BaseAPI {

	/**
	 * 获取 jsapi_ticket
	 * @param access_token
	 * @return
	 */
	public static Ticket ticketGetticket(String access_token){
		HttpUriRequest httpUriRequest = RequestBuilder.post()
				.setUri(BASE_URI + "/cgi-bin/ticket/getticket")
				.addParameter(PARAM_ACCESS_TOKEN,access_token)
				.addParameter("type", "jsapi")
				.build();
		return LocalHttpClient.executeJsonResult(httpUriRequest,Ticket.class);
	}
}
