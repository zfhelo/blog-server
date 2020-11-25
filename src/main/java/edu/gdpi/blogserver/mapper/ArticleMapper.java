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
     * 查询文章所
     *
     * @return 所有文章
     */
    @Select("SELECT t.* FROM bind_article_tag b, tag t WHERE b.article_id = #{aid} AND t.id = b.tag_id")
    List<Tag> findTagsByArticleId(Long aid);

    /**
     * 查询所有文章
     *
     * @return
     */
    @Select(value = "SELECT * FROM article ORDER BY send_time DESC")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "tags", column = "id",
                    many = @Many(select = "edu.gdpi.blogserver.mapper.ArticleMapper.findTagsByArticleId")),
            @Result(property = "category", column = "category_id", one = @One(select = "edu.gdpi.blogserver.mapper.CategoryMapper.selectById"))
    })
    List<Article> findAll();
}
