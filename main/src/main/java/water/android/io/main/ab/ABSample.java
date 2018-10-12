package water.android.io.main.ab;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class ABSample {

    public static void main(String[] args) {

        Gson gson = new Gson();
        List<ExpParam> list = gson.fromJson(ExpParam.testData(), new TypeToken<List<ExpParam>>() {}.getType());
        for (ExpParam p : list) {
            handleTest(p);
        }
    }

    public static void handleTest(ExpParam expParam) {
        if (!expParam.checkExpireTime()) {
            return;
        }
        try {
            handleOp(expParam, ABHandler.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void handleOp(ExpParam expParam, Object obj) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> methods = getDeclaredAnnotationMethod(obj.getClass(), ABTesting.class);
        if (methods.isEmpty()) {
            return;
        }
        for (Method m : methods) {
            ABTesting annotation = m.getAnnotation(ABTesting.class);
            if (annotation.op().equals(expParam.op) && annotation.pro().equals(expParam.prop)) {
                m.invoke(obj, null);
            }
        }
    }

    public static void handleOp(ExpParam expParam, Class c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> methods = getDeclaredAnnotationMethod(c, ABTesting.class);
        if (methods.isEmpty()) {
            return;
        }
        for (Method m : methods) {
            ABTesting annotation = m.getAnnotation(ABTesting.class);
            if (annotation.op().equals(expParam.op) && annotation.pro().equals(expParam.prop)) {
                m.invoke(c.newInstance(), null);
            }
        }
    }

    /**
     * 返回带有指定注解的方法集合
     *
     * @param c
     * @param a
     * @return
     */
    public static List<Method> getDeclaredAnnotationMethod(Class c, Class<? extends Annotation> a) {
        Method[] methods = c.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method m : methods) {
            if (m.isAnnotationPresent(a)) {
                list.add(m);
            }
        }
        return list;
    }
}
