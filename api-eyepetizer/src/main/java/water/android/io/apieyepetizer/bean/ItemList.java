package water.android.io.apieyepetizer.bean;

public class ItemList {
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
