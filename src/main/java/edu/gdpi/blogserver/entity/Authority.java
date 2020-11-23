package edu.gdpi.blogserver.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ZhengHaiFeng
 */
@Data
public class Authority implements Serializable {
    private Long id;
    private String authority;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
