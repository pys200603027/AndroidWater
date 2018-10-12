package water.android.io.uikit.view.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.socks.library.KLog;

/**
 * Webview使用时进行的一些基本设置
 */
public class SampleWebviewHelper {
    private WebView webView;

    public SampleWebviewHelper(WebView webView) {
        this.webView = webView;
    }

    public SampleWebviewHelper setWebviewSetting() {
        /**
         * setting
         */
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(false);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //适配5.0不允许http和https混合使用情况
            settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webView.clearCache(true);
        return this;
    }

    public SampleWebviewHelper setCoustomWebViewClient(WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
        return this;
    }

    public SampleWebviewHelper setSampleWebViewClient(WebViewClient webViewClient) {
        webView.setWebViewClient(new SampleWebViewClientWapper(webViewClient) {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                KLog.d("123", "+WebView onPageFinished");
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                KLog.d("123", "+WebView onReceivedError");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                KLog.d("123", "+WebView onPageStarted");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().startsWith("http://") || request.getUrl().toString().startsWith("https://")) {
                        view.loadUrl(request.getUrl().toString());
                        webView.stopLoading();
                        return true;
                    }
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        return this;
    }

    public SampleWebviewHelper setSampleWebChromeClient() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                KLog.d("123", "WebView onProgressChanged:" + newProgress);
            }
        });
        return this;
    }

    public SampleWebviewHelper loadURL(final String url) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });
        return this;
    }

    /*
       js 相关
     */
    public SampleWebviewHelper removeJavascriptInterface(String method) {
        if (webView != null) {
            webView.removeJavascriptInterface(method);
        }
        return this;
    }


    public SampleWebviewHelper addJavascriptInterface(Object o, String method) {
        if (webView != null) {
            webView.addJavascriptInterface(o, method);
        }
        return this;
    }

    /**
     * js调用 <br/>
     * 例子：callJs("javascript:getToken(\"" + mUserToken + "\")", new ValueCallback<String>(){});
     *
     * @param js
     * @param callback
     */
    public void callJs(String js, final ValueCallback<String> callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(js, callback);
        } else {
            webView.loadUrl(js);
        }
    }

}

