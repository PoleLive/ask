package cn.lawliex.ask.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.lawliex.ask.R;
import cn.lawliex.ask.data.User;

//程序入口
public class BeginActivity extends AppCompatActivity {
    LoginPresenter loginPresenter;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        txt = (TextView)findViewById(R.id.txt);

        Intent intent = new Intent(BeginActivity.this, LoginActivity.class);
        startActivity(intent);
//        loginPresenter = new LoginPresenter();
//        loginPresenter.login("login", "123", new LoginContract.LoginCallback() {
//            @Override
//            public void onLoginSuccess(User user) {
//                txt.setText(user.getName());
//            }
//
//            @Override
//            public void onLoginFail() {
//                txt.setText("fail");
//            }
//        });
    }
}