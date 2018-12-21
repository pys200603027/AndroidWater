package water.android.io.objetbox.chat;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToOne;

@Entity
public class ChatList {

    @Id
    public long id;

    public ToOne<ChatSession> chatSession;

    public ToOne<PostUser> user;
}
