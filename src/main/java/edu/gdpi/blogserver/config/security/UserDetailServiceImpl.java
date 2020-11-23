package edu.gdpi.blogserver.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.gdpi.blogserver.entity.JwtUser;
import edu.gdpi.blogserver.entity.User;
import edu.gdpi.blogserver.mapper.AuthorityMapper;
import edu.gdpi.blogserver.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhengHaiFeng
 */
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AuthorityMapper authorityMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户名 {%s} 不存在", username));
        }
        // 查询用户权限
        user.setAuthorities(authorityMapper.findByUserId(user.getId()));
        return new JwtUser(user);
    }
}
