package water.android.io.realm.bean.chat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 聊天列表
 */
public class Session extends RealmObject {

    public String uid;

    @PrimaryKey
    public String gid;

    public String fid;

    //

    public String nick;


    public String avatar;
    public String luid;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("uid:").append(uid);
        sb.append(",");
        sb.append("fid:").append(fid);
        sb.append(",");
        sb.append("gid:").append(gid);
        sb.append(",");
        sb.append("nick:").append(nick);

        return sb.toString();
    }
}
