package water.android.io.apieyepetizer.bean;

import java.util.List;

/**
 * 列表内容
 */
public class Content {
    public static final String BANNER="Banner";
    public static final String VIDEO_BEAN_FOR_CLIENT="VideoBeanForClient";
    public static final String TEXT_FOOTER="TextFooter";
    public static final String TEXT_HEADER="TextHeader";
    public static final String ITEM_COLLECTION="ItemCollection";

    public String dataType;
    public int id;
    public String title;
    public String description;
    public String library;
    public ConsumptionBean consumption;
    public String resourceType;
    public String slogan;
    public ProviderBean provider;
    public String category;
    public AuthorBean author;
    public CoverBean cover;
    public String playUrl;
    public Object thumbPlayUrl;
    public int duration;
    public WebUrlBean webUrl;
    public long releaseTime;
    public Object campaign;
    public Object waterMarks;
    public Object adTrack;
    public String type;
    public Object titlePgc;
    public Object descriptionPgc;
    public Object remark;
    public boolean ifLimitVideo;
    public int searchWeight;
    public int idx;
    public Object shareAdTrack;
    public Object favoriteAdTrack;
    public Object webAdTrack;
    public long date;
    public Object promotion;
    public Object label;
    public String descriptionEditor;
    public boolean collected;
    public boolean played;
    public Object lastViewTime;
    public Object playlists;
    public Object src;
    public List<TagsBean> tags;
    public List<PlayInfoBean> playInfo;
    public List<?> labelList;
    public List<?> subtitles;

    public static class ConsumptionBean {
        /**
         * collectionCount : 616
         * shareCount : 644
         * replyCount : 21
         */

        public int collectionCount;
        public int shareCount;
        public int replyCount;
    }

    public static class ProviderBean {
        /**
         * name : YouTube
         * alias : youtube
         * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
         */

        public String name;
        public String alias;
        public String icon;
    }

    public static class AuthorBean {
        /**
         * id : 2170
         * icon : http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg
         * name : 开眼动画精选
         * description : 有趣的人永远不缺童心
         * link :
         * latestReleaseTime : 1537405200000
         * videoNum : 652
         * adTrack : null
         * follow : {"itemType":"author","itemId":2170,"followed":false}
         * shield : {"itemType":"author","itemId":2170,"shielded":false}
         * approvedNotReadyVideoCount : 0
         * ifPgc : true
         */

        public int id;
        public String icon;
        public String name;
        public String description;
        public String link;
        public long latestReleaseTime;
        public int videoNum;
        public Object adTrack;
        public FollowBean follow;
        public ShieldBean shield;
        public int approvedNotReadyVideoCount;
        public boolean ifPgc;

        public static class FollowBean {
            /**
             * itemType : author
             * itemId : 2170
             * followed : false
             */

            public String itemType;
            public int itemId;
            public boolean followed;
        }

        public static class ShieldBean {
            /**
             * itemType : author
             * itemId : 2170
             * shielded : false
             */

            public String itemType;
            public int itemId;
            public boolean shielded;
        }
    }

    public static class CoverBean {
        /**
         * feed : http://img.kaiyanapp.com/e989dbc46d84cf2dd5ee60c84e9623b2.png?imageMogr2/quality/60/format/jpg
         * detail : http://img.kaiyanapp.com/e989dbc46d84cf2dd5ee60c84e9623b2.png?imageMogr2/quality/60/format/jpg
         * blurred : http://img.kaiyanapp.com/cf9c657e23deea733f1998b17e1b4ef8.png?imageMogr2/quality/60/format/jpg
         * sharing : null
         * homepage : http://img.kaiyanapp.com/e989dbc46d84cf2dd5ee60c84e9623b2.png?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
         */

        public String feed;
        public String detail;
        public String blurred;
        public Object sharing;
        public String homepage;
    }

    public static class WebUrlBean {
        /**
         * raw : http://www.eyepetizer.net/detail.html?vid=127769
         * forWeibo : http://www.eyepetizer.net/detail.html?vid=127769&resourceType=video&utm_campaign=routine&utm_medium=share&utm_source=weibo&uid=0
         */

        public String raw;
        public String forWeibo;
    }

    public static class TagsBean {
        /**
         * id : 14
         * name : 动画
         * actionUrl : eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB
         * adTrack : null
         * desc : null
         * bgPicture : http://img.kaiyanapp.com/c4e5c0f76d21abbd23c9626af3c9f481.jpeg?imageMogr2/quality/100
         * headerImage : http://img.kaiyanapp.com/88da8548d31005032c37df4889d2104c.jpeg?imageMogr2/quality/100
         * tagRecType : IMPORTANT
         * childTagList : null
         * childTagIdList : null
         */

        public int id;
        public String name;
        public String actionUrl;
        public Object adTrack;
        public Object desc;
        public String bgPicture;
        public String headerImage;
        public String tagRecType;
        public Object childTagList;
        public Object childTagIdList;
    }

    public static class PlayInfoBean {
        /**
         * height : 480
         * width : 854
         * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=127769&resourceType=video&editionType=normal&source=aliyun","size":5748307},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=127769&resourceType=video&editionType=normal&source=qcloud","size":5748307},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=127769&resourceType=video&editionType=normal&source=ucloud","size":5748307}]
         * name : 标清
         * type : normal
         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=127769&resourceType=video&editionType=normal&source=aliyun
         */

        public int height;
        public int width;
        public String name;
        public String type;
        public String url;
        public List<UrlListBean> urlList;

        public static class UrlListBean {
            /**
             * name : aliyun
             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=127769&resourceType=video&editionType=normal&source=aliyun
             * size : 5748307
             */
            public String name;
            public String url;
            public int size;
        }
    }

}
