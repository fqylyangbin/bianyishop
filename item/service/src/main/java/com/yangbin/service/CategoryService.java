
package com.yangbin.service;

import com.yangbin.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.yangbin.mapper.CategoryMapper;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/9/009
 * @Time 19:02
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public List<String> getCnameByCid(List<Long> asList) {
        List<Category> categories = categoryMapper.selectByIdList(asList);
        List<String> cName = new ArrayList<>();
        categories.forEach(category -> {
            cName.add(category.getName());
        });
        return cName;
    }

    public List<Category> queryCategoriesByPid(Long pid){
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);
    }

}
