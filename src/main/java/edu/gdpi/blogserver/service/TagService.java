package edu.gdpi.blogserver.service;

import edu.gdpi.blogserver.entity.Tag;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
public interface TagService {
    /**
     * 查询所有标签
     *
     * @return
     */
    List<Tag> findAll();

    /**
     * 保存标签
     *
     * @param tag
     */
    void save(Tag tag);
}
