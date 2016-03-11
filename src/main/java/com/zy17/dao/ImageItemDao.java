package com.zy17.dao;

import java.util.List;

import com.zy17.model.ImageItem;

public interface ImageItemDao {
    ImageItem save(ImageItem imageItem);

    List<ImageItem> findOneByTag(String tag);
}