package edu.gdpi.blogserver.controller;

import edu.gdpi.blogserver.api.ResponseEntity;
import edu.gdpi.blogserver.entity.Category;
import edu.gdpi.blogserver.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ZhengHaiFeng
 */
@Api("标签操作")
@RestController
@RequestMapping("/api/admin")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/categories")
    @ApiOperation(value = "查询所有类别")
    public ResponseEntity selectAll() {
        return ResponseEntity.success(categoryService.findAll());
    }

    @PostMapping("/category")
    @ApiOperation(value = "创建标签")
    public ResponseEntity save(@RequestBody Category cate) {
        cate.setId(null);
        categoryService.save(cate);
        return ResponseEntity.success(cate);
    }
}
