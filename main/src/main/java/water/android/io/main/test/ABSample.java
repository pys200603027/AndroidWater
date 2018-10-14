package water.android.io.main.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import water.android.io.main.ab.ABModel;
import water.android.io.main.ab.annotation.ABTesting;

@Deprecated
public class ABSample {

    public static void main(String[] args) {


    }

    public static void handleTest(ABModel expParam) {
        if (!expParam.checkExpireTime()) {
            return;
        }
        try {
//            handleOp(expParam, ABHandler.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void handleOp(ABModel expParam, Object obj) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> methods = getDeclaredAnnotationMethod(obj.getClass(), ABTesting.class);
        if (methods.isEmpty()) {
            return;
        }
        for (Method m : methods) {
            ABTesting annotation = m.getAnnotation(ABTesting.class);
            if (annotation.op().equals(expParam.op) && annotation.prop().equals(expParam.prop)) {
                m.invoke(obj, null);
            }
        }
    }

    public static void handleOp(ABModel expParam, Class c) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Method> methods = getDeclaredAnnotationMethod(c, ABTesting.class);
        if (methods.isEmpty()) {
            return;
        }
        for (Method m : methods) {
            ABTesting annotation = m.getAnnotation(ABTesting.class);
            if (annotation.op().equals(expParam.op) && annotation.prop().equals(expParam.prop)) {
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
