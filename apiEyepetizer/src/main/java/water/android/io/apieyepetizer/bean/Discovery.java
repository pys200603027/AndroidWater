package water.android.io.apieyepetizer.bean;

import java.util.List;

/**
 * DiscoveryList-> apiURL请求到到数据结构
 */
public class Discovery {
    public int count;
    public int total;
    public String nextPageUrl;
    public boolean adExist;
    public List<ItemListUpper> itemList;


}
