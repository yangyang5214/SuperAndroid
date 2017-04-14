package com.example.administrator.superandroid.activity.publish;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.superandroid.R;
import com.example.administrator.superandroid.activity.AlbumActivity;
import com.example.administrator.superandroid.activity.GalleryActivity;
import com.example.administrator.superandroid.dto.ImageItem;
import com.example.administrator.superandroid.dto.ResponseDto;
import com.example.administrator.superandroid.intent.RetrofitClient;
import com.example.administrator.superandroid.util.ImageUtil;
import com.example.administrator.superandroid.view.Bimp;
import com.example.expressdelivery.MyApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketPublishActivity extends AppCompatActivity {
    private GridView noScrollgridview;//显示图片
    private GridAdapter adapter;
    private Toolbar mToolBar;
    private TextView mTitleText;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    private static final int TAKE_PICTURE = 0x000001;
    private EditText contentEdit;
    private EditText priceEdit;
    private List<String> imageList;//动态图片的集合
    private ProgressDialog mProgressDialog;
    private SharedPreferences sharedPreferences;
    private String content;
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_publish);
        initToolBar();
        initView();
        initData();
    }

    private void initData() {
        adapter = new GridAdapter(this);
        noScrollgridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == Bimp.tempSelectBitmap.size()) {
                    //获取当前activity的view    getWindow().getDecorView()，
                    pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                } else {
                    //对图片进行查看，删除
                    Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                    intent.putExtra("position", "1");
                    intent.putExtra("ID", i);
                    startActivity(intent);
                }
            }
        });
        noScrollgridview.setAdapter(adapter);
    }

    private void initToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitleText = (TextView) findViewById(R.id.title_content);
        mTitleText.setText("二手发布");
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moving_share_top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //系统的返回键，前面加android。R.id
            case android.R.id.home:
                showNormalDialog();
                break;
            case R.id.show_moving:
                publishMoving();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //返回对话框
    private void showNormalDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("退出此次编辑？");
        dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Bimp.tempSelectBitmap.clear();
                finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    private void initView() {
        contentEdit = (EditText) findViewById(R.id.moving_content);
        priceEdit = (EditText) findViewById(R.id.market_price);
        priceEdit.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        pop = new PopupWindow(this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //拍照
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        //调用手机相册
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MarketPublishActivity.this, AlbumActivity.class);
                startActivity(intent);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


        noScrollgridview = (GridView) findViewById(R.id.gridview_show_image);
    }


    //调用系统相机
    public void takePhoto() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }


    //获取相机的数据，并返回为图片格式
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
                    ImageItem takePhoto = new ImageItem();
                    String fileName = String.valueOf(System.currentTimeMillis());
                    Bitmap bm = (Bitmap) data.getExtras().get("data");
                    takePhoto.setBitmap(bm);
                    FileOutputStream out = null;
                    String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/八一农大/";
                    File file = new File(SDPATH);
                    if (!file.exists()) {
                        file.mkdirs();// 如果文件夹不存在，则创建文件夹
                    }
                    String photoPath = SDPATH + fileName + ".jpg";
                    try {
                        out = new FileOutputStream(photoPath);
                        bm.compress(Bitmap.CompressFormat.JPEG, 90, out);// 把数据写入文件
                        takePhoto.setImagePath(photoPath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Bimp.tempSelectBitmap.add(takePhoto);
                }
                break;
        }
    }

    private void publishMoving() {
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        userid = sharedPreferences.getString("userId","");
//        userid = "1";
        content = contentEdit.getText().toString();
        String price = priceEdit.getText().toString();
        imageList = new ArrayList<>();
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            imageList.add(Bimp.tempSelectBitmap.get(i).imagePath);
        }
        List<MultipartBody.Part> parts = null;
        if (imageList.size() != 0) {
            parts = ImageUtil.filesToMultipartBodyParts(imageList);
        }
        mProgressDialog = ProgressDialog.show(this, null, "发表中...", true, false);
        mProgressDialog.setCancelable(true);
        Call<ResponseDto> responseBodyCall = RetrofitClient.getClient().publishMoving(parts, userid, content,price, 3);
        responseBodyCall.enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                ResponseDto message = response.body();
                if (message.getSuccess() == true) {
                    if (response.isSuccessful()) {
                        mProgressDialog.dismiss();
                        Bimp.tempSelectBitmap.clear();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }

    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public void update() {
            loading();
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if (Bimp.tempSelectBitmap.size() == 9) {
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = inflater.inflate(R.layout.moving_show_image, viewGroup, false);
                holder = new ViewHolder();
                holder.image = (ImageView) view.findViewById(R.id.item_grida_image);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            if (i == Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.moving_show_add_image));
                if (i == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(i).getBitmap());
            }

            return view;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    adapter.notifyDataSetChanged();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void loading() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        break;
                    } else {
                        Bimp.max += 1;
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                }
            }
        }).start();
    }

    public class ViewHolder {
        public ImageView image;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showNormalDialog();
        }
        return true;
    }
}
