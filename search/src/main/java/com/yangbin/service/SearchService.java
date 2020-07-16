package com.yangbin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangbin.client.*;
import com.yangbin.pojo.*;
import com.yangbin.repository.GoodsRepository;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 19:19
 * 导入数据
 * 导入数据其实就是查询数据，然后把查询到的Spu转变为Goods来保存
 */
@Service
public class SearchService {
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private SpuClient spuClient;
    @Autowired
    SpecificationClient specificationClient;
    @Autowired
    private GoodsRepository goodsRepository;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public Goods buildGoods(Spu spu)throws IOException{
        //创建Goods对象
        Goods goods = new Goods();
        //查询品牌 根据spu的中品牌id查询品牌信息
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());

        //查询分类名称，根据spu中各cid1,cid2,cid3来查出其分类名称
List<String> cName = this.categoryClient.queryNamesByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()));
        //查询spu下所有sku
       List<Sku> skus = this.goodsClient.querySkusBySpuId(spu.getId());

List<Double> prices = new ArrayList<>();
   List<Map<String,Object>> skuMapList = new ArrayList<>();
   //遍历skus，获取价格，和其他信息
skus.forEach(sku -> {
    prices.add(sku.getPrice());
    Map<String,Object> skuMap = new HashMap<>();
    skuMap.put("id",sku.getId());
    skuMap.put("title",sku.getTitle());
    skuMap.put("price",sku.getPrice());
    skuMap.put("image", StringUtils.isNotBlank(sku.getImages())?StringUtils.split(sku.getImages(),",")[0]:"null");
    skuMapList.add(skuMap);
});
//查询出所有的搜索规格参数
        List<SpecParam> specParams = this.specificationClient.querySpecParamByGid(null, spu.getCid3(), true, null, null);
       //查询spuDetail.获取规格参数值
        SpuDetail spuDetail = this.spuClient.querySpuDetailBySpuId(spu.getId());
        //获取通用的规格参数
        //把json格式的字符串转成指定对象，简单的用Bean.class，复杂的用TypeReference
 Map<Long,Object> genericSpecMap = MAPPER.readValue(spuDetail.getGenericSpec(),new TypeReference<Map<Long,Object>>(){});
//获取特殊的规格参数
        Map<Long, List<Object>>  specialSpecMap= MAPPER.readValue(spuDetail.getSpecialSpec(), new TypeReference<Map<Long, List<Object>>>() {
        });
     //定义map接收{规格参数名,规格参数值}
     Map<String,Object> paramMap = new HashMap<>();
     //遍历所有的搜索规格参数
     specParams.forEach(specParam -> {
         //判断是否是通用规格参数
         if(specParam.getGeneric()){
             //获取通用规格参数值
             String value = (String)genericSpecMap.get(specParam.getId().toString());
             if(specParam.getNumeric()){
                 //如果是数值，判断区间
                 value = chooseSement(value,specParam);
             }
             //把参数名和值放入结果集
             paramMap.put(specParam.getName(),value);
         }else {//是特殊值
             paramMap.put(specParam.getName(),specialSpecMap.get(specParam.getId().toString()));
         }

     });
     //设置参数
        goods.setId(spu.getId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setBrandId(spu.getBrandId());
        goods.setCreateTime(spu.getCreateTime());
        goods.setSubTitle(spu.getSubTitle());
        goods.setAll(spu.getTitle()+brand.getName()+StringUtils.join(cName," "));
        goods.setPrice(prices);
        goods.setSkus(MAPPER.writeValueAsString(skuMapList));
        goods.setSpecs(paramMap);
        return goods;
    }

    private String chooseSement(String value, SpecParam specParam) {
        double val = NumberUtils.toDouble(value);
        String result = "其他";
        //保存数据段
        for (String s : specParam.getSegments().split(",")) {
            String[] segs = s.split("-");
            //获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if(segs.length == 2){
                end = NumberUtils.toDouble(segs[1]);
            }
            //判断是否在范围内
            if(val >= begin && val < end){
                if(segs.length == 1){
                    //1000 元 以上
                    result = segs[0] + specParam.getUnit() +"以上";


                }else if(begin == 0){// 0 到 200 元
                    result = segs[1] = specParam.getUnit() + "以下";
                }else {
                    return segs + specParam.getUnit();
                }
                break;
            }
        }
        return result;
    }
}
