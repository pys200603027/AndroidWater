import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.relation.ToOne;
import water.android.io.objetbox.MyObjectBox;
import water.android.io.objetbox.chat.ChatList;
import water.android.io.objetbox.chat.ChatSession;
import water.android.io.objetbox.chat.PostUser;

/**
 * 1. 不允许同名类（即使在不同包中）
 * 2. 不允许内部类
 * 3. 涉及继承到父类，也需要进行@Entiry
 */
public class ChatBoxTest {
    BoxStore boxStore;

    @Before
    public void setUp() {
        boxStore = MyObjectBox.builder().name("objectbox-chat-db").build();
    }

    @After
    public void tearDown() {
        boxStore.close();
//        boxStore.deleteAllFiles();
    }

    @Test
    public void test1() {

        PostUser user = new PostUser();
        user.avatar = "avatar-1";
        user.name = "test-1";

        ChatSession chatSession = new ChatSession();
        chatSession.fid = "test-0";
        chatSession.gid = "001";
        chatSession.type = "chat";


        ChatList chatList = new ChatList();
        chatList.user.setTarget(user);

        chatList.chatSession.setTarget(chatSession);

        Box<ChatList> chatListBox = boxStore.boxFor(ChatList.class);
        chatListBox.put(chatList);
    }

    @Test
    public void test2() {
        Box<ChatList> chatListBox = boxStore.boxFor(ChatList.class);
        List<ChatList> chatLists = chatListBox.getAll();

        System.out.println(chatLists.size());
        for (ChatList c : chatLists) {
            ToOne<ChatSession> chatSession = c.chatSession;
            ChatSession cs = chatSession.getTarget();
//            PostUser userTarget = c.user.getTarget();
            System.out.println("type:" + cs.type);
        }

    }

}
