package com.cn;

import com.cn.bean.User;
import com.cn.repository.UserRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticDemoApplicationTests {


        @Autowired
        JestClient jestClient;

        @Autowired
        UserRepository userRepository;

        @Autowired
        Client client;

        @Test
        public void contextLoads() {

            User user=new User();
            user.setId(4);
            user.setEmail("2365958888@qq.com");
            user.setUserName("张三");

            //构建一个索引功能
            Index index = new Index.Builder(user).index("zcb").type("user").build();
            try {
                jestClient.execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Test
        public void search_user(){
            String json = "{\n" +
                    "\t\"userName\":\"张三\",\n" +
                    "\t\"email\":\"2365958888@qq.com\"\n" +
                    "}";
            Search search=new Search.Builder(json).addIndex("zcb").addType("user").build();
            try {
                SearchResult searchResult=jestClient.execute(search);
                System.out.println(searchResult.getJsonString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        @Test
        public void test03(){
            User user=new User();
            user.setUserName("张昌北");
            user.setEmail("2365956483@qq.com");
            user.setId(3);
            userRepository.index(user);
        }

        @Test
        public void es03(){
            System.out.println("hello");
        }

        @Test
        public void test04(){
         Iterable<User> list=  userRepository.findAll();
         list.forEach(user ->
                 changeName(user.getUserName())
                 );
        }

    private void changeName(String userName) {
        System.out.println(userName);
    }


    @Test
    public void test05(){
            User user = new User();
            user.setEmail("88798549875434@foxmail.com");
            user.setUserName("杨威");
            user.setId(4);
            userRepository.save(user);
    }

    @Test
    public void test06(){
            String index = "zcb";
            String type = "user";

        SearchRequestBuilder searchRequestBuilder =
                client.
                        prepareSearch(index).
                        setSearchType(type).
                        setQuery(QueryBuilders.matchAllQuery()).
                        setQuery(QueryBuilders.boolQuery().
                                must(QueryBuilders.matchQuery("userName", "张昌北")));

        System.out.println(searchRequestBuilder);



    }

}

