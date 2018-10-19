package water.android.io.apieyepetizer;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import water.android.io.apieyepetizer.bean.Content;
import water.android.io.apieyepetizer.bean.DiscoveryList;
import water.android.io.apieyepetizer.bean.Discovery;
import water.android.io.apieyepetizer.bean.ItemList;
import water.android.io.apieyepetizer.bean.Selected;
import water.android.io.apieyepetizer.service.EyepetizerService;
import water.android.io.liquid.http.SampleHttp;
import water.android.io.liquid.utils.Log;

public class EyepetizerMain {

    public static void main(String[] args) {
        String BASE_URL = "http://baobab.kaiyanapp.com/api/v4/";
        SampleHttp.init(BASE_URL);


        EyepetizerService eyepetizerService = SampleHttp.getInstance().getDefaultRetrofit().create(EyepetizerService.class);
        /**
         * 首页
         */
        eyepetizerService
                .getSelected()
                .subscribe(new Consumer<Selected>() {
                    @Override
                    public void accept(Selected homePage) throws Exception {
                        List<ItemList> itemList = homePage.itemList;
                        for (int i = 0; i < itemList.size(); i++) {
                            ItemList itemListBean = itemList.get(i);
                            Content data = itemListBean.data;
                            Log.d(data.dataType);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(throwable.getMessage());
                    }
                });
        /**
         * 发现
         */
        eyepetizerService
                .getDiscovery()
                .subscribe(new Consumer<DiscoveryList>() {
                    @Override
                    public void accept(DiscoveryList discovery) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        /**
         * 发现后续
         */
        eyepetizerService.getDiscovery()
                .flatMap(new Function<DiscoveryList, ObservableSource<DiscoveryList.TabInfoBean.TabListItem>>() {
                        @Override
                        public ObservableSource<DiscoveryList.TabInfoBean.TabListItem> apply(DiscoveryList discovery) throws Exception {
                            List<DiscoveryList.TabInfoBean.TabListItem> tabList = discovery.tabInfo.tabList;
                            return Observable.fromIterable(tabList);
                        }
                }).map(new Function<DiscoveryList.TabInfoBean.TabListItem, ResponseBody>() {
            @Override
            public ResponseBody apply(DiscoveryList.TabInfoBean.TabListItem tabListItem) throws Exception {

                OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
                Request request = new Request.Builder()
                        .url(tabListItem.apiUrl)
                        .method("GET", null)
                        .build();
                Call call = okHttpClient.newCall(request);
                Response re = call.execute();
                return re.body();

            }
        }).map(new Function<ResponseBody, Discovery>() {
            @Override
            public Discovery apply(ResponseBody responseBody) throws Exception {
                Gson gson = new Gson();
                Discovery hot = gson.fromJson(responseBody.string(), Discovery.class);
                return hot;
            }
        }).subscribe(new Consumer<Discovery>() {
            @Override
            public void accept(Discovery hot) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }
}
