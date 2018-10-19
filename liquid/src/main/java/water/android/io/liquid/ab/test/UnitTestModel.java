package water.android.io.liquid.ab.test;

import java.util.ArrayList;

import water.android.io.liquid.ab.ABModel;

/**
 * 测试用例
 */
public class UnitTestModel {

    public static ABModel<String> makeObjectA() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "uid";
        expParam.op = "include";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1542193331000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("0");
        expParam.value.add("1");
        expParam.value.add("2");

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectB() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "uid";
        expParam.op = "exclude";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1542193331000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("0");
        expParam.value.add("1");
        expParam.value.add("2");
        expParam.value.add("3");

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectC() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "update";
        expParam.prop = "gender";
        expParam.op = "in";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1542193331000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("1");

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectD() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "gender";
        expParam.op = "include";
        expParam.startTime = 1539473051000L;
        expParam.expireTime = 1539473051000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("1");

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectE() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "gender";
        expParam.op = "include";
        expParam.startTime = 1539473051000L;
        expParam.expireTime = 1542193331000L;
        expParam.value = new ArrayList<>();

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectF() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "uid";
        expParam.op = "include";
        expParam.platform = "ios";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1542193331000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("0");
        expParam.value.add("1");
        expParam.value.add("2");

        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectG() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "flag";
        expParam.op = "eq";
        expParam.platform = "all";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1545047014000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("false");
        System.out.println(expParam);
        return expParam;
    }

    public static ABModel<String> makeObjectI() {
        ABModel<String> expParam = new ABModel();
        expParam.expireTime = 123123123;
        expParam.name = "login";
        expParam.prop = "custom";
        expParam.op = "gt";
        expParam.platform = "all";
        expParam.startTime = 1539504131000L;
        expParam.expireTime = 1545047014000L;
        expParam.value = new ArrayList<>();
        expParam.value.add("3");
        System.out.println(expParam);
        return expParam;
    }

}
