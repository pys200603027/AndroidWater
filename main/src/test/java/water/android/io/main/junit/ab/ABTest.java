package water.android.io.main.junit.ab;

import org.junit.Assert;
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
                , json, new ABHandler.ABAction() {
                    @Override
                    public void run(boolean result) {
                        System.out.println("123, result:" + result);
                    }
                }, new ABHandler.ABError() {
                    @Override
                    public void throwable(Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void main(String[] args) {
        new ABTest().testCustom();
    }
}
