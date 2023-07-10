package com.qfedu.sys.utils;

/**
 * @Author:千锋强哥
 * @organization: 千锋教研院
 * @Version: 1.0
 *
 * 统一的返回对象
 */
public class ResultObj {
    private int code;  //状态码
    private String msg;  //消息

    public ResultObj(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultObj(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //创建静态的对象提供给需要的服务方
    public static ResultObj ADD_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.ADD_SUCCESS);
    public static ResultObj ADD_ERROR = new ResultObj(SysConstant.CODE_ERROR,SysConstant.ADD_ERROR);


    public static ResultObj UPDATE_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.UPDATE_SUCCESS);
    public static ResultObj UPDATE_ERROR = new ResultObj(SysConstant.CODE_ERROR,SysConstant.UPDATE_ERROR);


    public static ResultObj DELETE_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.DELETE_SUCCESS);
    public static ResultObj DELETE_ERROR = new ResultObj(SysConstant.CODE_ERROR,SysConstant.DELETE_ERROR);


    public static ResultObj DISPATCH_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.DISPATCH_SUCCESS);
    public static ResultObj DISPATCH_ERROR = new ResultObj(SysConstant.CODE_ERROR,SysConstant.DISPATCH_ERROR);


    public static ResultObj RESET_SUCCESS = new ResultObj(SysConstant.CODE_SUCCESS,SysConstant.RESET_SUCCESS);
    public static ResultObj RESET_ERROR = new ResultObj(SysConstant.CODE_ERROR,SysConstant.RESET_ERROR);


    public static ResultObj STATUS_TRUE = new ResultObj(SysConstant.CODE_SUCCESS);
    public static ResultObj STATUS_FALSE = new ResultObj(SysConstant.CODE_ERROR);
}
