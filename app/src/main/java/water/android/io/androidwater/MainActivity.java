package water.android.io.androidwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.socks.library.KLog;
import water.android.io.liquid.utils.Log;

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

        WebView webView = new WebView(this);
        String userAgentString = webView.getSettings().getUserAgentString();

//        new TestHttp().testOneArticle();
    }
}
