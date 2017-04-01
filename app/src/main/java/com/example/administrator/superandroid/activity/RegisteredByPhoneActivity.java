package com.example.administrator.superandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.administrator.superandroid.R;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisteredByPhoneActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int CODE_ING = 1;   //已发送，倒计时
    private static final int CODE_REPEAT = 2;  //重新发送
    private static final int SMSDDK_HANDLER = 3;  //短信回调
    private int TIME = 60;//倒计时60s

    private EventHandler eventHandler;

    private EditText mEditUsername;
    private EditText mEditPwd;
    private EditText mEditCode;
    private Button mButRegister;
    private Button mButGetCode;

    private String userPhone;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_by_phone);
        initView();//界面初始化
        initSDK();//短信初始化

    }

    private void initView() {
        mEditUsername = (EditText) findViewById(R.id.et_phoneNum);
        mEditPwd = (EditText) findViewById(R.id.et_password);
        mEditCode = (EditText) findViewById(R.id.et_code);
        mButRegister = (Button) findViewById(R.id.but_commit_code);
        mButGetCode = (Button) findViewById(R.id.but_get_code);
        mButGetCode.setOnClickListener(this);
        mButRegister.setOnClickListener(this);
    }

    private void initSDK() {
        SMSSDK.initSDK(this, "16c1e74ef21ee", "2dacb878591f2685145ef21842363361");
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                msg.what = SMSDDK_HANDLER;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    public void onClick(View v) {
        userPhone = mEditUsername.getText().toString();
        switch (v.getId()) {
            case R.id.but_get_code://获取验证码
                new AlertDialog.Builder(RegisteredByPhoneActivity.this)
                        .setTitle("发送短信")
                        .setMessage("我们将把验证码发送到以下号码:\n" + "+86:" + userPhone)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SMSSDK.getVerificationCode("86", userPhone);
                                mButGetCode.setClickable(false);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        for (int i = 60; i > 0; i--) {
                                            handler.sendEmptyMessage(CODE_ING);
                                            if (i <= 0) {
                                                break;
                                            }
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        handler.sendEmptyMessage(CODE_REPEAT);
                                    }
                                }).start();
                            }
                        })
                        .create()
                        .show();
                break;

            case R.id.but_commit_code://注册
                SMSSDK.submitVerificationCode("86", userPhone, mEditCode.getText().toString());//对验证码进行验证->回调函数
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CODE_ING://已发送,倒计时
                    mButGetCode.setText("重新发送(" + --TIME + "s)");
                    break;
                case CODE_REPEAT://重新发送
                    mButGetCode.setText("获取验证码");
                    mButGetCode.setClickable(true);
                    break;
                case SMSDDK_HANDLER:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    //回调完成
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        //验证码验证成功
                        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                            Toast.makeText(RegisteredByPhoneActivity.this, "验证成功", Toast.LENGTH_LONG).show();
                            Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mIntent);
                            finish();

                        }
                        //已发送验证码
                        else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                            Toast.makeText(getApplicationContext(), "验证码已经发送",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    if (result == SMSSDK.RESULT_ERROR) {
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(getApplicationContext(), des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            //do something
                        }
                    }
                    break;
//                case R.id.register_status:
//                    String result_code = msg.getData().getString("result").toString();
//                    if("1".equals(result_code))
//                    {
//                        Toast.makeText(RegisteredByPhoneActivity.this, "注册成功", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(RegisteredByPhoneActivity.this,MainActivity.class);
//                        intent.putExtra("userPhone", userPhone);
//                        RegisteredByPhoneActivity.this.setResult(RESULE_CODE, intent);
//                        //startActivity(intent);
//                        finish();
//                    }else
//                    {
//                        Toast.makeText(RegisteredByPhoneActivity.this, "注册失败", Toast.LENGTH_LONG).show();
//                    }
//                    break;
//                case R.id.check_phone_exist://手机号是否已存在
//                    String result_code_2 = msg.getData().getString("result").toString();
//                    if("1".equals(result_code_2))
//                    {
//                        errPhoneText.setText("手机号码已经注册，请换用其他号码");
//                        resultMap.put("phone", false);
//                    }
//                    else
//                    {
//                        errPhoneText.setText("");
//                        resultMap.put("phone", true);
//                    }
//                    break;
            }
        }
    };


}
