package com.yangbin.mapper;

import com.yangbin.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/9/009
 * @Time 18:57
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
}
