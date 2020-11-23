package edu.gdpi.blogserver.service;

import edu.gdpi.blogserver.entity.Category;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
public interface CategoryService {
    /**
     * 查询所有分类
     *
     * @return
     */
    List<Category> findAll();

    /**
     * 创建新的分类
     *
     * @param category
     */
    void save(Category category);
}
