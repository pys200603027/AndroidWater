package water.android.io.sampledialog;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import water.android.io.sampledialog.dialog.SampleDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SampleDialog dialog = SampleDialog.newInstance();
//        dialog.show(getSupportFragmentManager(), "tag");
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                finish();
//            }
//        }, 3000);

        BlankFragment blankFragment = BlankFragment.newInstance("", "");
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_contanier, blankFragment).commit();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 3000);
    }


}
