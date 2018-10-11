package water.android.io.uikit.view.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import water.android.io.water.base.R;

/**
 * 带refreshlayout的webview
 */
public class SampleWebView extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {

    ScrollChangeSampleWebView webView;
    RefreshWebviewClient webviewClient = new RefreshWebviewClient();

    public SampleWebView(Context context) {
        super(context);
        init(context);
    }

    public SampleWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_refresh_webview, this);
        setOnRefreshListener(this);

        webView = findViewById(R.id.webView);
        webView.setOnScrollChangedListener(new ScrollChangeSampleWebView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (webView.getScrollY() == 0) {
                    setEnabled(true);
                } else {
                    setEnabled(false);
                }
            }
        });
    }

    public ScrollChangeSampleWebView getWebView() {
        return webView;
    }

    public WebViewClient getRefershWebClient() {
        return webviewClient;
    }

    @Override
    protected void onDetachedFromWindow() {
        webView.setOnScrollChangedListener(null);
        setOnRefreshListener(null);
        super.onDetachedFromWindow();
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    public class RefreshWebviewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            setRefreshing(true);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            setRefreshing(false);
        }
    }

}
