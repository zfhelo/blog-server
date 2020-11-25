package edu.gdpi.blogserver.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gdpi.blogserver.entity.Comment;
import edu.gdpi.blogserver.mapper.CommentMapper;
import edu.gdpi.blogserver.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ZhengHaiFeng
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public PageInfo<Comment> findAll(int page, int size, Long articleId) {
        if (articleId == null) {
            log.info("查询所有评论");
            return PageHelper.startPage(page, size).doSelectPageInfo(() -> commentMapper.findAll());
        }
        log.info("查询文章 id 为 {} 的所有评论", articleId);
        return PageHelper.startPage(page, size).doSelectPageInfo(() -> commentMapper.findByArticleId(articleId));
    }

    @Override
    public void deleteComment(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public void postComment(Comment comment) {
        log.info("添加评论 用户 id: {} IP: {}, 文章 id: {}", comment.getUserId(), comment.getIp(), comment.getArticleId());
        commentMapper.insert(comment);
    }
}
