package water.android.io.androidwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.socks.library.KLog;

import water.android.io.liquid.utils.Log;
import water.android.io.uikit.view.webview.SampleWebView;
import water.android.io.uikit.view.webview.ScrollChangeSampleWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.init(new Log.FlatformLog() {
            @Override
            public void d(String msg) {
                KLog.d("123", msg);
            }
        });

        SampleWebView sw=findViewById(R.id.webView);
        final ScrollChangeSampleWebView webView = sw.getWebView();
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("https://github.com");
            }
        });

    }
}
