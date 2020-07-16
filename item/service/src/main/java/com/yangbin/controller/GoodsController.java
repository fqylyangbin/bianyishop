package com.yangbin.controller;

import com.yangbin.pojo.Sku;
import com.yangbin.pojo.SpuBo;
import com.yangbin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/13/013
 * @Time 17:04
 */
@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    /**
     * 添加商品
     * @param spuBo
     * @return
     */
    @PostMapping("/goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo){
        goodsService.saveGoods(spuBo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 通过spu的id查找sku
     * @param spuId
     * @return
     */
    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id") Long spuId){
       List<Sku> skus = goodsService. querySkusBySpuId(spuId);
       return ResponseEntity.ok(skus);
    }

    /**
     * 修改商品
     * @param spuBo
     * @return
     */
    @PutMapping("/goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
          goodsService.updateGoods(spuBo);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
