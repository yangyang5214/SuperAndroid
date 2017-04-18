package com.example.administrator.superandroid.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.dto.UserDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.util.ConfigUtil;
import com.example.administrator.superandroid.util.StringUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public EditText editUsername;

    public EditText editPassword;

    public Button buttonLogin;

    private RetrofitClient mRestClient;

    private String username;

    private String password;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        if(isLogin()){
            intentMain();
        }
        login();

    }

    private boolean isLogin() {
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        return sharedPreferences.getString("username","").length() !=0;
    }

    private void initView() {
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.login);
    }

    public void login() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                Call<ResponseDto<UserDto>> responseBodyCall = RetrofitClient.getClient().login(username, password);
                responseBodyCall.enqueue(new Callback<ResponseDto<UserDto>>() {
                    @Override
                    public void onResponse(Call<ResponseDto<UserDto>> call, Response<ResponseDto<UserDto>> response) {
                        ResponseDto<UserDto> userDto = response.body();
                        if (userDto.getSuccess() == true) {
                            saveUserMeaasge(userDto.getObj());
                            intentMain();
                        } else {
                            Toast.makeText(getApplicationContext(), userDto.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDto<UserDto>> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void  intentMain(){
        Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mIntent);
        this.finish();
    }

    public void saveUserMeaasge(UserDto userDto){
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userId", userDto.getId() + "");
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("nickName", userDto.getNickName());
        editor.putString("avatarUrl",userDto.getAvatarUrl());
        editor.commit();
    }
}