package edu.gdpi.blogserver.service.imp;

import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.entity.Category;
import edu.gdpi.blogserver.entity.Tag;
import edu.gdpi.blogserver.mapper.ArticleMapper;
import edu.gdpi.blogserver.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhengHaiFeng
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;


    @Override
    public void save(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void addTags(Long aid, List<Long> tid) {
        // 查询文章的所有标签
        List<Long> collect = articleMapper.findTagsByArticleId(aid)
                .stream()
                .map(Tag::getId).collect(Collectors.toList());
        tid.removeAll(collect);
        tid.forEach(id -> {
            log.info("为文章id {} 添加标签id {}", aid, id);
            articleMapper.addTag(aid, id);
        });
    }

    @Override
    public void addCategory(Long aid, List<Long> cid) {
        // 查询文章的所有标签
        List<Long> collect = articleMapper.findCategoryByArticleId(aid)
                .stream()
                .map(Category::getId).collect(Collectors.toList());
        cid.removeAll(collect);
        cid.forEach(id -> {
            log.info("为文章id {} 添加类别id {}", aid, id);
            articleMapper.addCategory(aid, id);
        });
    }
}
