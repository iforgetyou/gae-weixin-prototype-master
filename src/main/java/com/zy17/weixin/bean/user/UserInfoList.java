/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.zy17.weixin.bean.user;

import java.util.List;

import com.zy17.weixin.bean.BaseResult;

public class UserInfoList extends BaseResult {

	private List<User> user_info_list;

	public List<User> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<User> user_info_list) {
		this.user_info_list = user_info_list;
	}

}
