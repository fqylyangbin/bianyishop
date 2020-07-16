package com.yangbin.controller;

import com.yangbin.pojo.PageResult;
import com.yangbin.pojo.SpuBo;
import com.yangbin.pojo.SpuDetail;
import com.yangbin.service.GoodsService;
import com.yangbin.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/11/011
 * @Time 15:34
 */

/**
 *
 */
@Controller
@RequestMapping("/spu")
public class SpuController {
    @Autowired
    SpuService spuService;
    @Autowired
    GoodsService goodsService;

    /**
     * 分页查询商品
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/page")
    public ResponseEntity<PageResult<SpuBo>> queryAllSpuBoByPage(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "saleable",required = false) Boolean saleable,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "rows",defaultValue = "5") Integer rows
    ){
        PageResult<SpuBo> spuBos = spuService.queryAllSpuBoByPage(key,saleable,page,rows);
          return ResponseEntity.ok(spuBos);

    }
    @RequestMapping("/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId){
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(spuId);

        return ResponseEntity.ok(spuDetail);
    }
}
