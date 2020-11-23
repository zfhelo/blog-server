package edu.gdpi.blogserver.controller;

import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.A;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhengHaiFeng
 */
@RestController
@RequestMapping("/api/admin")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/article")
    public ResponseEntity save(@RequestBody Article article) {
        article.setId(null);
        articleService.save(article);
        return ResponseEntity.success(article);
    }

    @PostMapping("/article/tags")
    public ResponseEntity addTags(@RequestBody A a) {
        articleService.addTags(a.getAid(), a.getTid());
        return ResponseEntity.success(a.getTid());
    }

    @PostMapping("/article/categories")
    public ResponseEntity addCategory(@RequestBody A a) {
        articleService.addCategory(a.getAid(), a.getCid());
        return ResponseEntity.success(a.getCid());
    }
}
