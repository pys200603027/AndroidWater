package ab;

import org.junit.Test;

import water.android.io.liquid.ab.ABHandler;
import water.android.io.liquid.ab.ABModel;

import static org.junit.Assert.*;

public class ABTest {

    @Test
    public void testStringToFloat() throws Exception {
        float v = Float.parseFloat("3.0");
        assertEquals("检查字符串转成数字", 3, v);
    }

    @Test
    public void testCustom() {
        String json = "[{\"startTime\":1539676817000,\"value\":[true],\"name\":\"update\",\"prop\":\"flag\",\"op\":\"eq\",\"platform\":\"all\"},{\"expireTime\":1642297600000,\"startTime\":1539676817000,\"value\":[3],\"name\":\"guide_skip_button\",\"prop\":\"custom\",\"op\":\"gt\",\"platform\":\"all\"}]";

        new ABHandler().dispatch(new ABModel.ABCondition()
                        .setAbTestName("guide_skip_button")
                        .setCostom("6")
                , json,
                result -> System.out.println("123, result:" + result),
                e -> e.printStackTrace());
    }

    @Test
    public void testCreateTime() {
        String json = "[{\n" +
                "    \"name\": \"room_auto_recording\",\n" +
                "    \"prop\": \"uid\",\n" +
                "    \"op\": \"include\",\n" +
                "    \"startTime\": 1541913295000,\n" +
                "    \"expireTime\": 1857532495000,\n" +
                "    \"value\": [\"c\", \"d\", \"e\", \"f\"]\n" +
                "  }, {\n" +
                "    \"name\": \"user_create_time\",\n" +
                "    \"prop\": \"userCreateTime\",\n" +
                "    \"op\": \"gt\",\n" +
                "    \"startTime\": 1541913295000,\n" +
                "    \"expireTime\": 1857532495000,\n" +
                "    \"value\": [1541905616000]\n" +
                "  }]";
        new ABHandler().dispatch(
                new ABModel.ABCondition().setUserCreateTime("1857532495000").setAbTestName("user_create_time"),
                json,
                new ABHandler.ABAction() {
                    @Override
                    public void run(boolean result) {
                        System.out.println(result);
                    }
                },
                new ABHandler.ABError() {
                    @Override
                    public void throwable(Exception e) {
                        e.printStackTrace();
                    }
                }
        );

    }

    @Test
    public void testParstInt() {
        Long l = Long.parseLong("1857532495000");
        System.out.println(l);

        int i = Integer.parseInt("1857532495000");

    }

    @Test
    public void testAutoRecording() {
        String json = "[{\n" +
                "    \"name\": \"room_auto_recording\",\n" +
                "    \"prop\": \"uid\",\n" +
                "    \"op\": \"include\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"c\", \"d\", \"e\", \"f\"]\n" +
                "  }, {\n" +
                "    \"name\": \"room_auto_recording\",\n" +
                "    \"prop\": \"userCreateTime\",\n" +
                "    \"op\": \"gt\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"1541905616000\"]\n" +
                "  }]";

        new ABHandler().dispatch(
                new ABModel.ABCondition()
                        .setAbTestName("room_auto_recording")
                        .setUserCreateTime("1857532495000")
                        .setUid("acc"),
                json,
                new ABHandler.ABAction() {
                    @Override
                    public void run(boolean result) {
                        System.out.println(result);
                    }
                },
                new ABHandler.ABError() {
                    @Override
                    public void throwable(Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Test
    public void testSkipFlatForm() {
        String json = "[{\n" +
                "    \"name\": \"room_auto_recording\",\n" +
                "    \"prop\": \"uid\",\n" +
                "    \"op\": \"include\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"1\", \"2\", \"3\", \"4\", \"5\", \"a\", \"b\", \"c\", \"d\", \"e\", \"f\"]\n" +
                "  }, {\n" +
                "    \"name\": \"room_auto_recording\",\n" +
                "    \"prop\": \"userCreateTime\",\n" +
                "    \"op\": \"gt\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"1541905616000\"]\n" +
                "  }, {\n" +
                "    \"name\": \"guide_skip_button\",\n" +
                "    \"prop\": \"uid\",\n" +
                "    \"platform\": \"all\",\n" +
                "    \"op\": \"include\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"1\", \"2\", \"3\", \"4\", \"5\", \"a\", \"b\", \"c\", \"d\", \"e\", \"f\"]\n" +
                "  }, {\n" +
                "    \"name\": \"guide_skip_button\",\n" +
                "    \"prop\": \"userCreateTime\",\n" +
                "    \"platform\": \"all\",\n" +
                "    \"op\": \"gt\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"1541912690000\"]\n" +
                "  }\n" +
                "  , {\n" +
                "    \"name\": \"guide_skip_button\",\n" +
                "    \"prop\": \"custom\",\n" +
                "    \"platform\": \"all\",\n" +
                "    \"op\": \"gt\",\n" +
                "    \"startTime\": 1541905616000,\n" +
                "    \"expireTime\": 1641905616000,\n" +
                "    \"value\": [\"0\"]\n" +
                "  }]";

        new ABHandler().dispatch(
                new ABModel.ABCondition()
                        .setAbTestName("guide_skip_button")
                        .setUserCreateTime("1857532495000")
                        .setCostom("6")
                        .setUid("acc"),
                json,
                new ABHandler.ABAction() {
                    @Override
                    public void run(boolean result) {
                        System.out.println(result);
                    }
                },
                new ABHandler.ABError() {
                    @Override
                    public void throwable(Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    @Test
    public void checkSkipGuid() {
        String json = "[{\"name\":\"room_auto_recording\",\"prop\":\"uid\",\"op\":\"include\",\"startTime\":1541905616000,\"expireTime\":1641905616000,\"value\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"a\",\"b\",\"c\",\"d\",\"e\",\"f\"]},{\"name\":\"room_auto_recording\",\"prop\":\"userCreateTime\",\"op\":\"gt\",\"startTime\":1541905616000,\"expireTime\":1641905616000,\"value\":[\"1541905616000\"]},{\"name\":\"guide_skip_button\",\"prop\":\"uid\",\"platform\":\"all\",\"op\":\"include\",\"startTime\":1541905616000,\"expireTime\":1641905616000,\"value\":[\"1\",\"2\",\"3\",\"4\",\"5\",\"a\",\"b\",\"c\",\"d\",\"e\",\"f\"]},{\"name\":\"guide_skip_button\",\"prop\":\"userCreateTime\",\"platform\":\"all\",\"op\":\"gt\",\"startTime\":1541905616000,\"expireTime\":1641905616000,\"value\":[\"1541912690000\"]},{\"name\":\"guide_skip_button\",\"prop\":\"custom\",\"platform\":\"all\",\"op\":\"gt\",\"startTime\":1541905616000,\"expireTime\":1641905616000,\"value\":[\"0\"]}]";
        new ABHandler().dispatch(
                new ABModel.ABCondition()
                        .setAbTestName("guide_skip_button")
                        .setUserCreateTime("1541401547000")
                        .setCostom("3")
                        .setUid("cf27cd50-79d7-4663-9cc3-0f7f2a131cb5"),
                json,
                new ABHandler.ABAction() {
                    @Override
                    public void run(boolean result) {
                        System.out.println(result);
                    }
                },
                new ABHandler.ABError() {
                    @Override
                    public void throwable(Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }


    public static void main(String[] args) {
        new ABTest().testCustom();
    }
}
