package okhttp.exception;

/**
 * OkHttp异常类
 *
 * @author lijian
 * @date 2017-07-21 22:08
 */
public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Object emsg;

    /**
     * 错误消息
     */
    private int ecode;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
