package edu.gdpi.blogserver.controller;

import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.Comment;
import edu.gdpi.blogserver.entity.JwtUser;
import edu.gdpi.blogserver.service.CommentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhengHaiFeng
 */
@RestController
@RequestMapping("/api")
public class CommentController {
    @Resource
    private CommentService commentService;

    @GetMapping("/public/comment/list")
    public ResponseEntity listPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            Long articleId) {
        PageInfo<Comment> pageInfo = commentService.findAll(page, size, articleId);
        return ResponseEntity.success(pageInfo);
    }

    @DeleteMapping("/admin/comment/{id:\\d+}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.success(null);
    }

    @PostMapping("/guest/comment/{id:\\d+}")
    public ResponseEntity comment(@RequestBody Comment comment, @AuthenticationPrincipal JwtUser jwtUser, HttpServletRequest req) {
        String addr = req.getRemoteAddr();
        comment.setIp(addr);
        comment.setUserId(jwtUser.getId());
        commentService.postComment(comment);

        return ResponseEntity.success(comment);
    }
}
