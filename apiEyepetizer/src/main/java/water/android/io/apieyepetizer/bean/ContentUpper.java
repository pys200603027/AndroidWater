package water.android.io.apieyepetizer.bean;

import java.util.List;

/**
 * 多套了一层（热门，分类）
 */
public class ContentUpper {

    public String dataType;
    public HeaderModel header;
    public int count;
    public String adTrack;
    public List<ItemList> itemList;

    public static class HeaderModel {
        /**
         * id : 20
         * title : 音乐
         * font : normal
         * subTitle : 汇聚全球最新、最优质的音乐视频
         * subTitleFont : null
         * textAlign : left
         * cover : null
         * label : null
         * actionUrl : eyepetizer://category/20/?title=%E9%9F%B3%E4%B9%90
         * labelList : null
         * follow : {"itemType":"category","itemId":20,"followed":false}
         */

        public int id;
        public String title;
        public String font;
        public String subTitle;
        public String subTitleFont;
        public String textAlign;
        public String cover;
        public String label;
        public String actionUrl;
        public String labelList;
        public Follow follow;
    }

}
