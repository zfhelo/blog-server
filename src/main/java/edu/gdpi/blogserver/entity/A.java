package edu.gdpi.blogserver.entity;

import lombok.Data;

import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Data
public class A {
    private Long aid;
    private List<Long> tid;
    private List<Long> cid;
}
