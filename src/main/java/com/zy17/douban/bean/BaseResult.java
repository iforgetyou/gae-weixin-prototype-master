package com.zy17.douban.bean;

import com.alibaba.fastjson.JSON;
import com.zy17.entity.BaseEntity;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
public class BaseResult extends BaseEntity {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
