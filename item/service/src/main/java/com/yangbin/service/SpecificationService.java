package com.yangbin.service;

import com.yangbin.mapper.SpecGroupMapper;
import com.yangbin.mapper.SpecParamMapper;
import com.yangbin.pojo.SpecGroup;
import com.yangbin.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/11/011
 * @Time 11:21
 */
@Service
public class SpecificationService {
    @Autowired
    SpecGroupMapper specGroupMapper;
    @Autowired
    SpecParamMapper specParamMapper;
    public List<SpecGroup> queryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> select = specGroupMapper.select(specGroup);
        return select;
    }

    public List<SpecParam> querySpecParamByGid(Long gid,Long cid,Boolean searching,Boolean numeric,Boolean generic) {
        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        specParam.setCid(cid);
        specParam.setSearching(searching);
        specParam.setGeneric(generic);
        specParam.setNumeric(numeric);
       return specParamMapper.select(specParam);
    }
}
