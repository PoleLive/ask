package cn.lawliex.ask.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by Terence on 2017/1/6.
 */

public class Feed {
    private int id;
    private int user_id;
    private String name;
    private int type;
    private Date createdDate;
    private String data;
    private String headUrl;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    private JSONObject jsonObject = null;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getData() {
        return data;
    }

    public String getString(String key){
        return jsonObject.getString(key);
    }
    public int getInt(String key){
        return jsonObject.getInteger(key);
    }
    public void setData(String data) {
        jsonObject = JSON.parseObject(data);
        this.data = data;
    }
}
