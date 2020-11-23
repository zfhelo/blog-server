package edu.gdpi.blogserver.service;

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
     * 给文章添加标签
     *
     * @param aid 文章id
     * @param tid 标签id
     */
    void addTags(Long aid, List<Long> tid);

    /**
     * 给文章添加类别
     *
     * @param aid 文章id
     * @param cid 类别id
     */
    void addCategory(Long aid, List<Long> cid);
}
