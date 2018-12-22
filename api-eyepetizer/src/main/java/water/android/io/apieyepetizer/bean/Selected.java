package water.android.io.apieyepetizer.bean;

import java.util.List;

/**
 * 推荐首页
 */
public class Selected {

    public int count;
    public int total;
    public String nextPageUrl;
    public boolean adExist;
    public long date;
    public long nextPublishTime;
    public Object dialog;
    public Object topIssue;
    public int refreshCount;
    public int lastStartId;

    public List<ItemList> itemList;
}
