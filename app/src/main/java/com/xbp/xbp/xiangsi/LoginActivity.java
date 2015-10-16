package com.xbp.xbp.xiangsi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xbp.xbp.xiangsi.bean.UserMsg;
import com.xbp.xbp.xiangsi.utis.CommonUtils;

import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {


    private EditText username;
    private EditText useremail;
    private EditText userpassword;
    private EditText userrepassword;
    private Button regist;
    private View flowLayout;
    private UserMsg userMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
    }

    private void regist() {

        String name=username.getText().toString();
        String email=useremail.getText().toString();
        String password=userpassword.getText().toString();
        String rpassword=userrepassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

    boolean isNetConnected= CommonUtils.isNetworkAvailable(this);

        if(!isNetConnected){
            Toast.makeText(LoginActivity.this,"没有联网",Toast.LENGTH_SHORT).show();
            return;
        }

        userMsg=UserMsg.getInstance(name,email,password);
        userMsg.setUser_id(BmobInstallation.getInstallationId(this));
        flowLayout.setVisibility(View.VISIBLE);
        userMsg.save(LoginActivity.this, new SaveListener() {

            @Override
            public void onSuccess() {
                flowLayout.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                flowLayout.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this,"注册失败",Toast.LENGTH_SHORT).show();

            }
        });

    }



    private void initView() {
        username= (EditText) findViewById(R.id.et_username);
        useremail= (EditText) findViewById(R.id.et_email);
        userpassword= (EditText) findViewById(R.id.et_password);
        userrepassword= (EditText) findViewById(R.id.et_rpasswprd);
        flowLayout=findViewById(R.id.id_progress_loading);
        regist= (Button) findViewById(R.id.btn_register);
    }


}
