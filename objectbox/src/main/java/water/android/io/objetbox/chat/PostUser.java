package water.android.io.objetbox.chat;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class PostUser {
    @Id
    public long id;
    public String name;
    public String avatar;
}
