package water.android.io.eyepetizer;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;

/**
 * Splash Activity
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final TextView splashText = findViewById(R.id.splash);
        splashText.post(new Runnable() {
            @Override
            public void run() {
                splashText.setAlpha(0f);
                splashText.animate()
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                        }).setDuration(1200)
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .alpha(1f)
                        .setInterpolator(new DecelerateInterpolator())
                        .start();
            }
        });
    }
}
