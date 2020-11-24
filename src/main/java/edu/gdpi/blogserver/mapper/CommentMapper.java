package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
