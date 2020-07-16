package com.yangbin.api;

import com.yangbin.pojo.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:19
 */

public interface BrandApi {
    @GetMapping("/brand/{id}")
    public Brand queryBrandById(@PathVariable("id")Long id);
}
