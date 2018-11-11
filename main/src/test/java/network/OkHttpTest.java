package network;

import com.google.gson.Gson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import water.android.io.liquid.http.SampleHttp;

/**
 * 针对OKHttp进行试用
 */
public class OkHttpTest {

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

    private String testURL = "https://www.baidu.com";
    private String json = "{}";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * 简单展示了Get请求，同步执行
     */
    @Test
    public void testGet1() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(testURL).build();
        Call call = client.newCall(request);
        Response response = call.execute();

        assertTrue("返回200、300", response.isSuccessful());
    }


    /**
     * 展示Post一个Json字符串,同步执行
     */
    @Test
    public void testPostJson2() throws IOException {
        MediaType jsonType = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(jsonType, json);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(testURL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        System.out.println(response.code());
        assertTrue(response.isSuccessful());
    }

    /**
     * Post 键值对
     */
    @Test
    public void testPostForm3() throws IOException {
        FormBody requestBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        OkHttpClient client = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();

        assertTrue(response.isSuccessful());
    }

    /**
     * 异步执行Get
     */
    @Test
    public void testGetAsync4() {
        OkHttpClient client = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder().url(testURL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
                assertEquals("不会执行", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assertTrue(response.isSuccessful());
            }
        });


        synchronized (client) {
            try {
                client.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试超时
     * SocketTimeoutException
     */
    @Test
    public void testTimeOut5() throws SocketTimeoutException, IOException {
//        expectedException.expect(SocketTimeoutException.class);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MILLISECONDS)
                .readTimeout(100, TimeUnit.MILLISECONDS)
                .writeTimeout(100, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder().url(testURL).build();
        Response response = client.newCall(request).execute();
        System.out.println(response.code());

        assertTrue(response.isSuccessful());
    }

    /**
     * 测试返回错误,理解OKHttp返回onFailure
     * ConnectException
     */
    @Test
    public void testFail6() throws ConnectException, IOException {

        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("http://127.0.0.1:8080/bug")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:" + e.getMessage());
                expectedException.expect(ConnectException.class);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse:" + response.code());
                assertThat("<html >", containsString("<html"));
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试返回错误,理解OKHttp返回onFailure
     * UnknownHostException
     */
    @Test
    public void testFail7() {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.66game.com")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure:" + e.getMessage());
                expectedException.expect(UnknownHostException.class);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse:" + response.code());
                assertThat("<html >", containsString("<html"));
            }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 官方Demo，关于返回值String
     * 对小文件来说string()方法响应实体是方便和高效的.但如果响应实体很大(大于1 MiB),避免使用string(),因为它会将整个文档加载到内存中.在这种情况下,更倾向于用流处理实体
     */
    @Test
    public void testString8() throws IOException {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = okHttpClient.newCall(request).execute();

        assertTrue(response.isSuccessful());

        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i) + ": " + headers.value(i));
        }

        System.out.println(response.body().toString());
    }

    /**
     * 官方Demo
     * 设置Http头信息
     */
    @Test
    public void testHeader9() throws IOException {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        Response response = okHttpClient.newCall(request).execute();

        assertTrue(response.isSuccessful());

        System.out.println("Server: " + response.header("Server"));
        System.out.println("Date: " + response.header("Date"));
        System.out.println("Vary: " + response.header("Vary"));
    }

    /**
     * 官方Demo：交一个markdown文档发送给web服务
     * 扩展：提交Json
     */
    @Test
    public void testPostString10() throws IOException {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        MediaType typeMarkDown = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(typeMarkDown, postBody))
                .build();

        Response response = okHttpClient.newCall(request).execute();

        assertTrue(response.isSuccessful());

    }

    /**
     * 官方Demo：作为一个流post到web服务器
     */
    @Test
    public void testPostStream11() throws IOException {
        MediaType typeMarkDown = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        /**
         * 关键点在于构建requestBody
         */
        RequestBody requestBody = new RequestBody() {
            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }

            @Override
            public MediaType contentType() {
                return typeMarkDown;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");

                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        assertTrue(response.isSuccessful());
    }

    /**
     * 关键字:retryOnConnectionFailure
     */
    @Test
    public void testRetry12() throws IOException {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response response = okHttpClient.newCall(request).execute();

        assertTrue(response.isSuccessful());


    }

    /**
     * 对一些请求进行特殊定制okHttp
     */
    @Test
    public void testNewBuilder13() {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient().newBuilder()
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Call对象表示一个已经准备好可以执行的请求，用这个对象可以查询请求的执行状态，或者取消当前请求
     */
    @Test
    public void testCall14() throws IOException {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Call call = okHttpClient.newCall(request);

        Response response = call.execute();

        assertTrue(call.isExecuted());

        if (call.isExecuted()) {
            Call reCall = okHttpClient.newCall(call.request());
            Response re = reCall.execute();
            assertTrue(re.isSuccessful());

        }
    }

    /**
     * 通过Gson解析json数据
     */
    @Test
    public void testParseJson15() throws IOException {
        OkHttpClient okHttpClient = SampleHttp.getInstance().getDefaultOkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        Response response = okHttpClient.newCall(request).execute();

        assertTrue(response.isSuccessful());

        Gson gson = new Gson();
        Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }

    /**
     * 官方Demo
     * Cache设置
     */
    @Test
    public void testCacheControl16() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "build" + File.separator + "cache";
        File cacheDir = new File(path);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
        assertTrue(cacheDir.exists());
        int cacheSize = 10 * 1024 * 1024;//10MB
        Cache cache = new Cache(cacheDir, cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(SampleHttp.getHttpLogInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .header("Cache-Control", "max-stale=3600")
                .build();
        Response response1 = okHttpClient.newCall(request).execute();

        assertTrue(response1.isSuccessful());

        String response1Body = response1.body().string();
        System.out.println("Response 1 response:          " + response1);
        System.out.println("Response 1 cache response:    " + response1.cacheResponse());
        System.out.println("Response 1 network response:  " + response1.networkResponse());

        Response response2 = okHttpClient.newCall(request).execute();

        assertTrue(response2.isSuccessful());

        String response2Body = response2.body().string();
        System.out.println("Response 2 response:          " + response2);
        System.out.println("Response 2 cache response:    " + response2.cacheResponse());
        System.out.println("Response 2 network response:  " + response2.networkResponse());

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }

    /**
     * 监听下载进度解决方案
     * https://github.com/JessYanCoding/ProgressManager/blob/master/README-zh.md
     */
    @Test
    public void testOkHttpProgress17() {

    }

    /**
     * Retrofit+Rxjava+okhttp终极封装(Gson方案)
     * https://github.com/wzgiceman/RxjavaRetrofitDemo-master
     */
    @Test
    public void testOkHttpWrapper18() {

    }


}
