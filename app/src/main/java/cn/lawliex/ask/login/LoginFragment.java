package cn.lawliex.ask.login;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;
import cn.lawliex.ask.R;
import cn.lawliex.ask.data.BaseResponse;
import cn.lawliex.ask.data.LoginResponse;
import cn.lawliex.ask.data.User;

/**
 * Created by Terence on 2016/12/27.
 */

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener{

    LoginContract.Presenter loginPresenter;
    TextView errorMsgTxt;
    Button loginBn, registerBn;
    EditText usernameEdit, passwordEdit;
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.login_fragment,container,false);;
        usernameEdit = (EditText)view.findViewById(R.id.login_name_edit);
        passwordEdit = (EditText)view.findViewById(R.id.login_pass_edit);
        loginBn = (Button)view.findViewById(R.id.login_login_bn);
        registerBn = (Button)view.findViewById(R.id.login_register_bn);
        errorMsgTxt = (TextView)view.findViewById(R.id.login_msg_txt);

        initListener();
        return view;

    }
    private void initListener(){
        loginBn.setOnClickListener(this);
        registerBn.setOnClickListener(this);

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public String getUsername() {
        return usernameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public boolean isPasswordValid(String password) {
        return true;
    }

    @Override
    public boolean isUsernameValid(String username) {
        return true;
    }

    @Override
    public void toMainAct() {

    }

    @Override
    public void setErrorMessage(String error) {
        errorMsgTxt.setText(error);
    }

    @Override
    public void showLoginProgress(boolean show) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login_bn:
                loginPresenter.login(getUsername(), getPassword(), new LoginContract.LoginCallback() {
                    @Override
                    public void onLoginSuccess(BaseResponse response) {
                        setErrorMessage("success");
                    }

                    @Override
                    public void onLoginFail(String errMsg) {
                        setErrorMessage(errMsg);
                    }
                });
                break;

            case R.id.login_register_bn:
                loginPresenter.register(getUsername(), getPassword(), new LoginContract.RegisterCallback() {
                    @Override
                    public void onRegisterSuccess(BaseResponse response) {
                        setErrorMessage(response.getMsg());
                    }

                    @Override
                    public void onRegisterFail(String errMsg) {
                        setErrorMessage(errMsg);
                    }
                });
                break;
        }
    }
}
