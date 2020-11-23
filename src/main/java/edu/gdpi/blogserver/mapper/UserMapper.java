package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}