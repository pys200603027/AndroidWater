package water.android.io.realm.bean.chat;

import java.util.ArrayList;
import java.util.List;

public class ChatDataFactory {


    public static List<Session> makeSessionTest1() {
        List<Session> sessions = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Session session = new Session();
            session.gid = i + 1000 + "";
            session.fid = i + 1 + "";
            session.nick = "nickName" + i;
            session.uid = i + 10000 + "";

            sessions.add(session);
        }

        return sessions;
    }


    public static List<Message> makeMessageTest1() {
        List<Message> messages = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Message message = new Message();

            message.type = "chat";
            message.text = "Text test" + i;
            message.fid = 0 + "";
            message.gid = 1000 + "";
            message.uid = 10000 + "";

            messages.add(message);
        }

        return messages;
    }
}
