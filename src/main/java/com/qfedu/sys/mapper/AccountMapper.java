package com.qfedu.sys.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 */
public interface AccountMapper {

    //转入的方法
    public void inTransfer(@Param("name") String name ,@Param("money") double money);

    //转出的方法
    public void outTransfer(@Param("name") String name ,@Param("money")double money);
}
