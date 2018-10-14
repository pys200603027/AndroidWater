package water.android.io.main.ab;


import java.util.List;

/**
 * A/B测试分发
 */
public class ABHandler {
    private ABStrategy abStrategy = new ABStrategy();

    /**
     * @param condition   A/B测试所需要传入的比较参数,比如真实的用户uid，用户性别
     * @param abModels    访问服务器返回的A/B测试描述ABModel对象
     * @param abInterface 执行接口
     */
    public void dispater(ABModel.Condition condition, List<ABModel> abModels, ABInterface abInterface) {
        boolean ret = true;
        for (ABModel abModel : abModels) {
            if (!ABModel.validate(abModel)) {
                ret = ret & false;
                System.out.println("123:dispater ABModel validate=false");
                continue;
            }
            ret = ret & dispatchProp(condition, abModel);
        }
        if (ret && abInterface != null) {
            abInterface.runTestA();
        } else {
            abInterface.runTestB();
        }
    }


    /**
     * 根据ABModel->prop属性判断执行对策略
     *
     * @param condition
     * @param abModel
     * @return
     */
    private boolean dispatchProp(ABModel.Condition condition, ABModel abModel) {
        boolean ret = false;
        /**
         * 通过属性prop分发到不同执行策略
         */
        if (abModel.prop.equals(ABModel.Value.PROP_UID)) {
            ABModel<String> tmpModle = abModel;
            ret = abStrategy.checkUid(condition.getUid(), tmpModle);
            System.out.println("123:dispatchProp->checkUid=" + ret);
        } else if (abModel.prop.equals(ABModel.Value.PROP_GENDER)) {
            ABModel<String> tmpModle = abModel;
            ret = abStrategy.checkGender(condition.getGender(), tmpModle);
            System.out.println("123:dispatchProp->checkGender=" + ret);
        }
        return ret;
    }

    public interface ABInterface {
        void runTestA();

        void runTestB();
    }
}
