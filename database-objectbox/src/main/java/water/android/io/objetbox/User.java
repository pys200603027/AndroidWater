package water.android.io.objetbox;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Transient;
import io.objectbox.annotation.Unique;

@Entity
public class User {
    /**
     * 索引
     */
    @Id
    private long id;

    /**
     * 唯一值
     */
    @Unique
    private String name;

    @Transient
    private int tempUsageCount;

    /**
     * 查询uid
     */
    @Index
    private String uid;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(long id, String name, int tempUsageCount, String uid) {
        this.id = id;
        this.name = name;
        this.tempUsageCount = tempUsageCount;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }

    @Override
    public String toString() {
        return "id:" + id + ",name:" + name + ",uid:" + uid+",tempUseCount:"+tempUsageCount;
    }
}
