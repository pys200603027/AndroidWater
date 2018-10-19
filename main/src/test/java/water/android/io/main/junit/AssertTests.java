package water.android.io.main.junit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit4 官方Demo
 * https://github.com/junit-team/junit4/wiki/Assertions
 * 关于Assert的那些事情
 */
public class AssertTests {

    /**
     * 数组比较
     */
    @Test
    public void testAssertArrayEquals() {
        byte[] expected = "trial".getBytes();
        byte[] actual = "trail".getBytes();

        assertArrayEquals("failure - byte arrays not same", expected, actual);
    }

    @Test
    public void testAssertEquals() {
        assertEquals("failure - strings are not equal", "text", "text");
    }

    @Test
    public void testAssertFalse() {
        assertFalse("failure - should be false", false);
    }

    @Test
    public void testAssertNull() {
        assertNotNull("should not be null", new Object());
    }


}
