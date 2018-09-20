package water.android.io.androidwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import water.android.io.main.Log;
import water.android.io.main.test.TestHttp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = new WebView(this);
        String userAgentString = webView.getSettings().getUserAgentString();

        Log.d(userAgentString);

//        new TestHttp().testOneArticle();
    }
}
