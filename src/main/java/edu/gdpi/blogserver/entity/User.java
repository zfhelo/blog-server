package edu.gdpi.blogserver.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isValid;
    @TableField(exist = false)
    private List<Authority> authorities;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
