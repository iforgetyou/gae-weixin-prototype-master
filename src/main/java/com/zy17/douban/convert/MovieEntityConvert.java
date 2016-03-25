package com.zy17.douban.convert;

import com.zy17.douban.bean.SimpleSubject;
import com.zy17.entity.MovieSearchResultEntity;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class MovieEntityConvert {
    public static MovieSearchResultEntity convert(SimpleSubject subject) {
        MovieSearchResultEntity movie = new MovieSearchResultEntity();
        movie.setAlt(subject.getAlt());
        movie.setMovieId(subject.getId());
        movie.setAverageRating(subject.getRating().get(SimpleSubject.RATING_AVERAGE));
        movie.setStarsRating(subject.getRating().get(SimpleSubject.RATING_STARS));
        movie.setCollect_count(subject.getCollect_count());
        movie.setImageSmall(subject.getImages().get(SimpleSubject.IMAGE_TYPE_SMALL));
        movie.setImageMedium(subject.getImages().get(SimpleSubject.IMAGE_TYPE_MEDIUM));
        movie.setImageLarge(subject.getImages().get(SimpleSubject.IMAGE_TYPE_LARGE));
        movie.setOriginal_title(subject.getOriginal_title());
        movie.setYear(subject.getYear());
        movie.setTitle(subject.getTitle());
        movie.setSubtype(subject.getSubtype());
        return movie;
    }
}
