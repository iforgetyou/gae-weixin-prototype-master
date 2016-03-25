package com.zy17.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;
import com.zy17.GaeBaseTest;
import com.zy17.douban.api.celebrity.CelebrityApi;
import com.zy17.douban.bean.CelebrityResult;
import com.zy17.douban.convert.CelebrityEntityConvert;
import com.zy17.entity.CelebrityEntity;

import static org.hamcrest.Matchers.*;

/**
 * Created by zhangyan53 on 2016/3/19.
 */
public class CelebrityDaoTest extends GaeBaseTest {
    @Autowired
    CelebrityDao dao;

    @Test
    public void testSave() throws Exception {
        CelebrityResult celebrity = CelebrityApi.findCelebrity("1041404");
        CelebrityEntity entity = CelebrityEntityConvert.convert(celebrity);
        Key key = dao.save(entity);
        CelebrityEntity dbEntity = dao.findOneById(key.getId());
        System.out.println(entity);
        System.out.println(dbEntity);
        assertThat(entity.getID(), equalTo(dbEntity.getID()));
        assertThat(entity.getName(), equalTo(celebrity.getName()));
    }
}