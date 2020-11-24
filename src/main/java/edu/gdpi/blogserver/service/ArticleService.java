package edu.gdpi.blogserver.service;


import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.entity.Article;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
public interface ArticleService {
    /**
     * 保存文章
     *
     * @param article
     */
    void save(Article article);

    /**
     * 更新文章
     *
     * @param aid 文章id
     * @param tid 标签id
     */
    void updateTags(Long aid, List<Long> tid);

    /**
     * 通过 id 查找文章
     *
     * @param id 文章id
     * @return 文章
     */
    Article findById(Long id);

    /**
     * 更新文章
     *
     * @param article
     */
    void update(Article article);

    /**
     * 分页查询
     *
     * @param page 当前页面
     * @param size 每页size
     * @return
     */
    PageInfo<Article> listPage(Integer page, Integer size);
}
