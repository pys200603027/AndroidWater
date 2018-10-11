package water.android.io.uikit.view.webview;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 装饰者模式
 */
public class SampleWebViewClientWapper extends WebViewClient {

    WebViewClient webViewClient;

    public SampleWebViewClientWapper(WebViewClient webViewClient) {
        this.webViewClient = webViewClient;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (webViewClient != null) {
            webViewClient.onPageFinished(view, url);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (webViewClient != null) {
            webViewClient.onPageStarted(view, url, favicon);
        }
    }
}
