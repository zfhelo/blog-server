package edu.gdpi.blogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhengHaiFeng
 */
@Data
@TableName("article")
public class Article implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String description;
    private Boolean isCommented;
    private LocalDateTime sendTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
