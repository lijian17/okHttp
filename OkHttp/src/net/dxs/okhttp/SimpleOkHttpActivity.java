package net.dxs.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 简单OkHttp使用
 * 
 * @author lijian
 * @date 2017-7-2 下午12:15:44
 */
public class SimpleOkHttpActivity extends Activity implements OnClickListener {

	private Button mBtn_get;
	private Button mBtn_post;
	private TextView mTv_show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_okhttp);
		init();
	}

	private void init() {
		initView();
		initData();
	}

	private void initView() {
		mBtn_get = (Button) findViewById(R.id.btn_get);
		mBtn_post = (Button) findViewById(R.id.btn_post);
		mBtn_get.setOnClickListener(this);
		mBtn_post.setOnClickListener(this);
		mTv_show = (TextView) findViewById(R.id.tv_show);
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
		final Request request = new Request.Builder().url(
				HttpUrl.parse("https://www.baidu.com")).build();
		OkHttpClient client = new OkHttpClient();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {

			/**
			 * 此时还在非UI线程
			 */
			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				final String res = response.body().string();
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						mTv_show.setText(res);
					}
				});
			}

			@Override
			public void onFailure(Call call, IOException e) {

			}
		});
	}

	/**
	 * 发送一个post请求
	 */
	private void postRequest() {
	}
}
