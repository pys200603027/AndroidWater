package water.android.io.realm.bean.chat;

import java.util.List;

import io.realm.Realm;

public class MessageTest {

    /**
     * 根据gid获取最新聊天数据
     */
    public static void test1() {
        List<Message> messages = ChatDataFactory.makeMessageTest1();

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                /**
                 * 每次获取的最新聊天数据都进行数据存储
                 */
                realm.insertOrUpdate(messages);
            }
        });
    }
}
