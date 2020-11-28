package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 查找所有标签
     *
     * @return
     */
    @Select("SELECT * FROM tag")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "id", property = "used", one = @One(select = "edu.gdpi.blogserver.mapper.TagMapper.findUsedCount"))
    })
    List<Tag> findAll();

    /**
     * 查找标签被多少篇文章使用
     *
     * @param tid
     * @return
     */
    @Select("SELECT count(tag_id) used FROM bind_article_tag WHERE tag_id = #{tid}")
    Long findUsedCount(Long tid);
}
