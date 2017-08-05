package net.dxs.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.dxs.okhttp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 简单OkHttp使用
 *
 * @author lijian
 * @date 2017-07-16 22:14:55
 */
public class SimpleOkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn_get;
    private Button mBtn_post;
    private TextView mTv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_ok_http);
        init();
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mBtn_get = (Button) findViewById(R.id.btn_get);
        mBtn_post = (Button) findViewById(R.id.btn_post);
        mTv_show = (TextView) findViewById(R.id.tv_show);
        mBtn_get.setOnClickListener(this);
        mBtn_post.setOnClickListener(this);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                getRequest();
                break;
            case R.id.btn_post:
                postRequest();
                break;
        }
    }

    /**
     * 发送一个get请求
     */
    private void getRequest() {
        final Request request = new Request.Builder().url("https://www.baidu.com").build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                Log.d("GET", "get=" + res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv_show.setText(res);
                    }
                });
            }
        });
    }

    /**
     * 发送一个post请求
     */
    private void postRequest() {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("name", "lijian");
        builder.add("pwd", "123456");
        Request request = new Request.Builder().url("https://www.baidu.com").post(builder.build()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                Log.d("POST", "post=" + res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv_show.setText(res);
                    }
                });
            }
        });
    }
}
