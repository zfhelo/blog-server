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
@TableName("tag")
public class Tag implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
