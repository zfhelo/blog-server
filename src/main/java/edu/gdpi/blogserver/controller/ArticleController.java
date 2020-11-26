package edu.gdpi.blogserver.controller;

import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.A;
import edu.gdpi.blogserver.entity.Article;
import edu.gdpi.blogserver.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@RestController
@RequestMapping("/api")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/admin/article")
    public ResponseEntity save(@RequestBody Article article) {
        article.setId(null);
        articleService.save(article);
        return ResponseEntity.success(article);
    }

    @PostMapping("/admin/article/{id:\\d+}")
    public ResponseEntity update(@RequestBody Article article, @PathVariable Long id) {
        article.setId(id);
        articleService.update(article);
        return ResponseEntity.success(article);
    }

    @GetMapping("/admin/article/{id:\\d+}")
    public ResponseEntity findById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.success(article);
    }

    @PostMapping("/admin/article/tags")
    public ResponseEntity updateTags(@RequestBody A a) {
        articleService.updateTags(a.getAid(), a.getTid());
        return ResponseEntity.success(a.getTid());
    }

    @GetMapping("/admin/article/list")
    public ResponseEntity listPage(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageInfo<Article> pageInfo = articleService.listPage(page, size);
        return ResponseEntity.success(pageInfo);
    }

    @GetMapping("/admin/article/title/like")
    public ResponseEntity titleLike(String key) {
        List<Article> articles = articleService.findTitleLike(key);
        return ResponseEntity.success(articles);
    }

    @DeleteMapping("/admin/article/{id:\\d+}")
    public ResponseEntity deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResponseEntity.success(null);
    }

    @GetMapping("/public/article/{id:\\d+}")
    public ResponseEntity findByIdForGuest(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.success(article);
    }


    @GetMapping("/public/article/list")
    public ResponseEntity listPageForGuest(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        PageInfo<Article> pageInfo = articleService.listPage(page, size);
        return ResponseEntity.success(pageInfo);
    }

}
