package okhttp.listener;

/**
 * 数据处理的监听器
 *
 * @author lijian
 * @date 2017-07-21 22:16
 */
public interface DisposeDataListener {

    /**
     * 成功
     *
     * @param responseObj 响应消息
     */
    void onSuccess(Object responseObj);

    /**
     * 失败
     *
     * @param reasonObj 失败消息
     */
    void onFailure(Object reasonObj);
}
