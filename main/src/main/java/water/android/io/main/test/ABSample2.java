package water.android.io.main.test;

import java.util.ArrayList;
import java.util.List;

import water.android.io.liquid.ab.ABHandler;
import water.android.io.liquid.ab.ABModel;
import water.android.io.liquid.ab.test.UnitTestModel;
import water.android.io.liquid.ab.annotation.ABTesting;

public class ABSample2 {
    public static void main(String[] args) {

        testUidAndGender();
        testUidAndGenderIn();
        testUuid();
        testTime();
        testNull();
        testNull_1();
        testNull_2();
        testNull_3();
        testNull_4();
        testAndCondition();
        testPlatform();
        testFlag();
        testCostom();

        System.out.println(ABHandler.parseABModel("abc") == null);
    }


    @ABTesting(prop = {"uid", "gender"}, op = {"out", "in"})
    private static void testUidAndGender() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("134129").setGender("1").setAbTestName("login");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = {"uid", "gender"}, op = {"in", "in"})
    private static void testUidAndGenderIn() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectA());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("134129").setGender("1").setAbTestName("login");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = "uid", op = "out")
    private static void testUuid() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());

        ABHandler abHandler = new ABHandler();
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342349").setGender("1").setAbTestName("login");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = "uid", op = "in")
    private static void testTime() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectD());

        ABHandler abHandler = new ABHandler();
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342349").setGender("1").setAbTestName("login");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull() {
        List<ABModel> abModels = null;
        ABModel.ABCondition condition = null;
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_1() {
        List<ABModel> abModels = null;
        ABModel.ABCondition condition = new ABModel.ABCondition();
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_2() {
        List<ABModel> abModels = new ArrayList<>();
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342349").setGender("1").setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_3() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectE());
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342349").setGender("1").setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_4() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectE());
        ABModel.ABCondition condition = new ABModel.ABCondition();
        condition.setGender(null);
        condition.setUid(null);
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testAndCondition() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectA());
        abModels.add(UnitTestModel.makeObjectD());
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342342").setGender("1").setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testPlatform() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectF());
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342342").setGender("1").setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testFlag() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectG());
        ABModel.ABCondition condition = new ABModel.ABCondition().setUid("13412342342").setGender("1").setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testCostom() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectI());
        ABModel.ABCondition condition = new ABModel.ABCondition()
                .setCostom("4")
                .setAbTestName("login");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    static class TestABInterImpl implements ABHandler.ABAction {
        @Override
        public void run(boolean result) {
            System.out.println("|------------------------------|\nV");
            System.out.println("ABTesting Result:" + result);
            System.out.println("-------------------------------");
        }
    }

    static class TestABErrorImpl implements ABHandler.ABError {

        @Override
        public void throwable(Exception e) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("ABTesting run error:" + e.getMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
    }


}
