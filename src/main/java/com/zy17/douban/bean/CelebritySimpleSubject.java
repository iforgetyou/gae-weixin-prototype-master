package com.zy17.douban.bean;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 影人工作条目
 * Created by zy17 on 2016/3/17.
 */
@Data
public class CelebritySimpleSubject extends BaseResult {
    // 角色
    private List<String> roles;
    // 简单电影条目
    private SimpleSubject subject;
}
