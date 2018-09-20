package water.android.io.apieyepetizer;

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
import water.android.io.apieyepetizer.bean.Discovery;
import water.android.io.apieyepetizer.bean.Selected;
import water.android.io.apieyepetizer.service.EyepetizerService;
import water.android.io.liquid.http.SampleHttp;
import water.android.io.liquid.utils.Log;

public class EyepetizerMain {

    public static void main(String[] args) {
        String BASE_URL = "http://baobab.kaiyanapp.com/api/v4/";
        SampleHttp.init(BASE_URL);


        EyepetizerService eyepetizerService = SampleHttp.getInstance().getRetrofit()
                .create(EyepetizerService.class);
        /**
         * 首页
         */
        eyepetizerService
                .getSelected()
                .subscribe(new Consumer<Selected>() {
                    @Override
                    public void accept(Selected homePage) throws Exception {
                        List<Selected.ItemList> itemList = homePage.itemList;
                        for (int i = 0; i < itemList.size(); i++) {
                            Selected.ItemList itemListBean = itemList.get(i);
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
                .subscribe(new Consumer<Discovery>() {
                    @Override
                    public void accept(Discovery discovery) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });

        eyepetizerService.getDiscovery()
                .flatMap(new Function<Discovery, ObservableSource<Discovery.TabInfoBean.TabListItem>>() {
                    @Override
                    public ObservableSource<Discovery.TabInfoBean.TabListItem> apply(Discovery discovery) throws Exception {
                        List<Discovery.TabInfoBean.TabListItem> tabList = discovery.tabInfo.tabList;
                        return Observable.fromIterable(tabList);
                    }
                }).map(new Function<Discovery.TabInfoBean.TabListItem, ResponseBody>() {
            @Override
            public ResponseBody apply(Discovery.TabInfoBean.TabListItem tabListItem) throws Exception {
                OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
                Request request = new Request.Builder()
                        .url(tabListItem.apiUrl)
                        .method("GET", null)
                        .build();
                Call call = okHttpClient.newCall(request);
                Response re = call.execute();
                return re.body();
            }
        }).subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }
}
