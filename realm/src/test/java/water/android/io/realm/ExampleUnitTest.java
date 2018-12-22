package water.android.io.realm;

import org.junit.Test;

import java.util.List;

import water.android.io.realm.bean.chat.ChatDataFactory;
import water.android.io.realm.bean.chat.Message;
import water.android.io.realm.bean.chat.Session;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testFactory1(){
        List<Session> sessions = ChatDataFactory.makeSessionTest1();

        System.out.println(sessions);
    }

    @Test
    public void testFacotry2(){
        List<Message> messages = ChatDataFactory.makeMessageTest1();

        System.out.println(messages);
    }
}