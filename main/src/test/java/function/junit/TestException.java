package function.junit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

public class TestException {

    @Test
    public void testArrayOutOfIndex() {
        new ArrayList<>().get(0);
    }

    /**
     * 注意：这里代码中任意一个方法抛出IndexOutOfBoundsException，都会认为通过测试！
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayOutOfIndex2() {
        new ArrayList<>().get(0);
    }

    /**
     * JUnit3.x之前版本流行都检查写法
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionMessage() {
        try {
            new ArrayList<>().get(0);
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertThat(e.getMessage(), is("Index: 0, Size: 0"));
        }
    }

    /**
     * 通过Rule的形式声明抛出的异常（可以声明想抛出的字符串）
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
        List list = new ArrayList();

        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 0, Size: 0");

        list.get(0);
    }


    @Test
    public void shouldThrow() {
        TestThing testThing = new TestThing();
        thrown.expect(RuntimeException.class);
        thrown.expectMessage(startsWith("some Message"));

        testThing.chuck();
    }

    private class TestThing {
        public void chuck() {
            throw new RuntimeException("some Message");
        }
    }
}
