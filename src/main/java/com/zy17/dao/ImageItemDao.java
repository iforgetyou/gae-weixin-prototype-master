package com.zy17.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.zy17.model.ImageItem;

public interface ImageItemDao {
    Key save(ImageItem imageItem);

    List<ImageItem> findOneByTag(String tag);
    ImageItem findOneById(String id);

}