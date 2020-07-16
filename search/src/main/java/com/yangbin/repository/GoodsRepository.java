package com.yangbin.repository;

import com.yangbin.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:37
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
