package edu.gdpi.blogserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.entity.Category;
import edu.gdpi.blogserver.entity.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
     * 给文章添加分类
     *
     * @param aid
     * @param cid
     */
    @Insert("INSERT INTO bind_article_category(article_id, category_id) VALUES(#{aid}, #{cid})")
    void addCategory(@Param("aid") Long aid, @Param("cid") Long cid);

    /**
     * 查询文章所有标签
     *
     * @param aid
     * @return
     */
    @Select("SELECT t.* FROM bind_article_tag b, tag t WHERE b.article_id = #{aid} AND t.id = b.tag_id")
    List<Tag> findTagsByArticleId(Long aid);

    /**
     * 查询文章所有类别
     *
     * @param aid
     * @return
     */
    @Select("SELECT c.* FROM bind_article_category b, category c WHERE b.article_id = #{aid} AND c.id = b.category_id")
    List<Category> findCategoryByArticleId(Long aid);
}
