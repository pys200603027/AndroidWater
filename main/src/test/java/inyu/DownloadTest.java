package inyu;

import org.junit.Test;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import water.android.io.liquid.bean.RestfulResponse;
import water.android.io.liquid.http.SampleHttp;

public class DownloadTest {

    interface DownloadService {

        @GET("yinyu_app_resources_v1.json")
        Observable<RestfulResponse<AppResource>> getAppResource();
    }

    @Test
    public void test1() {
        String url = "https://static.inyuapp.com/";

        SampleHttp.init(url);
        SampleHttp
                .getInstance()
                .getDefaultRetrofit()
                .create(DownloadService.class)
                .getAppResource()
                .subscribe(response -> {
                    AppResource data = response.data;
                    List<AppResource.Audio> audios = data.getAudios();
                    System.out.println(audios.size());
                }, throwable -> throwable.printStackTrace());
    }
}
