package com.zy17.entity;

import com.zy17.douban.bean.MovieSearchResult;

import lombok.Data;

/**
 * 电影搜索结果存储
 * Created by zy17 on 2016/3/17.
 */
@Data
public class MovieSearchResultEntity extends BaseEntity {
    private long imageItemId;
    private String movieId;
    private String title;
    //    original_title	原名	str	Y	Y	Y	''
    private String original_title;
    //    alt	条目URL	float(1)	Y	Y	Y	-
    private String alt;

    private String imageLarge;
    private String imageMedium;
    private String imageSmall;
    // 评星数
    private String starsRating;
    // 平均分
    private String averageRating;

    private String year;
    //    subtype	条目分类, movie或者tv	str	Y	Y	Y	movie
    private String subtype;
    // 搜藏数
    private long collect_count;

}
