package com.example.administrator.superandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.util.CodeUtil;
import com.example.administrator.superandroid.util.ConfigUtil;
import com.example.administrator.superandroid.util.StringUtil;
import com.example.expressdelivery.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisteredByEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditEmail;
    private EditText mEditCode;
    private EditText mEditPassword;
    private Button mbutCommit;
    private String email;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_by_email);
        initView();
    }

    private void initView() {
        mbutCommit = (Button) findViewById(R.id.but_commit_code);
        mEditEmail = (EditText) findViewById(R.id.et_email);
        mEditCode = (EditText) findViewById(R.id.et_code);
        mEditPassword = (EditText) findViewById(R.id.et_password);

        code = CodeUtil.getInstance().getCode().toLowerCase();

        mbutCommit.setOnClickListener(this);
        mEditCode.setOnClickListener(this);
        mEditCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && mEditCode.getTextSize() > 0){
                    if (!code.equals(mEditCode.getText().toString())){
                        Toast.makeText(getApplicationContext(), ConfigUtil.getValueByKey(getApplicationContext(),"code.error"), Toast.LENGTH_SHORT).show();
                    }else{
                        mEditPassword.setFocusable(true);
                    }
                }
            }
        });
        mEditEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && mEditEmail.getTextSize() > 0){
                    if (StringUtil.isEmail(mEditEmail.getText().toString())){
                        Toast.makeText(RegisteredByEmailActivity.this, ConfigUtil.getValueByKey(getApplicationContext(),"email.error"), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_commit_code:
                registerd();
                break;
            case R.id.et_code:
                sendCode();
                break;
        }
    }

    private void registerd() {

    }

    private void sendCode() {
        code = StringUtil.getRandomString(4);
        email = mEditEmail.getText().toString();
        Call<ResponseDto> responseBodyCall = RetrofitClient.getClient().registerForCode(code,email);
        responseBodyCall.enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {

            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
            }
        });
    }
}
