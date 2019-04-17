package function.network;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import water.android.io.liquid.bean.RestfulResponse;
import water.android.io.liquid.http.SampleHttp;
import water.android.io.liquid.utils.Log;
import water.android.io.main.bean.OneArticle;

public class RetrofitTest {

    /**
     * Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36
     */
    public final static String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36";

    /**
     * 测试Retrofit
     */
    interface Github {
        @GET("pys200603027/AndroidWater")
        Observable<ResponseBody> getMyGithub();
    }

    /**
     * 每日一篇
     */
    interface OneArticleService {

        @GET("article/today")
        Observable<RestfulResponse<OneArticle>> getOne(@Query("dev") int dev);
    }

    /**
     * Android高清壁纸
     */
    interface AndroidDeskTop {

        @GET("vertical/vertical")
        Observable<ResponseBody> getAdesk(@Query("limit") int limit, @Query("adult") boolean adult, @Query("first") int first, @Query("skip") int skip, @Query("order") String order);
    }

    @Before
    public void setUp() {
        Log.init(new Log.FlatformLog() {
            @Override
            public void d(String msg) {
                System.out.println(msg);
            }
        });
    }

    /**
     * 测试Retrofit基本使用
     * GET
     */
    @Test
    public void test1() {
        SampleHttp.init("https://www.github.com/");
        Retrofit retrofit = SampleHttp.getInstance().getDefaultRetrofit();
        retrofit.create(Github.class)
                .getMyGithub()
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d("onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete");
                    }
                });

    }


    @Test
    public void test2() {
        SampleHttp.init("https://interface.meiriyiwen.com/");
        SampleHttp.getInstance().getDefaultRetrofit()
                .create(OneArticleService.class)
                .getOne(1)
                .subscribe(new Consumer<RestfulResponse<OneArticle>>() {
                    @Override
                    public void accept(RestfulResponse<OneArticle> oneArticleRestfulResponse) throws Exception {

                    }
                });

    }

    /**
     * 更换地址测试
     */
    @Test
    public void test3() {
        //这个是错误地址
        SampleHttp.init("https://www.baidu.com/");
        Retrofit retrofit = SampleHttp.getInstance().getDefaultRetrofit().newBuilder().baseUrl("https://interface.meiriyiwen.com/").build();
        Log.d(retrofit.baseUrl().url().toString());
        retrofit.create(OneArticleService.class)
                .getOne(1)
                .subscribe(new Consumer<RestfulResponse<OneArticle>>() {
                    @Override
                    public void accept(RestfulResponse<OneArticle> oneArticleRestfulResponse) throws Exception {

                    }
                });
    }

    interface InyuApp {
        @FormUrlEncoded
        @POST("v1/login/mobile/code")
        Observable<ResponseBody> getLoginCode(@Field("mobile") String phoneNumber);

        @FormUrlEncoded
        @POST("v1/login/mobile")
        Observable<ResponseBody> login(@Field("mobile") String phoneNumber, @Field("msg_code") String code, @Field("uniq") String uniq);
    }

    @Test
    public void test4() {
        SampleHttp.init("http://39.106.43.35:9761/");

        SampleHttp.getInstance().getDefaultRetrofit()
                .create(InyuApp.class)
                .getLoginCode("13264409010")
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        System.out.println(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });


    }

    @Test
    public void test5() {
        SampleHttp.init("http://39.106.43.35:9761/");
        SampleHttp.getInstance().getDefaultRetrofit()
                .create(InyuApp.class)
                .login("13264409010", "0000", "7282373600253801588")
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Test
    public void test6() throws IOException {
        SampleHttp.init("http://39.106.43.35:9761/");
        OkHttpClient ok = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("http://39.106.43.35:9761/v1/profile?t=YzUzMDk1NTg0N2EwMmEzZWNjNzBmZDc4ODgyODU0NTVlMTkzYTFiOSwxNTQxNTA4NTk4ODUy")
                .get()
                .addHeader("Cookie", "c=\"NWZhYjU0ZjdkMjAwM2Q3YWQ4MDQ4YTVkYjhjNzRmM2IzMzk5ZTc2NywyNTg2MjE3OTM2MjE3NzAyNSwxNTQxNTY3MjgxMzkw\"; Expire=Thursday, 06-Dec-2018 12:49:58 GMT+0000; Path=/; Domain=test_ip")
                .build();

        Response execute = ok.newCall(request).execute();

        System.out.println(execute.isSuccessful());
    }

    @Test
    public void test7() {
        String url = "https://www.baidu.com?t=66";
        String t = "aldfkjasldfasdflasdf";
        if (!url.contains("?")) {
            url = url + "?t=" + t;
        } else if (!url.contains("t=")) {
            url = url + "&t=" + t;
        }

        System.out.println(url);
    }


}
