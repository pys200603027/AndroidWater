package water.android.io.liquid.ab;

import java.util.List;

public class ABModel {
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
    public List<String> value;


    /**
     * ABModel使用前对整体检查
     *
     * @return
     */
    public boolean validate() {
        return (checkStartTime() && checkExpireTime());
    }

    public boolean checkFlatForm() {
        if (platform == null || platform.isEmpty() || Value.FLATFORM_ANDROID.equals(platform) || Value.FLATFORM_ALL.equals(platform)) {
            return true;
        }
        return false;
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
    public static class ABCondition {

        /**
         * 该AB测试
         */
        private String AbTestName;
        /**
         * 用户uuid
         */
        private String uid;
        /**
         * 用户性别
         */
        private String gender;

        private String costom;

        /**
         * 用户创建时间
         */
        private String userCreateTime;


        public String getCostom() {
            return costom;
        }

        public ABCondition setCostom(String costom) {
            this.costom = costom;
            return this;
        }

        public String getAbTestName() {
            return AbTestName;
        }

        public ABCondition setAbTestName(String abTestName) {
            AbTestName = abTestName;
            return this;
        }

        public String getUid() {
            return uid;
        }

        public ABCondition setUid(String uid) {
            this.uid = uid;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public ABCondition setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public String getUserCreateTime() {
            return userCreateTime;
        }

        public ABCondition setUserCreateTime(String userCreateTime) {
            this.userCreateTime = userCreateTime;
            return this;
        }
    }

    /**
     * 检查ABModel中的values是否可以转化为Int
     */
    public boolean checkIsParseToInt() {
        return (op.equals(ABModel.Value.OP_LESS) || op.equals(ABModel.Value.OP_GREATER));
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
        String PROP_UID = "uid";
        String PROP_FLAG = "flag";
        String PROP_GENDER = "gender";
        String PROP_CUSTOM = "custom";
        String PROP_USER_CREATE_TIME = "userCreateTime";


        /**
         * op属性可能对应的值
         */
        String OP_IN = "include";
        String OP_OUT = "exclude";
        String OP_EQ = "eq";
        String OP_GREATER = "gt";
        String OP_LESS = "lt";

        String FLATFORM_ANDROID = "android";
        String FLATFORM_ALL = "all";
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
            for (Long l : values) {
                if (target > l) {
                    return true;
                }
            }
            return false;
        }

        public static boolean checkoutLess(long target, List<Long> values) {
            for (Long l : values) {
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
