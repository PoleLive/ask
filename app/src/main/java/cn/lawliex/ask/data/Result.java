package cn.lawliex.ask.data;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Terence on 2016/12/28.
 */

public class Result<T> {
    private int code;
    private String msg;
    private String ticket;
    private T data;

    public int getCode() {
        return code;
    }
    public Result(JSONObject jsonObject,Class<T> cl){
        setCode(jsonObject.getInteger("code"));
        setMsg(jsonObject.getString("msg"));
        JSONObject object = jsonObject.getJSONObject("data");
        if(object!=null)
            data = object.toJavaObject(cl);
        else
            data = null;
        setTicket(jsonObject.getString("ticket"));
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
