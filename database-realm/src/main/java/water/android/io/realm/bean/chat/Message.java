package water.android.io.realm.bean.chat;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 聊天消息
 */
public class Message extends RealmObject {

    public String _class;
    //text
    public String type;
    public String text;

    public String fid;
    @PrimaryKey
    public String gid;

    public String uid;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type").append(type);
        sb.append(",");
        sb.append("text:").append(text);
        sb.append(",");
        sb.append("gid:").append(gid);
        sb.append(",");
        sb.append("uid:").append(uid);
        sb.append(",");
        sb.append("fid:").append(fid);
        return sb.toString();
    }
}
