package com.zy17.douban.bean;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class SimpleSubjectTest {
    @Test
    public void jsonTest() {
        JSON.parseObject(json, SimpleSubject.class);
    }

    public String json = "{\n"
            + "    \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 7.7,\n"
            + "        \"stars\": \"40\",\n"
            + "        \"min\": 0\n"
            + "    },\n"
            + "    \"genres\": [\n"
            + "        \"动作\",\n"
            + "        \"悬疑\",\n"
            + "        \"科幻\"\n"
            + "    ],\n"
            + "    \"title\": \"科洛弗道10号\",\n"
            + "    \"casts\": [\n"
            + "        {\n"
            + "            \"alt\": \"http://movie.douban.com/celebrity/1017898/\",\n"
            + "            \"avatars\": {\n"
            + "                \"small\": \"https://img3.doubanio.com/img/celebrity/small/1360912229.81.jpg\",\n"
            + "                \"large\": \"https://img3.doubanio.com/img/celebrity/large/1360912229.81.jpg\",\n"
            + "                \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1360912229.81.jpg\"\n"
            + "            },\n"
            + "            \"name\": \"约翰·古德曼\",\n"
            + "            \"id\": \"1017898\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"alt\": \"http://movie.douban.com/celebrity/1041018/\",\n"
            + "            \"avatars\": {\n"
            + "                \"small\": \"https://img1.doubanio.com/img/celebrity/small/32684.jpg\",\n"
            + "                \"large\": \"https://img1.doubanio.com/img/celebrity/large/32684.jpg\",\n"
            + "                \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/32684.jpg\"\n"
            + "            },\n"
            + "            \"name\": \"玛丽·伊丽莎白·温斯特德\",\n"
            + "            \"id\": \"1041018\"\n"
            + "        },\n"
            + "        {\n"
            + "            \"alt\": \"http://movie.douban.com/celebrity/1018165/\",\n"
            + "            \"avatars\": {\n"
            + "                \"small\": \"https://img1.doubanio.com/img/celebrity/small/50687.jpg\",\n"
            + "                \"large\": \"https://img1.doubanio.com/img/celebrity/large/50687.jpg\",\n"
            + "                \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/50687.jpg\"\n"
            + "            },\n"
            + "            \"name\": \"小约翰·加拉赫\",\n"
            + "            \"id\": \"1018165\"\n"
            + "        }\n"
            + "    ],\n"
            + "    \"collect_count\": 533,\n"
            + "    \"original_title\": \"10 Cloverfield Lane\",\n"
            + "    \"subtype\": \"movie\",\n"
            + "    \"directors\": [\n"
            + "        {\n"
            + "            \"alt\": \"http://movie.douban.com/celebrity/1354103/\",\n"
            + "            \"avatars\": {\n"
            + "                \"small\": \"https://img1.doubanio.com/img/celebrity/small/1458023591.47.jpg\",\n"
            + "                \"large\": \"https://img1.doubanio.com/img/celebrity/large/1458023591.47.jpg\",\n"
            + "                \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1458023591.47.jpg\"\n"
            + "            },\n"
            + "            \"name\": \"丹·特拉亨伯格\",\n"
            + "            \"id\": \"1354103\"\n"
            + "        }\n"
            + "    ],\n"
            + "    \"year\": \"2016\",\n"
            + "    \"images\": {\n"
            + "        \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2310097289.jpg\",\n"
            + "        \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2310097289.jpg\",\n"
            + "        \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2310097289.jpg\"\n"
            + "    },\n"
            + "    \"alt\": \"http://movie.douban.com/subject/2977957/\",\n"
            + "    \"id\": \"2977957\"\n"
            + "}";
}