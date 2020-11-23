package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
   
}
