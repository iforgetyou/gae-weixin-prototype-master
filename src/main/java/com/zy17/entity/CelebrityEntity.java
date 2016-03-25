package com.zy17.entity;

import lombok.Data;

/**
 * Created by zy17 on 2016/3/19.
 */
@Data
public class CelebrityEntity extends BaseEntity {
    // 演员id
    private String celebrityId;
    // 大头像
    private String avatarLarge;
    // 姓名
    private String name;
    // 英文名字
    private String nameEn;
    // 出生地
    private String  bornPlace;
}
