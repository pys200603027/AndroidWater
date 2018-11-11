package water.android.io.androidwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.socks.library.KLog;

import water.android.io.liquid.utils.Log;
import water.android.io.uikit.view.webview.SampleWebView;
import water.android.io.uikit.view.webview.ScrollChangeSampleWebView;

public class MainActivity extends AppCompatActivity {

    private String s = "<p style=\\\"font-size: 14px;\\\">您在游戏中<span style=\\\"font-size: 16px;color: #FF3C7F;\\\">「<strong> 违规作弊</strong>」</span>已被举报，经查明属实，给予<span style=\\\"font-size: 16px;color: #FF3C7F;\\\">「封禁」</span>，请文明游戏，情节恶劣者将采取封号处理。</p>";

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

        TextView textView = findViewById(R.id.hello);
        textView.setText(Html.fromHtml(s));

    }
}
