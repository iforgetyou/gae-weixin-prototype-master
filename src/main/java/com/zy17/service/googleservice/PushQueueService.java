package com.zy17.service.googleservice;

import org.springframework.stereotype.Component;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
@Slf4j
@Component
public class PushQueueService {
    Queue queue = QueueFactory.getDefaultQueue();

    public Queue getQueue() {
        return queue;
    }
}
