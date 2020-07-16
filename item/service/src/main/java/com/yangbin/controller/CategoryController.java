package com.yangbin.controller;

import com.yangbin.pojo.Category;
import com.yangbin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/9/009
 * @Time 19:09
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(name="pid",defaultValue = "0") Long pid){
        if(pid==null||pid<0){
            //相当于响应400
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoriesByPid(pid);
        if(CollectionUtils.isEmpty(categories)){
            //响应404
            return ResponseEntity.notFound().build();
        }
        //数据正常响应
        return ResponseEntity.ok(categories);
    }

    /**
     * 根据分类id查找分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    public ResponseEntity<List<String>>  queryNamesByIds(@RequestParam("ids")List<Long> ids){
       List<String> names = this.categoryService.getCnameByCid(ids);
        return ResponseEntity.ok(names);
    }

}

