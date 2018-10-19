package network;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 针对OKHttp进行试用
 */
public class OkHttpTest {

    private String testURL = "https://www.baidu.com";
    private String json = "{}";

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
}
