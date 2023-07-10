package com.qfedu.sys.service;

import com.qfedu.sys.domain.User;
import com.qfedu.sys.domain.UserVo;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
public interface IUserService {

    /**
     * 用户登录的方法
     * @param userVo
     * @return
     */
    User login(UserVo userVo);
}
