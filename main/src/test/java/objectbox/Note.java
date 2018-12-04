package objectbox;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Note {
    @Id
    public long id;

    public String text;

    @Override
    public String toString() {
        return "id:" + id + ",text:" + text;
    }
}
