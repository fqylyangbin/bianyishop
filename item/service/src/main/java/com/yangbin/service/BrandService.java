package com.yangbin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangbin.mapper.BrandMapper;
import com.yangbin.pojo.Brand;
import com.yangbin.pojo.PageResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/10/010
 * @Time 11:51
 */
@Service
@Transactional
public class BrandService {
    @Autowired
    BrandMapper brandMapper;

    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Brand.class);
        //创建criteria对象，添加模糊查询
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        //添加分页条件
        if(rows != -1){
            PageHelper.startPage(page,rows);
        }

        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc ? "desc" : "asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);
        //包装成pageinfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }


    public void saveBrand(Brand brand, List<Long> cids) {
        //先新增品牌
        brandMapper.insertSelective(brand);
        //在新增中间表
        cids.forEach(c->{
            brandMapper.insertCatagoryAndBrand(c,brand.getId());
        });
    }

    public List<Brand> findBrandByCid(Long cid) {
        return brandMapper.findBrandByCid(cid);
    }

    public Brand queryBrandById(Long id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }
}
