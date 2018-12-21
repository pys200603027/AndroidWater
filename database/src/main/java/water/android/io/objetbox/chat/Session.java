package water.android.io.objetbox.chat;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Session {

    @Id
    public long id;

    public String uid;

    public String gid;

    public String fid;

    public String type;
}
