package okhttp.listener;

import java.util.ArrayList;

/**
 * 当需要专门处理Cookie时创建此回调接口
 *
 * @author lijian
 * @date 2017-07-22 10:35
 */
public interface DisposeHandleCookieListener extends DisposeDataListener {
    /**
     * cookie信息储存
     *
     * @param cookieStrLists cookie集合
     */
    void onCookie(ArrayList<String> cookieStrLists);
}
