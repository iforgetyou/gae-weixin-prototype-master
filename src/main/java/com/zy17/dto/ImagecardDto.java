package com.zy17.dto;

import lombok.Data;

/**
 * Created by zhangyan53 on 2016/4/12.
 */
@Data
public class ImagecardDto extends BaseDto {
    public final static int NoMoreCard = 404;
    private String image;
    private String cardId;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

}
