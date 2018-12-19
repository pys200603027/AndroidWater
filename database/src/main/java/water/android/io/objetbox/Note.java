package water.android.io.objetbox;

import java.util.Date;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

@Entity
public class Note {
    @Id
    public long id;
    public String text;
    public Date date;

    @Transient
    public InnerNote innerNote;

    public Note(long id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public InnerNote getInnerNote() {
        return innerNote;
    }

    public void setInnerNote(InnerNote innerNote) {
        this.innerNote = innerNote;
    }

    @Override
    public String toString() {
        return "id:" + id + ",text:" + text + ",date:" + date.getTime() + ",innerNode:" + innerNote.getInnerText();
    }

    public static class InnerNote {
        private long id = 0;
        private String innerText;

        public InnerNote(String innerText) {
            this.innerText = innerText;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getInnerText() {
            return innerText;
        }

        public void setInnerText(String innerText) {
            this.innerText = innerText;
        }
    }
}
