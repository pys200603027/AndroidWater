package water.android.io.main.test;

import java.util.ArrayList;
import java.util.List;

import water.android.io.main.ab.ABHandler;
import water.android.io.main.ab.ABModel;
import water.android.io.main.ab.UnitTestModel;
import water.android.io.main.ab.annotation.ABTesting;

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

        System.out.println(ABHandler.parseABModel("abc") == null);
    }


    @ABTesting(prop = {"uid", "gender"}, op = {"out", "in"})
    private static void testUidAndGender() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("134129").setGender("1");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = {"uid", "gender"}, op = {"in", "in"})
    private static void testUidAndGenderIn() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectA());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("134129").setGender("1");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = "uid", op = "out")
    private static void testUuid() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());

        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    @ABTesting(prop = "uid", op = "in")
    private static void testTime() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectD());

        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull() {
        List<ABModel> abModels = null;
        ABModel.Condition condition = null;
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_1() {
        List<ABModel> abModels = null;
        ABModel.Condition condition = new ABModel.Condition();
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_2() {
        List<ABModel> abModels = new ArrayList<>();
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_3() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectE());
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testNull_4() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectE());
        ABModel.Condition condition = new ABModel.Condition();
        condition.setGender(null);
        condition.setUid(null);
        ABHandler abHandler = new ABHandler();
        abHandler.dispatch(condition, abModels, new TestABInterImpl(), new TestABErrorImpl());
    }

    private static void testAndCondition(){
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectA());
        abModels.add(UnitTestModel.makeObjectD());
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342342").setGender("1");
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
