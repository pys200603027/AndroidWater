package water.android.io.apieyepetizer.bean;

import java.util.List;

/**
 * {
 "tabInfo": {
 "tabList": [
 {
 "id": 0,
 "name": "热门",
 "apiUrl": "http://baobab.kaiyanapp.com/api/v4/discovery/hot"
 },
 {
 "id": 1,
 "name": "分类",
 "apiUrl": "http://baobab.kaiyanapp.com/api/v4/discovery/category"
 },
 {
 "id": 2,
 "name": "作者",
 "apiUrl": "http://baobab.kaiyanapp.com/api/v4/pgcs/all"
 }
 ],
 "defaultIdx": 0
 }
 }
 */
public class Discovery {


    /**
     * tabInfo : {"tabList":[{"id":0,"name":"热门","apiUrl":"http://baobab.kaiyanapp.com/api/v4/discovery/hot"},{"id":1,"name":"分类","apiUrl":"http://baobab.kaiyanapp.com/api/v4/discovery/category"},{"id":2,"name":"作者","apiUrl":"http://baobab.kaiyanapp.com/api/v4/pgcs/all"}],"defaultIdx":0}
     */

    public TabInfoBean tabInfo;

    public static class TabInfoBean {
        /**
         * tabList : [{"id":0,"name":"热门","apiUrl":"http://baobab.kaiyanapp.com/api/v4/discovery/hot"},{"id":1,"name":"分类","apiUrl":"http://baobab.kaiyanapp.com/api/v4/discovery/category"},{"id":2,"name":"作者","apiUrl":"http://baobab.kaiyanapp.com/api/v4/pgcs/all"}]
         * defaultIdx : 0
         */

        public int defaultIdx;
        public List<TabListItem> tabList;

        public static class TabListItem {
            /**
             * id : 0
             * name : 热门
             * apiUrl : http://baobab.kaiyanapp.com/api/v4/discovery/hot
             */

            public int id;
            public String name;
            public String apiUrl;
        }
    }
}
