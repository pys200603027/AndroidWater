package water.android.io.liquid.ab.strategy;

import water.android.io.liquid.ab.ABModel;

public interface ABStrategy {
    /**
     * AB策略分发判定
     *
     * @param condition AB测试所需要判定的具体参数，比如用户性别，用户ID
     * @param abModel   AB测试数据，描述了AB测试的行为,比如： 用户id尾数在{0，1，3}之内的
     * @return
     */
    boolean dispatchProp(ABModel.ABCondition condition, ABModel abModel);
}
