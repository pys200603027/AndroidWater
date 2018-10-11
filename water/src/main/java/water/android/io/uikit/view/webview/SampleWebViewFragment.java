package water.android.io.uikit.view.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.socks.library.KLog;

/**
 * 简单封装的WebView
 */
public class SampleWebViewFragment extends Fragment {

    private WebView mWebView;
    private boolean mIsWebViewAvailable;
    WebViewClient refreshWebclient;

    public static SampleWebViewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("url", url);
        SampleWebViewFragment fragment = new SampleWebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to instantiate the view. Creates and returns the WebView.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.destroy();
        }

        SampleWebView refreshWebView = new SampleWebView(getContext());
        mWebView = refreshWebView.getWebView();
        refreshWebclient = refreshWebView.getRefershWebClient();
        mIsWebViewAvailable = true;
        return refreshWebView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            final ScrollChangeSampleWebView webView = (ScrollChangeSampleWebView) getWebView();
            /**
             * WebClient & WebChromeClient
             */
            webView.setWebViewClient(new SampleWebViewClientWapper(refreshWebclient) {
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
            /**
             * setting
             */
            WebSettings settings = getWebView().getSettings();
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

            webView.post(new Runnable() {
                @Override
                public void run() {
                    //调用js
                    String url = getArguments().getString("url");
                    if (url == null || url.isEmpty()) {
                        throw new RuntimeException("you must set URL by intent.for example: intent.putExtra(\"url\",your URL)");
                    }
                    webView.loadUrl((url));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets the WebView.
     */
    public WebView getWebView() {
        return mIsWebViewAvailable ? mWebView : null;
    }

    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
    @Override
    public void onResume() {
        mWebView.onResume();
        super.onResume();
    }

    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
    @Override
    public void onDestroyView() {
        mIsWebViewAvailable = false;
        if (mWebView != null) {
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroyView();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    js 相关
     */

    protected void removeJavascriptInterface(String method) {
        WebView webView = getWebView();
        if (webView != null) {
            webView.removeJavascriptInterface(method);
        }
    }

    protected void addJavascriptInterface(Object o, String method) {
        WebView webView = getWebView();
        if (webView != null) {
            webView.addJavascriptInterface(o, method);
        }
    }

    /**
     * js调用 <br/>
     * 例子：callJs("javascript:getToken(\"" + mUserToken + "\")", new ValueCallback<String>(){});
     *
     * @param js
     * @param callback
     */
    private void callJs(String js, final ValueCallback<String> callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWebView().evaluateJavascript(js, callback);
        } else {
            getWebView().loadUrl(js);
        }
    }


}
