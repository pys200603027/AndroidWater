package water.android.io.apieyepetizer.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import water.android.io.apieyepetizer.bean.Discovery;
import water.android.io.apieyepetizer.bean.Selected;

public interface EyepetizerService {

    /**
     * 首页
     *
     * @return
     */
    @GET("tabs/selected")
    Observable<Selected> getSelected();

    /**
     * 发现页面
     *
     * @return
     */
    @GET("discovery")
    Observable<Discovery> getDiscovery();

    /**
     * 关注页面
     *
     * @return
     */
    @GET("tabs/follow")
    Observable<ResponseBody> getTabFollow();
}
