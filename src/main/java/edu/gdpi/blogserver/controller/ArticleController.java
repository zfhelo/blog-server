package edu.gdpi.blogserver.controller;

import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.A;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.service.ArticleService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/article/{id:\\d+}")
    public ResponseEntity update(@RequestBody Article article, @PathVariable Long id) {
        article.setId(id);
        articleService.update(article);
        return ResponseEntity.success(article);
    }

    @GetMapping("/article/{id:\\d+}")
    public ResponseEntity findById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.success(article);
    }

    @PostMapping("/article/tags")
    public ResponseEntity updateTags(@RequestBody A a) {
        articleService.updateTags(a.getAid(), a.getTid());
        return ResponseEntity.success(a.getTid());
    }

    @GetMapping("/article/list/")
    public ResponseEntity listPage(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageInfo<Article> pageInfo = articleService.listPage(page, size);
        return ResponseEntity.success(pageInfo);
    }
}
