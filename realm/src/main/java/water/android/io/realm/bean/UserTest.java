package water.android.io.realm.bean;

import io.realm.Realm;
import water.android.io.realm.bean.User;

public class UserTest {

    /**
     * 第一次使用realm
     */
    public static void test1() {
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
