package water.android.io.main.ab;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import water.android.io.main.ab.exception.ExecuteTimeErrorException;
import water.android.io.main.ab.strategy.ABStrategy;
import water.android.io.main.ab.strategy.ABStrategyImpl;

/**
 * A/B测试分发
 */
public class ABHandler {
    private ABStrategy abStrategy = new ABStrategyImpl();

    public void dispatch(ABModel.Condition condition, String json, ABAction abAction, ABError abError) {
        List<ABModel> unfilterABlist = parseABModel(json);
        dispatch(condition, unfilterABlist, abAction, abError);
    }

    /**
     * AB测试分发逻辑
     *
     * @param condition      A/B测试所需要传入的比较参数,比如真实的用户uid，用户性别
     * @param unfilterABlist 访问服务器返回的A/B测试描述ABModel对象
     * @param abAction       执行接口
     */
    public void dispatch(ABModel.Condition condition, List<ABModel> unfilterABlist, ABAction abAction, ABError abError) {
        try {
            if (condition == null) {
                throw new NullPointerException("Condition instance is null.");
            }

            if (unfilterABlist == null || unfilterABlist.isEmpty()) {
                throw new NullPointerException("ABModel instance is null.");
            }
            //剔除不在ABTesting时间范围内的用例
            List<ABModel> abModelsFilters = new ArrayList<>();
            for (ABModel abModel : unfilterABlist) {
                boolean timeValidated = ABModel.validate(abModel);
                if (timeValidated) {
                    abModelsFilters.add(abModel);
                }
            }
            //如果所有用例都不在测试时间内，返回错误
            if (abModelsFilters.isEmpty()) {
                throw new ExecuteTimeErrorException("Not in abTesting time");
            }

            boolean ret = true;
            for (ABModel abModel : abModelsFilters) {
                System.out.println("after filter: " + abModel);
                ret = ret & abStrategy.dispatchProp(condition, abModel);
            }
            if (abAction != null) {
                abAction.run(ret);
            }
        } catch (Exception e) {
            if (abError != null) {
                abError.throwable(e);
            }
        }

    }

    /**
     * 解析json
     *
     * @param json
     * @return
     */
    public static List<ABModel> parseABModel(String json) {
        try {
            Gson gson = new Gson();
            List<ABModel> list = gson.fromJson(json, new TypeToken<List<ABModel>>() {
            }.getType());
            return list;
        } catch (Exception e) {
            return null;
        }
    }


    public interface ABAction {
        void run(boolean result);
    }

    public interface ABError {
        void throwable(Exception e);
    }

}
