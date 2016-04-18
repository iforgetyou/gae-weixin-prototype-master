package com.zy17.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: cat
 * Date: 14-7-19
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */

@Data
public class ImageItem extends BaseEntity {
    // 电影
    //    public static final String MOVIE = "movie";
    // 名人
    public static final String CELEBRITY = "celebrity";
    // 广告
    //    public static final String ADVERTISEMENT = "ad";

    // 图片上传者
    private String creator;
    // 微信mediaId
    private String mediaId;
    // 图片url连接
    private String picUrl;
    // 图片类别
    private String type;
    // 图片标签列表
    private String tags;
    // gae blobkey
    private String blobKey;

    // 是否微信永久资源
    private boolean weixinMaterial;
}
