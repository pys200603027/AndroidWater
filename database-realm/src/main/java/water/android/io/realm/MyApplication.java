package water.android.io.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        // Get a Realm instance for this thread
//        Realm realm = Realm.getDefaultInstance();

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("water.realm")
                .schemaVersion(1)
                .rxFactory(new RealmObservableFactory())
                .build();

        // Use the config
//        Realm realm = Realm.getInstance(config);

        //设置为默认
        Realm.setDefaultConfiguration(config);
    }
}
