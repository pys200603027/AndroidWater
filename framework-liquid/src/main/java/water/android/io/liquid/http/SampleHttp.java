package water.android.io.liquid.http;

import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import water.android.io.liquid.utils.Log;

public class SampleHttp {

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    static SampleHttp sampleHttp;

    /**
     * Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36
     */
    public static final String SIMULATOR_USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; HUAWEI GRA-CL00 Build/HUAWEIGRA-CL00) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36";

    private static String baseURL = "";

    private SampleHttp() {
    }

    public static SampleHttp getInstance() {
        if (sampleHttp == null) {
            synchronized (SampleHttp.class) {
                if (sampleHttp == null) {
                    sampleHttp = new SampleHttp();
                }
            }
        }
        return sampleHttp;
    }


    public static void init(String baseUrl) {
        baseURL = baseUrl;
    }

    public static void init(String baseURL, Map<String, String> headers) {

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public OkHttpClient getDefaultOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                /**
                 * log
                 */
                .addInterceptor(getHttpLogInterceptor())
                /**
                 * add agent in header
                 */
                .addInterceptor(getSimulatorUserAgentInterceptor())
                .build();
        return okHttpClient;
    }

    /**
     * User-Agent Interceptor
     *
     * @return
     */
    public static Interceptor getSimulatorUserAgentInterceptor() {
        Interceptor httpHeaderInterceptor = chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", SIMULATOR_USER_AGENT)
                    .build();
            return chain.proceed(request);
        };

        return httpHeaderInterceptor;
    }

    public static Interceptor getHttpLogInterceptor() {
        //打印retrofit日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("retrofitBack = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    public Retrofit getDefaultRetrofit() {
        if (baseURL.isEmpty()) {
            throw new RuntimeException("you must set baseURL by calling init");
        }
        okHttpClient = getDefaultOkHttpClient();
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
}
