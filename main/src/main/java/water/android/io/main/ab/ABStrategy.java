package water.android.io.main.ab;

import java.util.List;

import water.android.io.main.ab.annotation.ABTesting;
import water.android.io.main.ab.annotation.ABTestingStratedy;

/**
 * AB测试分发策略  <br/>
 */
public class ABStrategy {

    /**
     * 判断用户UID最后一位是否属于values数组中
     *
     * @param condition
     */
    @ABTestingStratedy(name = {"update"})
    @ABTesting(prop = "uid", op = {ABModel.Value.OP_IN, ABModel.Value.OP_OUT, ABModel.Value.OP_EQ})
    public boolean checkUid(String condition, ABModel<String> abModel) {
        List<String> values = abModel.value;
        String lastS = condition.substring(condition.length() - 1, condition.length());
        return commonCheckString(lastS, abModel.op, values);
    }

    /**
     * 通过判断性别进行策略分发
     *
     * @param condition
     * @param abModel
     * @return
     */
    @ABTesting(prop = "gender", op = "eq")
    public boolean checkGender(String condition, ABModel<String> abModel) {
        List<String> values = abModel.value;
        return commonCheckString(condition, abModel.op, values);
    }

    /**
     * 执行该方法的条件 <br/>
     * 1. 判断ABModel.op 参见{@See ABModel.Value} <br/>
     * 2. value类型为String <br/>
     */
    public boolean commonCheckString(String condition, String op, List<String> values) {
        boolean ret = false;
        if (ABModel.Value.OP_EQ.equals(op) || ABModel.Value.OP_IN.equals(op)) {
            ret = ABModel.Op.checkIn(condition, values);
        } else if (ABModel.Value.OP_OUT.equals(op)) {
            ret = ABModel.Op.checkOut(condition, values);
        }
        return ret;
    }
}
