package water.android.io.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1();
    }

    /**
     * 第一次使用realm
     */
    private void test1() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setAge(18);
                user.setName("Peter");
            }
        });

        User user = realm.where(User.class).equalTo("age", 18).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User tmpUser = realm.where(User.class).equalTo("age", 18).findFirst();
                tmpUser.setAge(19);
            }
        });

        System.out.println("123 user-age:" + user.getAge());
    }
}
