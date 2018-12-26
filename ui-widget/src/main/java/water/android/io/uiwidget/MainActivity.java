package water.android.io.uiwidget;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.yinglan.keyboard.HideUtil;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.listener.OnResultListener;
import com.zhihu.matisse.ui.ActivityResultHelper;

import cn.jiguang.imui.chatinput.listener.CustomMenuEventListener;
import cn.jiguang.imui.chatinput.menu.Menu;
import cn.jiguang.imui.chatinput.menu.view.MenuFeature;
import cn.jiguang.imui.chatinput.menu.view.MenuItem;
import pub.devrel.easypermissions.EasyPermissions;
import water.android.io.uiwidget.input.CustomInputView;
import water.android.io.uiwidget.input.CustomMenuManager;
import water.android.io.uiwidget.input.GifSizeFilter;
import water.android.io.uiwidget.input.Glide4Engine;
import water.android.io.uiwidget.input.OnMenuClickListenerWrapper;
import water.android.io.uiwidget.input.SimpleInputView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private final int RC_PHOTO = 0x0003;
    private CustomInputView inputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HideUtil.init(this);
        this.mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        this.mWindow = getWindow();

        inputView = findViewById(R.id.simple_input);
        inputView.setMenuContainerHeight(819);

        //Init Matisse
        Matisse.from(this)
                .choose(MimeType.ofImage())
                .countable(false)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .maxSelectable(9)
                .originalEnable(true)
                .maxOriginalSize(10)
                .imageEngine(new Glide4Engine())
                .forCallback(new OnResultListener() {
                    @Override
                    public void onResult(int i, Intent intent) {
                        Log.d("123", "i:" + i);
                    }
                });
        CustomMenuManager menuManager = inputView.getMenuManager();
        menuManager.addCustomMenu("recorder", R.layout.im_menu_voice_item, R.layout.im_menu_voice_feature);
        menuManager.addCustomMenu("photo", R.layout.im_menu_photo_item, R.layout.im_menu_photo_feature);
        // Custom menu order
        menuManager.setMenu(Menu.newBuilder().
                customize(true).
                setBottom("recorder", "photo", Menu.TAG_EMOJI).build());
        menuManager.setCustomMenuClickListener(new CustomMenuEventListener() {
            @Override
            public boolean onMenuItemClick(String tag, MenuItem menuItem) {
                //Menu feature will not be show shown if return false；
                if (tag.equals("photo")) {
                    String[] perms = new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    };

                    if (!EasyPermissions.hasPermissions(MainActivity.this, perms)) {
                        EasyPermissions.requestPermissions(MainActivity.this, "需要获取相册权限", RC_PHOTO, perms);
                    }
                }
                return true;
            }

            @Override
            public void onMenuFeatureVisibilityChanged(int visibility, String tag, MenuFeature menuFeature) {
                if (visibility == View.VISIBLE) {
                    // Menu feature is visible.
                } else {
                    // Menu feature is gone.
                }
            }
        });
        inputView.setMenuClickListener(new OnMenuClickListenerWrapper());
        inputView.setOnTouchListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResultHelper.getInstance().onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private InputMethodManager mImm;
    private Window mWindow;


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (inputView.getMenuState() == View.VISIBLE) {
                    inputView.dismissMenuLayout();
                }
                try {
                    View v = getCurrentFocus();
                    if (mImm != null && v != null) {
                        mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        view.clearFocus();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case MotionEvent.ACTION_UP:
                view.performClick();
                break;
            default:
        }
        return false;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
