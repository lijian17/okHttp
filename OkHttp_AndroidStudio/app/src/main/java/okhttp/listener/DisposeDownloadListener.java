package okhttp.listener;

/**
 * 监听下载进度
 *
 * @author lijian
 * @date 2017-07-22 11:27
 */
public interface DisposeDownloadListener extends DisposeDataListener {
    /**
     * 下载进度回调
     *
     * @param progrss 当前进度值
     */
    void onProgress(int progrss);
}
