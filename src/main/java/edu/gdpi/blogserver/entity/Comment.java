package edu.gdpi.blogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhengHaiFeng
 */
@Data
public class Comment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long articleId;
    private String parent;
    private String content;
    private String ip;

    @TableField(exist = false)
    private User user;
    private User parentUser;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
