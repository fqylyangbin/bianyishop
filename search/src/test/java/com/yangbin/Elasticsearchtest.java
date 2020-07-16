package com.yangbin;

import com.yangbin.client.GoodsClient;
import com.yangbin.client.SpuClient;
import com.yangbin.pojo.Goods;
import com.yangbin.pojo.PageResult;
import com.yangbin.pojo.Spu;
import com.yangbin.pojo.SpuBo;
import com.yangbin.repository.GoodsRepository;
import com.yangbin.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)

public class Elasticsearchtest {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    GoodsClient goodsClient;
    @Autowired
    SpuClient spuClient;
    @Autowired
    SearchService searchService;

    @Test
    public void deleteIndex(){
       elasticsearchTemplate.deleteIndex(Goods.class);
    }


    @Test
    public void createIndex(){
       /* elasticsearchTemplate.deleteIndex(Goods.class);*/
        elasticsearchTemplate.createIndex(Goods.class);
        elasticsearchTemplate.putMapping(Goods.class);
        Integer page = 1;
        Integer rows = 100;
        do{
          //分批查询spubo
            PageResult<SpuBo> spuBoPageResult = this.spuClient.queryAllSpuBoByPage(null, true, page, rows);
            //遍历spubo集合转List<Goods>
            //
            spuBoPageResult.getItems().forEach(spuBo -> {

            });
            List<Goods> collect = spuBoPageResult.getItems().stream().map(spuBo -> {
                try {
                    return this.searchService.buildGoods((Spu) spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            this.goodsRepository.saveAll(collect);
            //获取当前页数据条数，如果是最后一页，没有100条
            rows = spuBoPageResult.getItems().size();
            //每次循环页码+1
            page ++;
        }while (rows == 100);
    }


@Test
    public void test(){
    /*    String s = "http://image.leyou.com/images/9/15/1524297313793.jpg";
    String[] split = StringUtils.split(s, ",");
    System.out.println(split[0]);
    List<String>  list = new ArrayList<>();
    list.add("111");
    list.add("222");
    String join = StringUtils.join(list, " ");
    System.out.println(join);*/
    String ss = "aaabbbcccdddbbbb";
    System.out.println(ss.lastIndexOf("bbb"));
    System.out.println(ss.substring(2,6));
    System.out.println(ss.replace("f","e"));
}



}
