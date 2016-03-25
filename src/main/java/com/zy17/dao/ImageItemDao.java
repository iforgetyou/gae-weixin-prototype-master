package com.zy17.dao;

import java.util.List;

import com.zy17.entity.ImageItem;

public interface ImageItemDao extends BaseDao<ImageItem> {
    /**
     * 数据库tag全匹配查找
     *
     * @param tag
     *
     * @return
     */
    List<ImageItem> findOneByTag(String tag);

    List<Long> findAllAvaluableId();
    List<Long> findAllId();

}