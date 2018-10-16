package water.android.io.main.ab;

import java.util.List;

public class ABModel<T> {
    /**
     * 平台
     */
    public String platform;
    /**
     * name表示用例在那个功能区域执行，比如login
     */
    public String name;

    /**
     * 过期时间:当前时间只有小于过期时间才生效
     */
    public long expireTime;
    /**
     * 开始时间:当前时间只有大于开始时间用例才生效
     */
    public long startTime;

    /**
     * prop->op->value 为级联关系, prop确定了用例需要用到的策略,见{@see ABStrategyImpl}，op定义了操作，见{@see ABModel.Op},values为操作需要的值
     */
    public String prop;
    public String op;
    public List<T> value;


    /**
     * ABModel使用前对整体检查
     *
     * @param abModel
     * @return
     */
    public static boolean validate(ABModel abModel) {
        return (abModel.checkStartTime() && abModel.checkExpireTime());
    }

    @Deprecated
    public static boolean checkNull(ABModel abModel) {
        return (abModel == null || abModel.value == null || abModel.value.isEmpty());
    }

    /**
     * 检查用例开始时间
     *
     * @return
     */
    public boolean checkStartTime() {
        long now = System.currentTimeMillis();
        System.out.println("123:checkStartTime:" + (now >= startTime));
        return now >= startTime;
    }

    /**
     * 检查用例过期时间
     *
     * @return
     */
    public boolean checkExpireTime() {
        long now = System.currentTimeMillis();
        System.out.println("123:checkExpireTime:" + (now <= expireTime));
        return now <= expireTime;
    }

    /**
     * AB测试需要外部用户传递过来的比对参数
     */
    public static class Condition {
        /**
         * 用户uuid
         */
        private String uid;
        /**
         * 用户性别
         */
        private String gender;

        public String getUid() {
            return uid;
        }

        public Condition setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public Condition setGender(String gender) {
            this.gender = gender;
            return this;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("name:").append(name).append(",");
        sb.append("prop:").append(prop).append(",");
        sb.append("op:").append(op).append(",");
        sb.append("startTime:").append(startTime).append(",");
        sb.append("expireTime:").append(expireTime).append(",");
        sb.append("value:").append(value);
        sb.append("}");
        return sb.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public interface Value {
        /**
         * prop属性可能对应的值
         */
        public static final String PROP_UID = "uid";
        public static final String PROP_FLAG = "flag";
        public static final String PROP_GENDER = "gender";

        /**
         * op属性可能对应的值
         */
        public static final String OP_IN = "in";
        public static final String OP_OUT = "out";
        public static final String OP_EQ = "eq";
        public static final String OP_GREATER = "greater";
        public static final String OP_LESS = "less";
    }


    /**
     * 针对ABModel->Op作出的判断
     */
    public static class Op {
        public static boolean checkIn(String target, List<String> values) {
            System.out.println("123:checkIn target=" + target + ",values:" + values + ",check:" + values.contains(target));
            return values.contains(target);
        }

        public static boolean checkOut(String target, List<String> values) {
            System.out.println("123:checkOut target=" + target + ",values:" + values + ",check:" + !values.contains(target));
            return !values.contains(target);
        }

        public static boolean checkoutGrater(long target, List<Long> values) {
            for (long l : values) {
                if (target > l) {
                    return true;
                }
            }
            return false;
        }

        public static boolean checkoutLess(long target, List<Long> values) {
            for (long l : values) {
                if (target < l) {
                    return true;
                }
            }
            return false;
        }

        public static boolean checkEq(String target, String value) {
            return target.equals(value);
        }
    }
}
