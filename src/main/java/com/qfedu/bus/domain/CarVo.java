package com.qfedu.bus.domain;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 * 车辆的视图对象
 */
public class CarVo extends Car{
    //1.分页参数
    private Integer page;  //当前页
    private Integer limit; //每页现实多少条
    //2.ids 多个车牌号
    private String[] ids;

    public CarVo() {
    }

    public CarVo(Integer page, Integer limit, String[] ids) {
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
