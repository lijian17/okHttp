package okhttp.listener;

/**
 * 数据处理器<br>
 * 封装响应回调和字节码对象
 *
 * @author lijian
 * @date 2017-07-21 22:07
 */
public class DisposeDataHandle {

    public DisposeDataListener mListener;
    public Class<?> mClass;
    public String mSource;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> clazz) {
        this.mListener = listener;
        this.mClass = clazz;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.mListener = listener;
        this.mSource = source;
    }
}
