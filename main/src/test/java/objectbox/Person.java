package objectbox;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;

@Entity
public class Person {
    @Id
    private long id;
    private String first_name;

    @Index
    private long uid;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstNname() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }
}
