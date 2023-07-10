package com.qfedu.bus.domain;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 * 客户的视图对象
 */
public class CustomerVo extends Customer{
    //分页参数
    private Integer page; //当前页码
    private Integer limit; //每页显示多少条信息

    //补充ids集合
    private String[] ids; //批量删除会用户身份证的ids的数组

    public CustomerVo() {
    }

    public CustomerVo(Integer page, Integer limit, String[] ids) {
        this.page = page;
        this.limit = limit;
        this.ids = ids;
    }

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

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
