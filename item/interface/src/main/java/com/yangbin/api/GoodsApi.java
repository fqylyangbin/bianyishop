package com.yangbin.api;

import com.yangbin.pojo.Sku;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:30
 */
public interface GoodsApi {
    @GetMapping("/sku/list")
    public List<Sku>querySkusBySpuId(@RequestParam("id") Long spuId);
}
