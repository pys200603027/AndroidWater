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


    public static class ItemList {
        /**
         * 有这几种类型
         */
        public static final String BANNER = "banner2";
        public static final String VIDEO = "video";
        public static final String TEXT_FOOTER = "textFooter";
        public static final String VIDEO_COLLECTION_WITH_COVER = "videoCollectionWithCover";
        public static final String VIDEO_COLLECTION_OF_FOLLOW = "videoCollectionOfFollow";

        /**
         * 类型
         */
        public String type;
        public Content data;
        public String tag;
        public int id;
        public int adIndex;
    }
}
