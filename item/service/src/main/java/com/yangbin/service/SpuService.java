package com.yangbin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangbin.mapper.BrandMapper;
import com.yangbin.mapper.SpuMapper;
import com.yangbin.pojo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/11/011
 * @Time 15:39
 */
@Service
public class SpuService {
    @Autowired
    SpuMapper spuMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandMapper brandMapper;


    public PageResult<SpuBo> queryAllSpuBoByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtils.isNotBlank(key)){
criteria.andLike("title","%"+key+"%");
criteria.orLike("subTitle","%"+key+"%");
        }
        if(saleable != null){
            if(saleable){
                criteria.andEqualTo("saleable",1);
            }else {
                criteria.andEqualTo("saleable",0);
            }
        }


        if(rows != -1){
            PageHelper.startPage(page,rows);
        }
        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo<Spu> pageInfo = new PageInfo<>(spus);
        List<SpuBo>  spuBos = new ArrayList<>();
        spus.forEach(spu -> {
            SpuBo spuBo = new SpuBo();
            //把spu的属性复制到spuBo
            BeanUtils.copyProperties(spu,spuBo);
            //根据cid查找种类名称
            List<String> cName = this.categoryService.getCnameByCid(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()));
           spuBo.setCname(cName.get(0)+"/"+cName.get(1)+"/"+cName.get(2));
            Brand brand = this.brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());
            spuBos.add(spuBo);
        });
return new PageResult<SpuBo>(pageInfo.getTotal(),spuBos);

    }


}
