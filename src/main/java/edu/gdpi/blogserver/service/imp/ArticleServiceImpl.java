package edu.gdpi.blogserver.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.entity.Tag;
import edu.gdpi.blogserver.mapper.ArticleMapper;
import edu.gdpi.blogserver.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhengHaiFeng
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;


    @Override
    public void save(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void updateTags(Long aid, List<Long> tid) {
        // 查询文章的所有标签
        List<Long> old = articleMapper.findTagsByArticleId(aid)
                .stream()
                .map(Tag::getId).collect(Collectors.toList());

        ArrayList<Object> t = new ArrayList<>(old);
        old.removeAll(tid);
        // 删除标签
        old.forEach(id -> {
            log.info("文章id {} 删除标签id {}", aid, id);
            articleMapper.deleteTag(aid, id);
        });

        tid.removeAll(t);
        // 添加标签
        tid.forEach(id -> {
            log.info("为文章id {} 添加标签id {}", aid, id);
            articleMapper.addTag(aid, id);
        });

    }

    @Override
    public Article findById(Long id) {
        log.info("查询文章 {}", id);
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new RuntimeException("没有该文章 {" + id + "}");
        }
        List<Tag> tags = articleMapper.findTagsByArticleId(id);
        article.setTags(tags);
        return article;
    }

    @Override
    public void update(Article article) {
        log.info("更新文章 {} ", article.getId());
        articleMapper.updateById(article);
    }

    @Override
    public PageInfo<Article> listPage(Integer page, Integer size) {
        return PageHelper.startPage(page, size).doSelectPageInfo(() -> articleMapper.findAll());
    }
}
