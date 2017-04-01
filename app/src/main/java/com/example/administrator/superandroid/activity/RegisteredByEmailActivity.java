package com.example.administrator.superandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.util.CodeUtil;
import com.example.administrator.superandroid.util.ConfigUtil;
import com.example.administrator.superandroid.util.StringUtil;

public class RegisteredByEmailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EMAIL ="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    private EditText mEditEmail;
    private EditText mEditCode;
    private Button mbutCommit;
    private ImageView mShowCode;
    private EditText mEditPwd;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initView() {
        mbutCommit = (Button) findViewById(R.id.but_commit_code);
        mEditEmail = (EditText) findViewById(R.id.et_phoneNum);
        mEditCode = (EditText) findViewById(R.id.et_phoneCodes);
        mShowCode = (ImageView) findViewById(R.id.iv_showCode);
        mEditPwd = (EditText) findViewById(R.id.et_password);

        mShowCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
        code = CodeUtil.getInstance().getCode().toLowerCase();

        mbutCommit.setOnClickListener(this);
        mEditCode.setOnClickListener(this);
        mShowCode.setOnClickListener(this);
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
            case R.id.iv_showCode:
                mShowCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
                code = CodeUtil.getInstance().getCode().toLowerCase();
                break;
            case R.id.but_commit_code:
                if (mEditEmail.getText().toString().length() < 0){
                    Toast.makeText(RegisteredByEmailActivity.this, ConfigUtil.getValueByKey(getApplicationContext(),"username.not.null"), Toast.LENGTH_SHORT).show();
                }
                if (mEditCode.getText().toString().length() < 0){
                    Toast.makeText(RegisteredByEmailActivity.this, ConfigUtil.getValueByKey(getApplicationContext(),"code.not.null"), Toast.LENGTH_SHORT).show();
                }
                if (mEditPwd.getText().toString().length() < 0){
                    Toast.makeText(RegisteredByEmailActivity.this, ConfigUtil.getValueByKey(getApplicationContext(),"password.not.null"), Toast.LENGTH_SHORT).show();
                }
                String phoneCode = mEditCode.getText().toString().toLowerCase();
                if (phoneCode.equals(code)) {
                    registerd();
                } else {
                    Toast.makeText(RegisteredByEmailActivity.this, phoneCode + ConfigUtil.getValueByKey(getApplicationContext(),"code.error"), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void registerd() {

    }
}
