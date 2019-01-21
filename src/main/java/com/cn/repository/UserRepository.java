package com.cn.repository;
import com.cn.bean.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,Integer> {
}
