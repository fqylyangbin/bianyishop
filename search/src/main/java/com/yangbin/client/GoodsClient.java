package com.yangbin.client;

import com.yangbin.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 17:22
 */
@FeignClient(value = "myservice")
public interface GoodsClient extends GoodsApi {
}
