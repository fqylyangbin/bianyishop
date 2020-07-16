package com.yangbin.service;

import com.yangbin.mapper.SkuMapper;
import com.yangbin.mapper.SpuDetailMapper;
import com.yangbin.mapper.SpuMapper;
import com.yangbin.mapper.StockMapper;
import com.yangbin.pojo.Sku;
import com.yangbin.pojo.SpuBo;
import com.yangbin.pojo.SpuDetail;
import com.yangbin.pojo.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/13/013
 * @Time 17:15
 */
@Service
@Transactional
public class GoodsService {
    @Autowired
    SpuMapper spuMapper;
    @Autowired
    SpuDetailMapper spuDetailMapper;
    @Autowired
    SkuMapper skuMapper;
    @Autowired
    StockMapper stockMapper;

    /**
     * 添加商品
     * @param spuBo
     */
    public void saveGoods(SpuBo spuBo) {
        //添加spu
          spuBo.setId(null);
          spuBo.setCreateTime(new Date());
          spuBo.setLastUpdateTime(spuBo.getCreateTime());
          spuBo.setValid(true);
          spuBo.setSaleable(true);
          this.spuMapper.insertSelective(spuBo);
          //添加描述
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        this.spuDetailMapper.insertSelective(spuDetail);
       //添加sku列表和库存
        this.saveSkusAndStock(spuBo);
    }

    /**
     * 添加sku和库存
     * @param spuBo
     */
    private void saveSkusAndStock(SpuBo spuBo){
        List<Sku> skus = spuBo.getSkus();
        skus.forEach(sku -> {
            sku.setId(null);
         sku.setCreateTime(new Date());
         sku.setLastUpdateTime(sku.getCreateTime());
         sku.setSpuId(spuBo.getId());
        this.skuMapper.insertSelective(sku);
           //新增库存
            Stock stock1 = new Stock();
            stock1.setSkuId(sku.getId());
            stock1.setStock(sku.getStock());
            this.stockMapper.insertSelective(stock1);
        });
    }

    public SpuDetail querySpuDetailBySpuId(Long spuId) {
            SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
            return spuDetail;
    }

    /**
     * 根据spu的id查出sku，（根据商品集的id查询属于该商品集的所有商品）
     * @param spuId
     * @return
     */
    public List<Sku> querySkusBySpuId(Long spuId) {
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skus = skuMapper.select(sku);
        //根据sku的id查询库存
        skus.forEach(sku1->{
            Stock stock = stockMapper.selectByPrimaryKey(sku1.getId());
            sku1.setStock(stock.getStock());
        });
        return skus;
    }

    /**
     * 修改商品
     * @param spuBo
     */
    public void updateGoods(SpuBo spuBo) {
        //删除商品sku和库存
        List<Sku> skus = spuBo.getSkus();
        //实现删除sku的库存
        skus.forEach(sku -> {
            this.stockMapper.deleteByPrimaryKey(sku.getId());
        });
        //删除sku
        Sku sku = new Sku();
        sku.setSpuId(spuBo.getId());
        this.skuMapper.delete(sku);

        //建立新的库存和sku
        this.saveSkusAndStock(spuBo);

        //更新spu
        spuBo.setLastUpdateTime(new Date());
        spuBo.setSaleable(null);
        spuBo.setValid(null);
        this.spuMapper.updateByPrimaryKeySelective(spuBo);
       //更新spu详情
        this.spuDetailMapper.updateByPrimaryKeySelective(spuBo.getSpuDetail());


    }
}
