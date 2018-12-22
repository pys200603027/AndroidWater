package water.android.io.eyepetizer;

import android.app.Application;

import com.socks.library.KLog;

import water.android.io.liquid.utils.Log;

public class EyeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.init(new Log.FlatformLog() {
            @Override
            public void d(String msg) {
                KLog.d("123", msg);
            }
        });
    }
}
