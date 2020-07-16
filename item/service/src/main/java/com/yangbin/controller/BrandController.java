package com.yangbin.controller;

import com.yangbin.pojo.Brand;
import com.yangbin.pojo.PageResult;
import com.yangbin.service.BrandService;
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
 * @Date 2020/7/10/010
 * @Time 11:49
 */
@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsbyPage(
       @RequestParam(value = "key", required = false) String key,
       @RequestParam(value = "page", defaultValue = "1") Integer page,
       @RequestParam(value = "rows" ,defaultValue = "5") Integer rows,
       @RequestParam(value = "sortBy",required = false) String sortBy,
       @RequestParam(value = "desc", required = false) Boolean desc
    ){
        PageResult<Brand>  result=this.brandService.queryBrandsByPage(key,page,rows,sortBy,desc);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids){
        brandService.saveBrand(brand,cids);
        //201(已创建)请求成功并且服务器创建了新的资源
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

@GetMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> findBrandByCid(@PathVariable("cid") Long cid){
    List<Brand> brands = brandService.findBrandByCid(cid);
          return ResponseEntity.ok(brands);
}
@GetMapping("{id}")
    public ResponseEntity<Brand> queryBrandById(@PathVariable("id")Long id){
        Brand brand = this.brandService.queryBrandById(id);
        return ResponseEntity.ok(brand);
}
}
