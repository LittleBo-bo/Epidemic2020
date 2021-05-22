package com.cb.epidemic.service.impl;

import com.cb.epidemic.bean.UserInfo;
import com.cb.epidemic.mapper.UserMapper;
import com.cb.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 实现类
 */
//业务层
@Service
//事务层
@Transactional
public class UserServiceImpl implements UserService {
        //实例化
    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名和密码进行用户登录
     *
     * @param user
     * @return
     */
    @Override
    public UserInfo findByAccount(UserInfo user) {
        return userMapper.findByAccount(user);
    }
}
