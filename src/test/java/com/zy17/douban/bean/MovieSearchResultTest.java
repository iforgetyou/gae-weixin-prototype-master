package com.zy17.douban.bean;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.zy17.weixin.bean.xmlmessage.XMLNewsMessage;

/**
 * Created by zhangyan53 on 2016/3/17.
 */
public class MovieSearchResultTest {

    @Test
    public void jsonTest() {
        XMLNewsMessage xmlNewsMessage = MovieSearchResultTest.genXMLNewsMessage();
        assertNotNull(xmlNewsMessage);


    }

    public static XMLNewsMessage genXMLNewsMessage() {
        MovieSearchResult movieSearchResult = JSON.parseObject(json, MovieSearchResult.class);
        System.out.println(movieSearchResult);


        ArrayList<XMLNewsMessage.Article> articles = new ArrayList<>();
        for (SimpleSubject simpleSubject : movieSearchResult.getSubjects()) {
            articles.add(new XMLNewsMessage.Article(simpleSubject.getTitle(), simpleSubject.getOriginal_title(),
                    simpleSubject.getAlt(),
                    simpleSubject.getImages().get("")));
        }
        return new XMLNewsMessage("fromUser", "toUser", articles);
    }


    public static String json = "{\n"
            + "  \"count\": 5,\n"
            + "  \"start\": 0,\n"
            + "  \"total\": 43,\n"
            + "  \"subjects\": [\n"
            + "    {\n"
            + "      \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 9.2,\n"
            + "        \"stars\": \"50\",\n"
            + "        \"min\": 0\n"
            + "      },\n"
            + "      \"genres\": [\n"
            + "        \"剧情\",\n"
            + "        \"古装\"\n"
            + "      ],\n"
            + "      \"title\": \"琅琊榜\",\n"
            + "      \"casts\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1274477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1355402981.88.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1355402981.88.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1355402981.88.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"胡歌\",\n"
            + "          \"id\": \"1274477\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1011562/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1415689928.93.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1415689928.93.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1415689928.93.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"刘涛\",\n"
            + "          \"id\": \"1011562\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1314544/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1444998211.72.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1444998211.72.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1444998211.72.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"王凯\",\n"
            + "          \"id\": \"1314544\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"collect_count\": 132460,\n"
            + "      \"original_title\": \"琅琊榜\",\n"
            + "      \"subtype\": \"tv\",\n"
            + "      \"directors\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1314760/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/26279.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/26279.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/26279.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"孔笙\",\n"
            + "          \"id\": \"1314760\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1329012/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1371198624.54.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1371198624.54.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1371198624.54.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"李雪\",\n"
            + "          \"id\": \"1329012\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"year\": \"2015\",\n"
            + "      \"images\": {\n"
            + "        \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2271982968.jpg\",\n"
            + "        \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2271982968.jpg\",\n"
            + "        \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2271982968.jpg\"\n"
            + "      },\n"
            + "      \"alt\": \"http://movie.douban.com/subject/25754848/\",\n"
            + "      \"id\": \"25754848\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 8.3,\n"
            + "        \"stars\": \"45\",\n"
            + "        \"min\": 0\n"
            + "      },\n"
            + "      \"genres\": [\n"
            + "        \"剧情\",\n"
            + "        \"悬疑\"\n"
            + "      ],\n"
            + "      \"title\": \"伪装者\",\n"
            + "      \"casts\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1274477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1355402981.88.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1355402981.88.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1355402981.88.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"胡歌\",\n"
            + "          \"id\": \"1274477\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1314123/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img3.doubanio.com/img/celebrity/small/1442385961.61.jpg\",\n"
            + "            \"large\": \"https://img3.doubanio.com/img/celebrity/large/1442385961.61.jpg\",\n"
            + "            \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1442385961.61.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"靳东\",\n"
            + "          \"id\": \"1314123\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1318610/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/43969.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/43969.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/43969.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"刘敏涛\",\n"
            + "          \"id\": \"1318610\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"collect_count\": 45408,\n"
            + "      \"original_title\": \"伪装者\",\n"
            + "      \"subtype\": \"tv\",\n"
            + "      \"directors\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1329012/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1371198624.54.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1371198624.54.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1371198624.54.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"李雪\",\n"
            + "          \"id\": \"1329012\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"year\": \"2015\",\n"
            + "      \"images\": {\n"
            + "        \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2221539583.jpg\",\n"
            + "        \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2221539583.jpg\",\n"
            + "        \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2221539583.jpg\"\n"
            + "      },\n"
            + "      \"alt\": \"http://movie.douban.com/subject/25994712/\",\n"
            + "      \"id\": \"25994712\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 8.1,\n"
            + "        \"stars\": \"40\",\n"
            + "        \"min\": 0\n"
            + "      },\n"
            + "      \"genres\": [\n"
            + "        \"爱情\",\n"
            + "        \"冒险\"\n"
            + "      ],\n"
            + "      \"title\": \"仙剑奇侠传\",\n"
            + "      \"casts\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1274477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1355402981.88.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1355402981.88.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1355402981.88.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"胡歌\",\n"
            + "          \"id\": \"1274477\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1049732/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img3.doubanio.com/img/celebrity/small/38640.jpg\",\n"
            + "            \"large\": \"https://img3.doubanio.com/img/celebrity/large/38640.jpg\",\n"
            + "            \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/38640.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"刘亦菲\",\n"
            + "          \"id\": \"1049732\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1275280/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img3.doubanio.com/img/celebrity/small/11546.jpg\",\n"
            + "            \"large\": \"https://img3.doubanio.com/img/celebrity/large/11546.jpg\",\n"
            + "            \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/11546.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"安以轩\",\n"
            + "          \"id\": \"1275280\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"collect_count\": 51408,\n"
            + "      \"original_title\": \"仙剑奇侠传\",\n"
            + "      \"subtype\": \"tv\",\n"
            + "      \"directors\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1318119/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/42287.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/42287.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/42287.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"吴锦源\",\n"
            + "          \"id\": \"1318119\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1333706/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img3.doubanio.com/img/celebrity/small/1435824210.91.jpg\",\n"
            + "            \"large\": \"https://img3.doubanio.com/img/celebrity/large/1435824210.91.jpg\",\n"
            + "            \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1435824210.91.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"梁胜权\",\n"
            + "          \"id\": \"1333706\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1323355/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio"
            + ".com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png\",\n"
            + "            \"large\": \"https://img3.doubanio"
            + ".com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png\",\n"
            + "            \"medium\": \"https://img1.doubanio"
            + ".com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png\"\n"
            + "          },\n"
            + "          \"name\": \"麦贯之\",\n"
            + "          \"id\": \"1323355\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": null,\n"
            + "          \"avatars\": null,\n"
            + "          \"name\": \"于建福\",\n"
            + "          \"id\": null\n"
            + "        }\n"
            + "      ],\n"
            + "      \"year\": \"2005\",\n"
            + "      \"images\": {\n"
            + "        \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2270330339.jpg\",\n"
            + "        \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2270330339.jpg\",\n"
            + "        \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2270330339.jpg\"\n"
            + "      },\n"
            + "      \"alt\": \"http://movie.douban.com/subject/2210031/\",\n"
            + "      \"id\": \"2210031\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 0,\n"
            + "        \"stars\": \"00\",\n"
            + "        \"min\": 0\n"
            + "      },\n"
            + "      \"genres\": [\n"
            + "        \"剧情\",\n"
            + "        \"爱情\"\n"
            + "      ],\n"
            + "      \"title\": \"猎场\",\n"
            + "      \"casts\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1274477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1355402981.88.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1355402981.88.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1355402981.88.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"胡歌\",\n"
            + "          \"id\": \"1274477\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1328420/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img3.doubanio.com/img/celebrity/small/1400726587.81.jpg\",\n"
            + "            \"large\": \"https://img3.doubanio.com/img/celebrity/large/1400726587.81.jpg\",\n"
            + "            \"medium\": \"https://img3.doubanio.com/img/celebrity/medium/1400726587.81.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"菅韧姿\",\n"
            + "          \"id\": \"1328420\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1315477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1409894362.63.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1409894362.63.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1409894362.63.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"万茜\",\n"
            + "          \"id\": \"1315477\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"collect_count\": 88,\n"
            + "      \"original_title\": \"猎场\",\n"
            + "      \"subtype\": \"tv\",\n"
            + "      \"directors\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1314065/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/21619.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/21619.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/21619.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"姜伟\",\n"
            + "          \"id\": \"1314065\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"year\": \"2015\",\n"
            + "      \"images\": {\n"
            + "        \"small\": \"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p2249002177.jpg\",\n"
            + "        \"large\": \"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p2249002177.jpg\",\n"
            + "        \"medium\": \"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p2249002177.jpg\"\n"
            + "      },\n"
            + "      \"alt\": \"http://movie.douban.com/subject/26322642/\",\n"
            + "      \"id\": \"26322642\"\n"
            + "    },\n"
            + "    {\n"
            + "      \"rating\": {\n"
            + "        \"max\": 10,\n"
            + "        \"average\": 5.7,\n"
            + "        \"stars\": \"30\",\n"
            + "        \"min\": 0\n"
            + "      },\n"
            + "      \"genres\": [\n"
            + "        \"剧情\",\n"
            + "        \"爱情\"\n"
            + "      ],\n"
            + "      \"title\": \"大好时光\",\n"
            + "      \"casts\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1274477/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1355402981.88.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1355402981.88.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1355402981.88.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"胡歌\",\n"
            + "          \"id\": \"1274477\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1316344/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/1406948986.47.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/1406948986.47.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/1406948986.47.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"王晓晨\",\n"
            + "          \"id\": \"1316344\"\n"
            + "        },\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1275152/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio.com/img/celebrity/small/9932.jpg\",\n"
            + "            \"large\": \"https://img1.doubanio.com/img/celebrity/large/9932.jpg\",\n"
            + "            \"medium\": \"https://img1.doubanio.com/img/celebrity/medium/9932.jpg\"\n"
            + "          },\n"
            + "          \"name\": \"周楚楚\",\n"
            + "          \"id\": \"1275152\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"collect_count\": 8385,\n"
            + "      \"original_title\": \"大好时光\",\n"
            + "      \"subtype\": \"tv\",\n"
            + "      \"directors\": [\n"
            + "        {\n"
            + "          \"alt\": \"http://movie.douban.com/celebrity/1324843/\",\n"
            + "          \"avatars\": {\n"
            + "            \"small\": \"https://img1.doubanio"
            + ".com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png\",\n"
            + "            \"large\": \"https://img3.doubanio"
            + ".com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png\",\n"
            + "            \"medium\": \"https://img1.doubanio"
            + ".com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png\"\n"
            + "          },\n"
            + "          \"name\": \"夏晓昀\",\n"
            + "          \"id\": \"1324843\"\n"
            + "        }\n"
            + "      ],\n"
            + "      \"year\": \"2015\",\n"
            + "      \"images\": {\n"
            + "        \"small\": \"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2247752561.jpg\",\n"
            + "        \"large\": \"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2247752561.jpg\",\n"
            + "        \"medium\": \"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2247752561.jpg\"\n"
            + "      },\n"
            + "      \"alt\": \"http://movie.douban.com/subject/26322641/\",\n"
            + "      \"id\": \"26322641\"\n"
            + "    }\n"
            + "  ],\n"
            + "  \"title\": \"搜索 \\\"胡歌\\\" 的结果\"\n"
            + "}";


}