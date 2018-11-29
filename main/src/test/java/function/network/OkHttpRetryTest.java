package function.network;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okHttp自定义重试
 * 通过拦截器进行设置重试，目前重试没有停顿时间，就是不断重试
 */
public class OkHttpRetryTest {

    public class RetryIntercepter implements Interceptor {
        private int maxRetry;//最大重试次数
        private int retryNum = 0;//假如设置为3次重试的话,则最大可能请求4次(默认1次+3次重试)

        public RetryIntercepter(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            System.out.println("RetryIntercepter: retryNum=" + retryNum);
            Response response = chain.proceed(request);
            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                System.out.println("RetryIntercepter: retryNum=" + retryNum);
                response = chain.proceed(request);
            }
            return response;
        }
    }

    class TestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            System.out.println("TestInterceptor: url=" + url);
            Response response = null;
            if (url.equals(mUrl)) {
                StringBuilder sb = new StringBuilder();
                sb
                        .append("{")
                        .append("\"").append("message").append("\"")
                        .append(":")
                        .append("\"").append("我是模拟的数据").append("\"")
                        .append("}");

                System.out.println("TestInterceptor: " + sb.toString());

                response = new Response.Builder()
                        .code(400)
                        .request(request)
                        .protocol(Protocol.HTTP_1_0)
                        .message("这是一个模拟重试数据")
                        .body(ResponseBody.create(MediaType.parse("application/json"), sb.toString()))
                        .addHeader("content-type", "application/json")
                        .build();
            } else {
                response = chain.proceed(request);
            }
            return response;
        }
    }

    String mUrl = "https://www.baidu.com/";
    OkHttpClient mClient;

    @Before
    public void setUp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .addInterceptor(new RetryIntercepter(3))//重试
                .addInterceptor(logging)//网络日志
                .addInterceptor(new TestInterceptor())//模拟网络请求
                .build();
    }

    @Test
    public void testRequest() throws IOException {
        Request request = new Request.Builder()
                .url(mUrl)
                .build();
        Response response = mClient.newCall(request).execute();
        System.out.println("onResponse:" + response.body().string());
    }


}
