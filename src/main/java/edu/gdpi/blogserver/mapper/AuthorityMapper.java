package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
    /**
     * 通过用户id查询该用户的所有权限
     *
     * @param uid 用户id
     * @return
     */
    @Select("SELECT a.* FROM authority a, bind_user_authority b WHERE b.user_id = #{uid} and b.authority_id = a.id")
    List<Authority> findByUserId(Long uid);
}
