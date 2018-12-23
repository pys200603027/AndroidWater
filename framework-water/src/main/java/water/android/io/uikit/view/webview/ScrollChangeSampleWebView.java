package water.android.io.uikit.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 头部滑动时提供回调处理
 */
public class ScrollChangeSampleWebView extends WebView {

    public ScrollChangeSampleWebView(Context context) {
        super(context);
    }

    public ScrollChangeSampleWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (listener != null) {
            listener.onScrollChanged(l, t, oldl, oldt);
            return;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    OnScrollChangedListener listener;

    public interface OnScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        this.listener = listener;
    }
}
