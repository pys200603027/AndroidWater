package water.android.io.main.test;

import java.util.ArrayList;
import java.util.List;

import water.android.io.main.ab.ABHandler;
import water.android.io.main.ab.ABModel;
import water.android.io.main.ab.UnitTestModel;
import water.android.io.main.ab.annotation.ABTesting;

public class ABSample2 {
    public static void main(String[] args) {
//        Gson gson = new Gson();
//        List<ABModel> list = gson.fromJson(ABModel.testData(), new TypeToken<List<ABModel>>() {
//        }.getType());
//
        testUidAndGender();
        testUidAndGenderIn();
        testUuid();
        testTime();
    }

    @ABTesting(prop = {"uid", "gender"}, op = {"out", "in"})
    private static void testUidAndGender() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("134129").setGender("1");
        abHandler.dispater(condition, abModels, new TestABInterImpl());
    }

    @ABTesting(prop = {"uid", "gender"}, op = {"in", "in"})
    private static void testUidAndGenderIn() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectA());
        abModels.add(UnitTestModel.makeObjectC());
        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("134129").setGender("1");
        abHandler.dispater(condition, abModels, new TestABInterImpl());
    }

    @ABTesting(prop = "uid", op = "out")
    private static void testUuid() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectB());

        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        abHandler.dispater(condition, abModels, new TestABInterImpl());
    }

    @ABTesting(prop = "uid", op = "in")
    private static void testTime() {
        List<ABModel> abModels = new ArrayList<>();
        abModels.add(UnitTestModel.makeObjectD());

        ABHandler abHandler = new ABHandler();
        ABModel.Condition condition = new ABModel.Condition().setUid("13412342349").setGender("1");
        abHandler.dispater(condition, abModels, new TestABInterImpl());
    }

    static class TestABInterImpl implements ABHandler.ABInterface {

        @Override
        public void runTestA() {
            System.out.println("123:runTestA");
            System.out.println("=======================================");
        }

        @Override
        public void runTestB() {
            System.out.println("123:runTestB");
            System.out.println("==========================================");
        }
    }


}
