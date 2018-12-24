package water.android.io.realm.bean.chat;

import com.socks.library.KLog;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;
import water.android.io.realm.bean.chat.ChatDataFactory;
import water.android.io.realm.bean.chat.Session;

public class SessionTest {

    /**
     * 拿到现实数据，保存到数据库中
     */
    public static void test1() {
        List<Session> sessions = ChatDataFactory.makeSessionTest1();

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                /**
                 * 列表加入到数据库
                 */
                realm.insertOrUpdate(sessions);


                RealmResults<Session> all = realm.where(Session.class).findAll();
                KLog.d("123", all);
            }
        });
    }

    public static void test2() {
        Realm.getDefaultInstance()
                .asFlowable()
                .map(new Function<Realm, Object>() {
                    @Override
                    public Object apply(Realm realm) throws Exception {
                        KLog.d("123", "CurrentThread :" + Thread.currentThread());
                        return new Object();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {

                    @Override
                    public void accept(Object o) throws Exception {

                    }
                });
    }
}
