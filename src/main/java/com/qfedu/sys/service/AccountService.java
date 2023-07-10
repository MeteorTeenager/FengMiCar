package com.qfedu.sys.service;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface AccountService {

    //转账的操作
    public int updateAccount(String inName ,String outName ,double money);
}
