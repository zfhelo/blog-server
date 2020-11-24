package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 给文章添加标签
     *
     * @param aid
     * @param tid
     */
    @Insert("INSERT INTO bind_article_tag(article_id, tag_id) VALUES(#{aid}, #{tid})")
    void addTag(@Param("aid") Long aid, @Param("tid") Long tid);

    /**
     * 删除文章标签
     *
     * @param aid 文章id
     * @param tid 标签id
     */
    @Delete("DELETE FROM bind_article_tag WHERE article_id = #{aid} AND tag_id = #{tid}")
    void deleteTag(@Param("aid") Long aid, @Param("tid") Long tid);


    /**
     * 查询文章所有标签
     *
     * @param aid
     * @return
     */
    @Select("SELECT t.* FROM bind_article_tag b, tag t WHERE b.article_id = #{aid} AND t.id = b.tag_id")
    List<Tag> findTagsByArticleId(Long aid);

    @Select(value = "SELECT * FROM article")
    @Results({
            @Result(property = "tags", column = "id",
                    many = @Many(select = "edu.gdpi.blogserver.mapper.ArticleMapper.findTagsByArticleId"))
    })
    List<Article> findAll();
}
