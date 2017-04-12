package com.example.administrator.superandroid.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.adapter.FolderAdapter;
import com.example.administrator.superandroid.view.Bimp;


/**
 * 这个类主要是用来进行显示包含图片的文件夹
 *
 */
public class ImageFile extends AppCompatActivity {

	private FolderAdapter folderAdapter;
	private Context mContext;

	private GridView gridView;

	private Toolbar mToolBar;
	private TextView mTitleText;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plugin_camera_image_file);
		initToolBar();
		mContext = this;
		initView();
		initData();
	}

	private void initData() {
		folderAdapter = new FolderAdapter(this,ImageFile.this);
		gridView.setAdapter(folderAdapter);
	}

	private void initView() {
		gridView = (GridView) findViewById(R.id.fileGridView);
	}


	private void initToolBar() {
		mToolBar = (Toolbar) findViewById(R.id.toolbar);
		mTitleText = (TextView) findViewById(R.id.title_content);
		mTitleText.setText("选择相册");
		mToolBar.setTitle("");
		setSupportActionBar(mToolBar);
	}

	//取消按钮设置监听
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.photos_recall:
				//清空选择的图片
				Bimp.tempSelectBitmap.clear();
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}


	//右上角的取消按钮
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.moving_photos_recall, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return true;
	}

}
