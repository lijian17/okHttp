package net.dxs.activity;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.dxs.model.User;
import net.dxs.okhttp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import okhttp.CommonOkHttpClient;
import okhttp.listener.DisposeDataHandle;
import okhttp.listener.DisposeDataListener;
import okhttp.listener.DisposeDownloadListener;
import okhttp.listener.DisposeHandleCookieListener;
import okhttp.request.CommonRequest;
import okhttp.request.RequestParams;


/**
 * 封装OkHttp使用
 *
 * @author lijian
 * @date 2017-07-22 11:32
 */
public class EncapsulationOkHttpActivity extends AppCompatActivity implements View.OnClickListener, DisposeHandleCookieListener {
    private static final String TAG = "OkHttpActivity";

    private ImageView mIv_four;
    private Button mBtn_login;
    private Button mBtn_getCookie;
    private Button mBtn_downLoadFile;
    private TextView mTv_showCookie;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encapsulation_ok_http);
        init();
    }

    private void init() {
        mIv_four = (ImageView) findViewById(R.id.iv_four);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        mBtn_getCookie = (Button) findViewById(R.id.btn_getCookie);
        mBtn_downLoadFile = (Button) findViewById(R.id.btn_downLoadFile);
        mTv_showCookie = (TextView) findViewById(R.id.tv_showCookie);
        mBtn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                getRequest();
//                postRequest();
                break;
            case R.id.btn_getCookie:
                try {
                    uploadFile();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_downLoadFile:
                downloadFile();
                break;
            default:
                break;
        }
    }

    private void getRequest() {
        RequestParams params = new RequestParams();
        params.put("type", "1");
        params.put("name", "lijian");
        CommonOkHttpClient.get(CommonRequest.createGetRequest("https://publicobject.com/helloworld.txt", params), new DisposeDataHandle(new DisposeDataListener() {

            @Override
            public void onSuccess(Object responseObj) {
                Log.d(TAG, responseObj.toString());
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e(TAG, "onFailure=" + reasonObj.toString());
            }
        }));
    }


    /**
     * 发送post请求,经过封装后使用方式式与AsyncHttpClient完全一样
     */
    private void postRequest() {
        // 这里在实际工程中还要再封装一层才好
        RequestParams params = new RequestParams();
        params.put("name", "lijian");
        params.put("pwd", "123456");
        CommonOkHttpClient.post(CommonRequest.createPostRequest("https://i.qianjing.com/user/login_phone.php", params), new DisposeDataHandle(this, User.class));
    }

    private void downloadFile() {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest("http://images.csdn.net/20150817/1.jpg", null),
                new DisposeDataHandle(new DisposeDownloadListener() {
                    @Override
                    public void onProgress(int progrss) {
                        // 监听下载进度，更新UI
                        Log.d("--------->当前进度为:", progrss + "");
                    }

                    @Override
                    public void onSuccess(Object responseObj) {
                        mIv_four.setImageBitmap(BitmapFactory.decodeFile(((File) responseObj).getAbsolutePath()));
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                }, Environment.getExternalStorageDirectory().getAbsolutePath() + "/test2.jpg"));
    }

    private void uploadFile() throws FileNotFoundException {

        RequestParams params = new RequestParams();
        params.put("test", new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test2.jpg"));

        CommonOkHttpClient.post(CommonRequest.createMultiPostRequest("https://api.imgur.com/3/image", params),
                new DisposeDataHandle(new DisposeDataListener() {

                    @Override
                    public void onSuccess(Object responseObj) {

                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                }));
    }

    /**
     * 服务器返回数据
     *
     * @param responseObj
     */
    @Override
    public void onSuccess(Object responseObj) {

        /**
         * 这是一个需要Cookie的请求，说明Okhttp帮我们存储了Cookie
         */
        CommonOkHttpClient.post(CommonRequest.createPostRequest("https://i.qianjing.com/user/push_list.php", null),
                new DisposeDataHandle(new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        // mCookieTextView.setText(responseObj.toString());
                        // Log.e("push_list", responseObj.toString());
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                    }
                }));
    }

    @Override
    public void onFailure(Object reasonObj) {
    }

    @Override
    public void onCookie(ArrayList<String> cookieStrLists) {
        // 自己处理Cookie回调，返回的是cookie字符串，如果想要cookie对象，可以使用HttpCookie解析为对象类型。
        mTv_showCookie.setText(cookieStrLists.get(0));
    }
}
