package com.example.login.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.Model.UserData;
import com.example.login.R;

import java.io.Serializable;

public class SignUpActivity extends AppCompatActivity implements Serializable {
    public static final String EXTRA_KEY_SIGN = "com.example.login.Controller.SignUp";
    public static final int RESULT_CODE_SIGN = 0;
    private EditText mUsername ;
    private EditText mPassword;
    private String mUsernameStr="";
    private String mPasswordStr="";
    private Button mSignUpButton;
    private UserData mUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        getIntentValue();
        findView();
        setListener();
        mUserData = new UserData(mUsername.getText().toString(),mPassword.getText().toString());

    }

    private void findView() {
        mUsername = findViewById(R.id.username_edit);
        mPassword = findViewById(R.id.password_edit);
        mSignUpButton = findViewById(R.id.sign_button);
    }

    private void getIntentValue() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra(LoginActivity.EXTRA_KEY_LOGIN) != null) {
            mUserData = (UserData) intent.getSerializableExtra(LoginActivity.EXTRA_KEY_LOGIN);
            logPageInfo();
            mPassword.setText(mPasswordStr);
            mUsername.setText(mUsernameStr);
        }
    }
    private void logPageInfo(){
        if (mUserData.getmUserName()!=null&&mUserData.getmPassword()!=null){
            mPasswordStr=mUserData.getmPassword();
            mUsernameStr=mUserData.getmUserName();
        }
    }
    private void setListener() {
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData();
                sendResult();
                finish();
            }
        });
    }

    private void getUserData() {
        mUserData.setmUserName(mUsername.getText().toString());
        mUserData.setmPassword(mPassword.getText().toString());
    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_KEY_SIGN, mUserData);
        setResult(RESULT_CODE_SIGN, intent);
    }

/*    private void test(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }*/
}