package edu.gdpi.blogserver.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.gdpi.blogserver.entity.Tag;
import edu.gdpi.blogserver.mapper.TagMapper;
import edu.gdpi.blogserver.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhengHaiFeng
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> findAll() {
        return tagMapper.selectList(null);
    }

    @Override
    public void save(Tag tag) {
        QueryWrapper<Tag> query = new QueryWrapper<>();
        query.eq("name", tag.getName());
        if (tagMapper.selectOne(query) != null) {
            throw new RuntimeException(String.format("标签已存在 {}", tag.getName()));
        }
        log.info("添加标签 {}", tag);
        tagMapper.insert(tag);
    }
}
