package com.zy17.douban.convert;

import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.bean.MovieTop250Result;
import com.zy17.douban.bean.SimpleSubject;
import com.zy17.entity.CelebrityEntity;
import com.zy17.entity.MovieSearchResultEntity;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class CelebrityEntityConvert {
    public static CelebrityEntity convert(CelebrityResult subject) {
        CelebrityEntity celebrityEntity = new CelebrityEntity();
        celebrityEntity.setName(subject.getName());
        celebrityEntity.setNameEn(subject.getName_en());
        celebrityEntity.setCelebrityId(subject.getId());
        if(subject.getAvatars()!=null){
            celebrityEntity.setAvatarLarge(subject.getAvatars().get(CelebrityResult.IMAGE_TYPE_LARGE));
        }
        celebrityEntity.setBornPlace(subject.getBorn_place());

        return celebrityEntity;
    }

}
