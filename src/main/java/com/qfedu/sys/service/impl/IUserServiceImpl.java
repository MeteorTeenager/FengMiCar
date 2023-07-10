package com.qfedu.sys.service.impl;

import com.qfedu.sys.domain.User;
import com.qfedu.sys.domain.UserVo;
import com.qfedu.sys.mapper.UserMapper;
import com.qfedu.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserVo userVo) {

        //1.获取到userVO中的密码，使用md5进行加密操作， 加密后再将密码设置给userVo
        String pwd = userVo.getPwd();
        String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        userVo.setPwd(md5Pwd);
        User user = userMapper.queryUserByNameAndPwd(userVo);


        return user;
    }
}
