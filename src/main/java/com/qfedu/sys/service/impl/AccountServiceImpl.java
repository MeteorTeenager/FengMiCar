package com.qfedu.sys.service.impl;

import com.qfedu.sys.mapper.AccountMapper;
import com.qfedu.sys.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    // @Autowired是用来注入对象的， spring容器中有 mapper的代理对象
    @Autowired
    private AccountMapper accountMapper;


    @Override
    public int updateAccount(String inName, String outName, double money) {

        try {
            accountMapper.inTransfer(inName,money);
            accountMapper.outTransfer(outName,money);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
