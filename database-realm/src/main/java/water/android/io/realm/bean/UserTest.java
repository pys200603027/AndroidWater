package water.android.io.realm.bean;

import com.socks.library.KLog;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import water.android.io.realm.bean.User;

public class UserTest {

    /**
     * 第一次使用realm
     */
    public static void test1() {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                KLog.d("123", "current Thread:" + Thread.currentThread().getName());
                User user = realm.createObject(User.class);
                user.setAge(18);
                user.setName("Peter");

                User tmpUser = realm.where(User.class).equalTo("age", 18).findFirst();
                tmpUser.setAge(19);
                System.out.println("123 user-age:" + user.getAge());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                KLog.d("123", "current Thread:" + Thread.currentThread().getName());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
    }

    public static void test2() {
        Realm realm = Realm.getDefaultInstance();
        Disposable disposable =
                realm.where(User.class).equalTo("age", 18)
                        .findAllAsync()
                        .asFlowable()
                        .map(users -> {
                            KLog.d("123", "CurrentThread:" + Thread.currentThread().getName());
                            return new Object();
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(o -> {

                        });
    }

    public static void test3() {

        Realm.getDefaultInstance()
                .asFlowable()
                .flatMap(new Function<Realm, Publisher<List<User>>>() {
                    @Override
                    public Publisher<List<User>> apply(Realm realm) throws Exception {
                        KLog.d("123", "CurrentThread:" + Thread.currentThread().getName());
                        RealmResults<User> age = realm.where(User.class).equalTo("age", 18).findAll();

                        List<User> users = realm.copyFromRealm(age);
                        Flowable<List<User>> just = Flowable.just(users);

                        return just;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {

                    }
                });

    }

    public static void test4() {

    }
}
