package water.android.io.uiwidget.input;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import water.android.io.uiwidget.R;

/**
 * 聊天简单输入功能
 */
public class SimpleInputView extends RelativeLayout implements ViewTreeObserver.OnDrawListener, ViewTreeObserver.OnPreDrawListener {
    EditText editText;
    TextView sendView;
    OnSendClickListener sendClickListener;
    OnSendTouchListener onSendTouchListener;


    RelativeLayout recordMenu;
    RelativeLayout photoMenu;
    RelativeLayout menuContainer;

    public SimpleInputView(Context context) {
        super(context);
        initView(context);
    }

    public SimpleInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        getViewTreeObserver().addOnPreDrawListener(this);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.im_item_input_view, this);
        editText = findViewById(R.id.et_input);
        sendView = findViewById(R.id.send);
        sendView.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();

            if (TextUtils.isEmpty(text)) {
                Toast.makeText(getContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                return;
            }

            editText.setText("");
            if (sendClickListener != null) {
                sendClickListener.onSendText(text);
            }
        });
        //输入框时滑动到最后一行
        editText.setOnTouchListener((v, event) -> {
            if (onSendTouchListener != null) {
                onSendTouchListener.onEditTouch();
            }
            return false;
        });


        recordMenu = findViewById(R.id.menu_record);
        photoMenu = findViewById(R.id.menu_photo);
        menuContainer = findViewById(R.id.menu_container);

        findViewById(R.id.voice).setOnClickListener(v -> {
            menuContainer.setVisibility(VISIBLE);
            recordMenu.setVisibility(VISIBLE);
            photoMenu.setVisibility(GONE);
        });
        findViewById(R.id.tu).setOnClickListener(v -> {
            menuContainer.setVisibility(VISIBLE);
            recordMenu.setVisibility(GONE);
            photoMenu.setVisibility(VISIBLE);
        });

    }

    public void setOnSendClickListener(OnSendClickListener sendClickListener) {
        this.sendClickListener = sendClickListener;
    }

    public void setOnSendTouchListener(OnSendTouchListener onSendTouchListener) {
        this.onSendTouchListener = onSendTouchListener;
    }


    //////////////////
    //////////////////
    @Override
    public void onDraw() {

    }

    //////////////////
    //////////////////
    @Override
    public boolean onPreDraw() {
        return false;
    }


    public interface OnSendClickListener {
        void onSendText(String o);
    }

    public interface OnSendTouchListener {
        void onEditTouch();
    }
}
