package function.junit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 控制了方法的执行顺序
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMethodOrder {

    @Test
    public void TestA() {
        System.out.println("TestA");
    }

    @Test
    public void TestB() {
        System.out.println("TestB");
    }

    @Test
    public void TestC() {
        System.out.println("TestC");
    }

}
