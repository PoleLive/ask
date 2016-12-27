package cn.lawliex.ask.data;

/**
 * Created by Terence on 2016/12/27.
 */

public class LoginResponse extends BaseResponse {

    public int getCode() {
        return  super.getCode();
    }

    public void setCode(int code) {
        super.setCode(code);
    }

    public String getMsg() {
        return super.getMsg();
    }

    public void setMsg(String msg) {
        super.setMsg(msg);
    }

    public User getUser() {
        return (User) super.getData();
    }

    public void setUser(User user) {
        super.setData(user);
    }
}
