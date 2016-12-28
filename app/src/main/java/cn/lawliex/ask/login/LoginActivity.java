package cn.lawliex.ask.login;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.lawliex.ask.R;

public class LoginActivity extends AppCompatActivity{

    LoginFragment loginFragment;
    LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginFragment = (LoginFragment) getFragmentManager().findFragmentById(R.id.login_fragment);
        loginPresenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(loginPresenter);
        loginPresenter.start();
    }
}
