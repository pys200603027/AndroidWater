package water.android.io.uikit.view.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SampleWebView sampleWebview = new SampleWebView(getContext());
        mWebView = sampleWebview.getWebView();
        refreshWebclient = sampleWebview.getRefershWebClient();
        mIsWebViewAvailable = true;
        return sampleWebview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            String url = getArguments().getString("url");
            if (url == null || url.isEmpty()) {
                throw new RuntimeException("you must set URL by intent.for example: intent.putExtra(\"url\",your URL)");
            }
            SampleWebviewHelper webviewHelper = new SampleWebviewHelper(getWebView())
                    .setWebviewSetting()
                    .setSampleWebChromeClient()
                    //让webview支持下拉刷新
                    .setSampleWebViewClient(refreshWebclient)
                    .loadURL(url);

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
}
