package com.yangbin.api;

import com.yangbin.pojo.SpecParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/15/015
 * @Time 18:33
 */

public interface SpecificationApi {
    @GetMapping("/spec/params")
    public List<SpecParam> querySpecParamByGid(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching,
            @RequestParam(value = "numeric",required = false) Boolean numeric,
            @RequestParam(value = "generic",required = false) Boolean generic
    );
}
