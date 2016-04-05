package com.zy17.douban.bean;

import static org.junit.Assert.*;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
 * Created by zhangyan53 on 2016/3/18.
 */
public class BoxSubjectTest {
    @Test
    public void jsonTest() {
        BoxSubject boxSubject = JSON.parseObject(json, BoxSubject.class);
    }

    public String json = "{\n"
            + "    \"box\": 50000000,\n"
            + "    \"new\": false,\n"
            + "    \"rank\": 1,\n"
            + "    \"subject\": {\n"
            + "        \"rating\": {\n"
            + "            \"max\": 10,\n"
            + "            \"average\": 9.3,\n"
            + "            \"stars\": \"50\",\n"
            + "            \"min\": 0\n"
            + "        },\n"
            + "        \"genres\": [\n"
            + "            \"喜剧\",\n"
            + "            \"动作\",\n"
            + "            \"动画\"\n"
            + "        ],\n"
            + "        \"title\": \"疯狂动物城\",\n"
            + "        \"casts\": [\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1017930/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img3.doubanio.com/img/celebrity/small/4815.jpg\",\n"
            + "                    \"large\": \"https://img3.doubanio.com/img/celebrity/large/4815.jpg\",\n"
            + "                    \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/4815.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"金妮弗·古德温\",\n"
            + "                \"id\": \"1017930\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1013760/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img1.doubanio.com/img/celebrity/small/18772.jpg\",\n"
            + "                    \"large\": \"https://img1.doubanio.com/img/celebrity/large/18772.jpg\",\n"
            + "                    \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/18772.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"杰森·贝特曼\",\n"
            + "                \"id\": \"1013760\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1049501/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img3.doubanio.com/img/celebrity/small/1410696326.11.jpg\",\n"
            + "                    \"large\": \"https://img3.doubanio.com/img/celebrity/large/1410696326.11.jpg\",\n"
            + "                    \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1410696326.11.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"伊德里斯·艾尔巴\",\n"
            + "                \"id\": \"1049501\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"collect_count\": 205388,\n"
            + "        \"original_title\": \"Zootopia\",\n"
            + "        \"subtype\": \"movie\",\n"
            + "        \"directors\": [\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1286985/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img1.doubanio.com/img/celebrity/small/1457505519.94.jpg\",\n"
            + "                    \"large\": \"https://img1.doubanio.com/img/celebrity/large/1457505519.94.jpg\",\n"
            + "                    \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1457505519.94.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"拜伦·霍华德\",\n"
            + "                \"id\": \"1286985\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1324037/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img1.doubanio.com/img/celebrity/small/1456810684.78.jpg\",\n"
            + "                    \"large\": \"https://img1.doubanio.com/img/celebrity/large/1456810684.78.jpg\",\n"
            + "                    \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1456810684.78.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"瑞奇·摩尔\",\n"
            + "                \"id\": \"1324037\"\n"
            + "            },\n"
            + "            {\n"
            + "                \"alt\": \"http://movie.douban.com/celebrity/1304069/\",\n"
            + "                \"avatars\": {\n"
            + "                    \"small\": \"https://img3.doubanio.com/img/celebrity/small/1456810614.66.jpg\",\n"
            + "                    \"large\": \"https://img3.doubanio.com/img/celebrity/large/1456810614.66.jpg\",\n"
            + "                    \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1456810614.66.jpg\"\n"
            + "                },\n"
            + "                \"name\": \"杰拉德·布什\",\n"
            + "                \"id\": \"1304069\"\n"
            + "            }\n"
            + "        ],\n"
            + "        \"year\": \"2016\",\n"
            + "        \"images\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2315672647"
            + ".jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2315672647"
            + ".jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2315672647"
            + ".jpg\"\n"
            + "        },\n"
            + "        \"alt\": \"http://movie.douban.com/subject/25662329/\",\n"
            + "        \"id\": \"25662329\"\n"
            + "    }\n"
            + "}";
}