package network;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import water.android.io.liquid.http.SampleHttp;

/**
 * 针对OKHttp进行试用
 */
public class OkHttpTest {

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
                .add("name", "bug")
                .build();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(testURL).post(requestBody).build();
        Response response = client.newCall(request).execute();

        System.out.println(response.code());
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
     * 测试执行线程时
     */
    @Test
    public void testAyncThread() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Test aync..");
                }
                try {
                    TimeUnit.SECONDS.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
