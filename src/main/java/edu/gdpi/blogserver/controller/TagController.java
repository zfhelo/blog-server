package edu.gdpi.blogserver.controller;

import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.Tag;
import edu.gdpi.blogserver.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ZhengHaiFeng
 */
@Api("标签操作")
@RestController
@RequestMapping("/api/")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/public/tags")
    @ApiOperation(value = "查询所有标签")
    public ResponseEntity findAll() {
        return ResponseEntity.success(tagService.findAll());
    }

    @PostMapping("/admin/tag")
    @ApiOperation(value = "创建标签")
    public ResponseEntity save(@RequestBody Tag tag) {
        tag.setId(null);
        tagService.save(tag);
        return ResponseEntity.success(tag);
    }
}
