package water.android.io.main.test;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import water.android.io.main.Log;
import water.android.io.main.bean.OneArticle;
import water.android.io.main.bean.RestfulResponse;

public class TestHttp {
    /**
     * Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36
     */
    public final static String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36";

    /**
     * @param baseURL
     * @return
     */
    private Retrofit getRetrofit(String baseURL) {
        Interceptor httpHeaderInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .removeHeader("User-Agent")
                        .addHeader("User-Agent", USER_AGENT)
                        .build();
                return chain.proceed(request);
            }
        };
        //打印retrofit日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(httpHeaderInterceptor)
                .build();
        //配置
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 测试Retrofit
     */
    interface Github {

        @GET("pys200603027/AndroidWater")
        Observable<ResponseBody> getMyGithub();
    }

    public void test3() {
        //配置
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl("https://github.com/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        /**
         * 执行
         */
        retrofit.create(Github.class)
                .getMyGithub()
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return responseBody.string();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(s);
            }
        });
    }

    interface OneArticleService {

        @GET("article/today")
        Observable<RestfulResponse<OneArticle>> getOne(@Query("dev") int dev);
    }


    public void testOneArticle() {

        getRetrofit("https://interface.meiriyiwen.com/").create(OneArticleService.class)
                .getOne(1)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("loading...");
                    }
                })
                .subscribe(new Consumer<RestfulResponse<OneArticle>>() {
                    @Override
                    public void accept(RestfulResponse<OneArticle> oneArticleRestfulResponse) throws Exception {
                        OneArticle data = oneArticleRestfulResponse.data;
                        Log.d("finish");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(throwable.getMessage());
                    }
                });
    }

    interface AndroidDeskTop {

        @GET("vertical/vertical")
        Observable<ResponseBody> getAdesk(@Query("limit") int limit, @Query("adult") boolean adult, @Query("first") int first, @Query("skip") int skip, @Query("order") String order);
    }

    public void testAndroidDeskTop() {
        getRetrofit("http://service.picasso.adesk.com/v1/").create(AndroidDeskTop.class)
                .getAdesk(10, true, 0, 180, "new")
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d("loading ...");
                    }
                })
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Log.d(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(throwable.getMessage());
                    }
                });
    }


}
