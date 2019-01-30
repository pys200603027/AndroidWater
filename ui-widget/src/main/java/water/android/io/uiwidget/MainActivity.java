package water.android.io.uiwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yinglan.keyboard.HideUtil;
import water.android.io.uiwidget.input.SimpleInputView;

public class MainActivity extends AppCompatActivity {

    private SimpleInputView inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HideUtil.init(this);

        inputView = findViewById(R.id.simple_input);
    }

}
