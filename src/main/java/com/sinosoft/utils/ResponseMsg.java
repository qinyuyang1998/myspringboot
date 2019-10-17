package com.sinosoft.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.sinosoft.myspringboot.enums.ResultStatusCodeEmun;

/** 
 * 响应统一报文格式
 * @ClassName: ResponseMsg 
 * @Description: TODO
 * @author: liuch
 * @date: 2018年9月28日 下午1:48:04  
 */
public class ResponseMsg{
    
    private int code;//响应编码
    private String msg;//响应消息
    private Object resultData;
    
    
    /** 
     * @Title:ResponseMsg
     * @Description:TODO  
     */  
    public ResponseMsg() {
        super();
    }

    public ResponseMsg(int code,String msg,Object resultData) {
        this.code = code;
        this.msg = msg;
        covertResultData(resultData);
        
    }
    
    public ResponseMsg(ResultStatusCodeEmun result){
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.resultData = null;
    }
    
    public ResponseMsg(ResultStatusCodeEmun result, Object resultData){
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.resultData = resultData;
    }
    
    
    /** 
    * @Description: resultdata为json字符串转jsonobject 
    * @param resultData void
    * @author liuch
    * @date 2018年12月20日下午2:22:02
    */ 
    private void covertResultData(Object resultData) {
        if(resultData instanceof String){
            try{
                this.resultData = JSONObject.parseObject((String)resultData);
            }catch(JSONException e){
                try{
                    this.resultData = JSONArray.parseArray((String) resultData);
                }catch(JSONException ex){
                    this.resultData = resultData;
                }
            }
        }else{
            this.resultData = resultData == null ? "" : resultData;
        }
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
    public Object getResultData() {
        return resultData;
    }
    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "ResponseMsg [code=" + code + ", msg=" + msg + ", resultData="
                + resultData + "]";
    }
    
    
}
