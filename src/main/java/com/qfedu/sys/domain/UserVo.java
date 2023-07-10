package com.qfedu.sys.domain;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 * UserVo对象继承User对象，弥补一些User做不到的事情。
 * 封装一些分页参数 ， 验证码参数
 */
public class UserVo extends User{
    //定义分页参数
    private Integer page;   //当前页码
    private Integer limit;  //每页显示多少条数据
    private String code; //验证码
    private Integer[] ids; //id的数组 ， 批量删除会用到

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
