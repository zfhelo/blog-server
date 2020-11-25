package edu.gdpi.blogserver.service;

import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.entity.Comment;

/**
 * @author ZhengHaiFeng
 */
public interface CommentService {
    /**
     * 删除评论
     *
     * @param page
     * @param size
     * @param articleId
     * @return
     */
    PageInfo<Comment> findAll(int page, int size, Long articleId);

    /**
     * 删除文章
     *
     * @param id
     */
    void deleteComment(Long id);

    /**
     * 发送评论
     *
     * @param comment
     */
    void postComment(Comment comment);
}
