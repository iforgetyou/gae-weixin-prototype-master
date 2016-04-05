package com.zy17.dto;

import lombok.Data;

/**
 * Created by zhangyan53 on 2016/3/30.
 */
@Data
public class CelebrityImageDto {
    // 图片url连接
    private String picUrl;
    // 图片类别
    private String type;
    // 图片标签列表
    private String tags;
}
