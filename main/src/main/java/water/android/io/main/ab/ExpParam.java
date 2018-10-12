package water.android.io.main.ab;

import java.util.ArrayList;
import java.util.List;

public class ExpParam {

    public String name;
    public String prop;
    public String op;
    public long expireTime;
    public List<String> value;

    public static String testData() {
        return "[{\"name\":\"login\",\"prop\":\"uid\",\"op\":\"left\",\"expireTime\":123123123,\"value\":[\"value1\",\"value2\",\"value3\"]},{\"name\":\"gentle\",\"prop\":\"out\",\"op\":\"operation\",\"expireTime\":123123123,\"value\":[\"value1\",\"value2\",\"value3\"]},{\"name\":\"login\",\"prop\":\"city\",\"op\":\"in\",\"expireTime\":123123123,\"value\":[\"value1\",\"value2\",\"value3\"]}]";
    }

    public static ExpParam makeObject() {
        ExpParam expParam = new ExpParam();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.op = "1";
        expParam.prop = "a";
        expParam.value = new ArrayList<>();
        expParam.value.add("value1");
        expParam.value.add("value2");
        expParam.value.add("value3");
        return expParam;
    }

    public boolean checkExpireTime() {
        return true;
    }
}
