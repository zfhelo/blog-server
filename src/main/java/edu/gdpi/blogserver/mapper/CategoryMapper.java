package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
