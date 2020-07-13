package com.example.login.Controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.Model.UserData;
import com.example.login.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_LOGIN ="com.example.login.Controller.SingUp";
    public static final int RESULT_CODE_SIGN = 0 ;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogin;
    private Button mSignUp;
    private UserData mUserData ;
    private boolean mCheckUserData  ;
/*    private HashMap<String,Integer> usersData = new HashMap<>();
    private void addDataUser(){
        usersData.put("admin",1234);
        usersData.put("max fire",12345678);
        usersData.put("Sam Sparrow",741258);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        addDataUser();
        findView();
        setListener();
        mUserData =new UserData(mUsername.getText().toString(),mPassword.getText().toString()) ;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK || data == null){
            return;
        }
        if(requestCode==RESULT_CODE_SIGN){
            mUserData = (UserData) data.getSerializableExtra(SignUpActivity.EXTRA_KEY_SIGN);
            if (mUserData !=null){
                mUsername.setText(mUserData.getmUserName());
                mPassword.setText(mUserData.getmPassword());
                mCheckUserData = loginCheck(mUserData);
            }
        }

    }
    private void findView(){
        mUsername = findViewById(R.id.username_edit);
        mPassword = findViewById(R.id.password_edit);
        mLogin = findViewById(R.id.login_button);
        mSignUp = findViewById(R.id.sign_button);
    }

/*    private void signUpPage(){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_KEY_LOGIN,mUserData);
        startActivityForResult(intent,RESULT_CODE_SIGN);
    }*/

    private boolean loginCheck(UserData userData){
        boolean flag = false ;
            if (userData.getmUserName().equalsIgnoreCase(mUsername.getText().toString())&&
                    userData.getmPassword().equalsIgnoreCase(mPassword.getText().toString())){
                flag= true ;
            }
            else {
                flag = false ;
            }

        return flag ;
    }
    private void setListener(){
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckUserData){
                    Snackbar snackbar = Snackbar.make(findViewById(R.id.loginPage),"Welcome",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else {
                    sendMassage();
                }

            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpPage();
            }
        });
    }
    private void sendMassage(){
        Toast.makeText(this,"notCorrectData Please SignUp",Toast.LENGTH_LONG).show();

    }
    private void signUpPage(){
/*        mUserData.setmPassword(mPassword.getText().toString());
        mUserData.setmUserName(mUsername.getText().toString());*/
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        intent.putExtra(EXTRA_KEY_LOGIN,mUserData);
        startActivityForResult(intent,RESULT_CODE_SIGN);
    }
}