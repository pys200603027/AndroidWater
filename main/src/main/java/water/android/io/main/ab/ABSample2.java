package water.android.io.main.ab;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ABSample2 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        List<ExpParam> list = gson.fromJson(ExpParam.testData(), new TypeToken<List<ExpParam>>() {
        }.getType());

        for (ExpParam expParam : list) {
            handleTest(expParam);
        }
    }

    public static void handleTest(ExpParam expParam) {
        String prop = expParam.prop;
        ABStrategy abStrategy = new ABStrategy();

        if (prop.equals("uid")) {

        }

    }
}
