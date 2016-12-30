package cn.lawliex.ask.login;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.lawliex.ask.R;
import cn.lawliex.ask.data.User;
import cn.lawliex.ask.data.source.local.UserLocalDataSource;
import cn.lawliex.ask.question.list.QuestionListActivity;


/**
 * Created by Terence on 2016/12/27.
 */

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener{

    LoginContract.Presenter loginPresenter;
    TextView errorMsgTxt;
    Button loginBn, registerBn;
    EditText usernameEdit, passwordEdit;
    UserLocalDataSource localDataSource;
    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        loginPresenter = presenter;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        localDataSource = UserLocalDataSource.getInstance(getActivity());
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
        usernameEdit.setText(localDataSource.getString("name",""));
        passwordEdit.setText(localDataSource.getString("password",""));
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
        Intent intent = new Intent(getActivity(), QuestionListActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    @Override
    public void saveUser(User user) {
        localDataSource.saveUser(user);
    }
    @Override
    public void saveTicket(String ticket) {
        localDataSource.saveTicket(ticket);
    }

    @Override
    public String getTicket() {
        return localDataSource.getTicket();
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
                loginPresenter.login(getUsername(), getPassword());
                break;
            case R.id.login_register_bn:
                loginPresenter.register(getUsername(), getPassword());
                break;
        }
    }
}
