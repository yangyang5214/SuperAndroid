package com.example.administrator.superandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.WebView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.main.LibraryActivity;
import com.example.administrator.superandroid.util.ConfigUtil;

public class NoticeActivity extends AppCompatActivity {
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        cardView = (CardView)findViewById(R.id.cardView);

        cardView.setRadius(8);//设置图片圆角的半径大小

        cardView.setCardElevation(8);//设置阴影部分大小

        cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LibraryActivity.class);
                intent.putExtra("url",ConfigUtil.getValueByKey(getApplicationContext(),"school.nitice"));
                startActivity(intent);
            }
        });
    }
}
