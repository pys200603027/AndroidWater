package water.android.io.objetbox;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Note {
    @Id
    public long id;
    public String text;
    public Date date;

    public Note(long id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    @Override
    public String toString() {
        return "id:" + id + ",text:" + text + ",date:" + date.getTime();
    }
}
