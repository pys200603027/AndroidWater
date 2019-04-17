package function.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BaseTest {
    /**
     * 测试数据传递
     */
    class ClassPass {
        List<String> list;

        ClassPass(List<String> list) {
            this.list = list;
        }

        /**
         * 这里list指向了另一个指针，因此失去了之前传入当联系
         */
        void method1() {
            List<String> temp = new ArrayList<>();
            temp.add("Hello World");
            list = temp;
        }

        void method2() {
            list.add("One");
            list.add("Second");
        }

        void method3() {
            List<String> temp = new ArrayList<>();
            temp.add("One");
            temp.add("Second");
            temp.add("Three");

            list.addAll(temp);
        }
    }

    /**
     * 当对象作为null传递过去时，并没有发生引用传递
     */
    @Test
    public void testReferencePassNull() {
        List<String> list = null;
        ClassPass classPass = new ClassPass(list);
        classPass.method1();
        System.out.println(list.size());
    }

    @Test
    public void testReferencePassEmpty1() {
        List<String> list = new ArrayList<>();
        ClassPass classPass = new ClassPass(list);
        classPass.method1();
        System.out.println(list.size());
    }

    @Test
    public void testReferencePassEmpty2() {
        List<String> list = new ArrayList<>();
        ClassPass classPass = new ClassPass(list);
        classPass.method2();
        System.out.println(list.size());
    }

    @Test
    public void testReferencePassEmpty3() {
        List<String> list = new ArrayList<>();
        ClassPass classPass = new ClassPass(list);
        classPass.method3();
        System.out.println(list.size());
    }
}
