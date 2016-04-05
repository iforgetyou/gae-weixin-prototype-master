package com.zy17.entity;

import lombok.Data;

/**
 * 答题卡片
 */

@Data
public class ImageGuessCard extends BaseEntity {
    // 电影
    //    public static final String MOVIE = "movie";
    // 名人
    public static final String CELEBRITY = "celebrity";
    // 广告
    //    public static final String ADVERTISEMENT = "ad";

    // 图片上传者
    private String creator;
    // 图片url连接
    private String picUrl;
    // 图片类别
    private String type;
    // 图片正确答案
    private String answer;
}
