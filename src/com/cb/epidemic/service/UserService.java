package com.cb.epidemic.service;

import com.cb.epidemic.bean.UserInfo;

/**
 * 业务层 service层
 */
public interface UserService {
    /**
     * 根据用户名和密码进行用户登录
     * @param user
     * @return
     */
    UserInfo findByAccount(UserInfo user);
}
