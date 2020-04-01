package com.sdl.times.common.model;

import java.util.HashMap;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";
    /** 返回内容 */
    public static final String MSG_TAG = "msg";
    /** 数据对象 */
    public static final String DATA_TAG = "data";

    public Result() {
    }
    /**
     * 构造返回对象
     * @param code
     * @param msg
     * @param data
     */
    public Result(int code,String msg,Object data) {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if(data!=null){
            super.put(DATA_TAG,data);
        }
    }
    /**
     * 成功消息
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result success(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
    public static Result success(String msg){
        return success(200,msg,null);
    }
    public static Result success(Object data){
        return success(200,"操作成功",data);
    }
    public static Result success(){
        return success("操作成功");
    }
    /**
     * 失败
     * @param msg
     * @param data
     * @return
     */
    public static Result error(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
    public static Result error(int code,String msg){
        return  error(code,msg,null);
    }
    public static Result error(String msg){
        return  error(500,msg,null);
    }
    public static Result error(Object data){
        return  error(500,"操作失败",null);
    }
    public static Result error(){
        return error("操作失败");
    }
}
