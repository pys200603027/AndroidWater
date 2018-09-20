package water.android.io.main;

import water.android.io.main.test.TestHttp;
import water.android.io.main.test.TestRxJava;

/**
 * just for test
 */
public class MyClass {

    static MyClass target = new MyClass();

    public static void main(String[] args) {
        new TestRxJava().test1();
        new TestRxJava().test2();

//        new TestHttp().test3();

//        new TestHttp().testOneArticle();

//        new TestHttp().testAndroidDeskTop();

        new TestHttp().testAndroidDeskTop();

    }

}
