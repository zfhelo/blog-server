package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Comment;
import edu.gdpi.blogserver.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 查询所有评论
     *
     * @return
     */
    @Select(value = "SELECT * FROM comment ORDER BY create_time DESC")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "edu.gdpi.blogserver.mapper.UserMapper.selectById"))
    })
    List<Comment> findAll();

    /**
     * 查询 文章下的所有评论
     *
     * @param id
     * @return
     */
    @Select(value = "SELECT * FROM comment WHERE article_id = #{id} ORDER BY create_time DESC")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "user", column = "user_id", one = @One(select = "edu.gdpi.blogserver.mapper.UserMapper.selectById")),
            @Result(property = "parentUser", column = "parent", one = @One(select = "edu.gdpi.blogserver.mapper.CommentMapper.findCommentUser"))
    })
    List<Comment> findByArticleId(Long id);

    /**
     * 通过评论id 查找用户
     *
     * @param commentId
     * @return
     */
    @Select("SELECT u.* FROM `comment` c, `user` u WHERE c.id = #{commentId} AND c.user_id = u.id")
    User findCommentUser(Long commentId);
}
