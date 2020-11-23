package edu.gdpi.blogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhengHaiFeng
 */
@Data
public class Category {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
