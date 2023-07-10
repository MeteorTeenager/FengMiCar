package com.qfedu.sys.mapper;

import com.qfedu.sys.domain.User;
import com.qfedu.sys.domain.UserVo;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface UserMapper {
    User queryUserByNameAndPwd(UserVo userVo);
}
