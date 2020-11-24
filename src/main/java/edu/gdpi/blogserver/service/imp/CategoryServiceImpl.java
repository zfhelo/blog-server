package edu.gdpi.blogserver.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.gdpi.blogserver.entity.Category;
import edu.gdpi.blogserver.mapper.CategoryMapper;
import edu.gdpi.blogserver.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> findAll() {
        return categoryMapper.selectList(null);
    }

    @Override
    public void save(Category category) {
        QueryWrapper<Category> query = new QueryWrapper<>();
        query.eq("name", category.getName());
        if (categoryMapper.selectOne(query) != null) {
            throw new RuntimeException(String.format("标签已存在 {}", category.getName()));
        }
        log.info("添加新分类 {}", category);
        categoryMapper.insert(category);
    }
}
