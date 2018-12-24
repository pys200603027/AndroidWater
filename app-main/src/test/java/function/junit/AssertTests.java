package function.junit;

import org.hamcrest.core.CombinableMatcher;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * JUnit4 官方Demo
 * https://github.com/junit-team/junit4/wiki/Assertions
 * 关于Assert的那些事情
 */
public class AssertTests {

    /**
     * 数组比较相等
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
    public void testAssertNotNull() {
        assertNotNull("should not be null", new Object());
    }

    @Test
    public void testAssertNotSame() {
        assertNotSame("Should not be same Object", new Object(), new Object());
    }

    /**
     * 对象是否相等
     */
    @Test
    public void testAssertSame() {
        assertSame("Should be same Object", new Object(), new Object());
    }

    @Test
    public void testAssertNull() {
        assertNull("Should be null", null);
    }

    /**
     * 检查字符串包含
     */
    @Test
    public void testAssertThatBothContainsString() {
        assertThat("albumen", both(containsString("a")).and(containsString("b")));
    }

    /**
     * 检查数组包含数字
     */
    @Test
    public void testAssertThatEveryItemContainsString() {
        assertThat(Arrays.asList("one", "two", "three"), hasItems("one", "three"));
    }

    /**
     * 检查数组每个字符串是否包含
     */
    @Test
    public void testAssertEveryItemContainsString() {
        assertThat(Arrays.asList(new String[]{"fun", "ban", "net"}), everyItem(containsString("n")));
    }

    /**
     * 关于HamcrestCore的比对用法
     */
    @Test
    public void testAssertThatHamcrestCoreMathers() {
        assertThat("good", allOf(equalTo("good"), startsWith("good")));
        assertThat("good", not(allOf(equalTo("bad"), equalTo("good"))));
        assertThat("good", anyOf(equalTo("bad"), equalTo("good")));

        assertThat(7, not(CombinableMatcher.<Integer>either(equalTo(3)).or(equalTo(4))));

        assertThat(new Object(), not(sameInstance(new Object())));
    }

    @Test
    public void testAssertTrue() {
        assertTrue("failure-should be true", true);
    }


}
