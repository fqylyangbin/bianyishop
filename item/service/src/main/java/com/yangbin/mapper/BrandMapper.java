package com.yangbin.mapper;

import com.yangbin.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/10/010
 * @Time 11:43
 */
public interface BrandMapper extends Mapper<Brand> {
   @Insert("insert into tb_category_brand values(#{cid},#{bid})")
    void insertCatagoryAndBrand(@Param(value = "cid") Long c,@Param(value = "bid") Long id);



    @Select("select * from tb_brand where id IN\n" +
           "(\n" +
           "select brand_id from  tb_category_brand where category_id = #{cid})")
    List<Brand> findBrandByCid(Long cid);
}
