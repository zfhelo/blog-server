package edu.gdpi.blogserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhengHaiFeng
 */
@Data
public class Comment implements Serializable {
    private Long id;
    private Long userId;
    private Long articleId;
    private String parent;
    private String content;
    private String ip;
}
