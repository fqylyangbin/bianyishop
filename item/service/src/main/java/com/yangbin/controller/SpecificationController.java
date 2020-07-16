package com.yangbin.controller;

import com.yangbin.pojo.SpecGroup;
import com.yangbin.pojo.SpecParam;
import com.yangbin.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/11/011
 * @Time 11:19
 */
@Controller
@RequestMapping("/spec")
public class SpecificationController {
    @Autowired
    SpecificationService specificationService;
@GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid")Long cid){
   List<SpecGroup> specGroups = specificationService.queryGroupByCid(cid);
   if(CollectionUtils.isEmpty(specGroups)){
       return ResponseEntity.notFound().build();
   }
    return ResponseEntity.ok(specGroups);
}
@GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParamByGid(
            @RequestParam(value = "gid",required = false) Long gid,
            @RequestParam(value = "cid",required = false) Long cid,
            @RequestParam(value = "searching",required = false) Boolean searching,
            @RequestParam(value = "numeric",required = false) Boolean numeric,
            @RequestParam(value = "generic",required = false) Boolean generic
){
    List<SpecParam> specParams = specificationService.querySpecParamByGid(gid,cid,searching,numeric,generic);
    if (CollectionUtils.isEmpty(specParams)) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(specParams);


}
}
