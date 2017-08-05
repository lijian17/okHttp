package okhttp;

import okhttp.cookie.SimpleCookieJar;
import okhttp.listener.DisposeDataHandle;
import okhttp.response.CommonFileCallback;
import okhttp.response.CommonJsonCallback;
import okhttp.ssl.HttpsUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 用来发送get,post请求的工具类，包括设置一些请求的共用参数
 *
 * @author lijian
 * @date 2017-07-22 11:32
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT_CONNECT = 30;
    private static final int READ_TIME_OUT = 30;
    private static final int WRITE_TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        okhttpClientBuilder.cookieJar(new SimpleCookieJar());
        okhttpClientBuilder.connectTimeout(TIME_OUT_CONNECT, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        okhttpClientBuilder.followRedirects(true);

        // 信任所有HTTPS点
//        okhttpClientBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());
        mOkHttpClient = okhttpClientBuilder.build();
    }

    /**
     * 指定cilent信任指定证书
     *
     * @param certificates
     */
    public static void setCertificates(InputStream... certificates) {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory(certificates, null, null)).build();
    }

    /**
     * 指定client信任所有证书
     */
    public static void setCertificates() {
        mOkHttpClient.newBuilder().sslSocketFactory(HttpsUtils.getSslSocketFactory());
    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     * @return
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call downloadFile(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonFileCallback(handle));
        return call;
    }
}
