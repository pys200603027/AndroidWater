package water.android.io.objetbox.chat;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ChatSession extends Session {

    public String last;
    public int left;
}
