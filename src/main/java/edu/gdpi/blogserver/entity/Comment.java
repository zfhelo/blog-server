package edu.gdpi.blogserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    @TableField(exist = false)
    private User user;
    
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
