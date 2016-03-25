package com.zy17.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zy17.dao.ImageItemDao;
import com.zy17.entity.ImageItem;
import com.zy17.entity.UserStatistics;
import com.zy17.service.googleservice.CacheService;
import com.zy17.weixin.bean.message.EventMessage;
import com.zy17.weixin.bean.xmlmessage.XMLImageMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zy17 on 2016/3/16.
 */
@Slf4j
@Component
public class ServiceUtil {
    @Autowired
    ImageItemDao imageItemDao;
    @Autowired
    CacheService cacheUtil;

    /**
     * 获取随机图片
     *
     * @param msg
     *
     * @return
     */
    public XMLImageMessage genRandomImage(EventMessage msg) {

        List<Long> allId = imageItemDao.findAllAvaluableId();
        if (allId.size() == 0) {
            return null;
        }
        UserStatistics statistics = getStatistics(msg.getFromUserName());
        allId.removeAll(statistics.getHisIds());
        if (allId.size() == 0) {
            return null;
        }
        Random random = new Random();
        Long oneByRandomId = allId.get(random.nextInt(allId.size()));
        ImageItem randomResult = imageItemDao.findOneById(oneByRandomId);
        XMLImageMessage result =
                new XMLImageMessage(msg.getFromUserName(), msg.getToUserName(), randomResult.getMediaId());
        // 放缓存,已发图
        cacheUtil.getCache().put(msg.getFromUserName(), randomResult);
        log.debug("put cache his ids :{}", msg.getFromUserName() + randomResult.getID());
        return result;

    }

    public UserStatistics getStatistics(String fromUserName) {
        String statisticsKey = fromUserName + UserStatistics.class.getSimpleName();
        Object obj = cacheUtil.getCache().get(statisticsKey);
        UserStatistics statistics;
        if (obj == null) {
            statistics = new UserStatistics();
            statistics.setStatisticsKey(statisticsKey);
        } else {
            statistics = (UserStatistics) obj;
        }
        return statistics;
    }

    public void clearStatistics(String fromUserName) {
        String statisticsKey = fromUserName + UserStatistics.class.getSimpleName();
        cacheUtil.getCache().remove(statisticsKey);
    }

    public UserStatistics statistics(String fromUserName, boolean result, long id) {
        UserStatistics statistics = getStatistics(fromUserName);
        statistics.addGuessHis(id, result);
        // 统计结果
        if (result && !statistics.getHisIds().contains(id)) {
            // 答对的放入缓存
            statistics.getHisIds().add(id);
        }

        cacheUtil.getCache().put(statistics.getStatisticsKey(), statistics);
        return statistics;
    }

}
