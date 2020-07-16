package com.yangbin.client;

import com.yangbin.api.SpuApi;
import com.yangbin.pojo.PageResult;
import com.yangbin.pojo.Sku;
import com.yangbin.pojo.SpuBo;
import com.yangbin.pojo.SpuDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 17:10
 */
@FeignClient(value = "myservice")
public interface SpuClient extends SpuApi {

}