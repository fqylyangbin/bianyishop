package com.yangbin.api;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:32
 */

public interface CategoryApi {
    @GetMapping("/category/names")
    public List<String> queryNamesByIds(@RequestParam("ids")List<Long> ids);
}
