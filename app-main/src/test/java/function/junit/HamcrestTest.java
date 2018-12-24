package function.junit;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * https://code.google.com/archive/p/hamcrest/wikis/Tutorial.wiki
 * <p>
 * Hamcrest comes with a library of useful matchers. Here are some of the most important ones.
 * <p>
 * <p>
 * 1) Core
 * anything - always matches, useful if you don't care what the object under test is
 * describedAs - decorator to adding custom failure description
 * is - decorator to improve readability - see "Sugar", below
 * <p>
 * 2) Logical
 * allOf - matches if all matchers match, short circuits (like Java &&)
 * anyOf - matches if any matchers match, short circuits (like Java ||)
 * not - matches if the wrapped matcher doesn't match and vice versa
 * <p>
 * 3) Object
 * equalTo - test object equality using Object.equals
 * hasToString - test Object.toString
 * instanceOf, isCompatibleType - test type
 * notNullValue, nullValue - test for null
 * sameInstance - test object identity
 * <p>
 * <p>
 * 4) Beans
 * hasProperty - test JavaBeans properties
 * <p>
 * 5) Collections
 * array - test an array's elements against an array of matchers
 * hasEntry, hasKey, hasValue - test a map contains an entry, key or value
 * hasItem, hasItems - test a collection contains elements
 * hasItemInArray - test an array contains an element
 * <p>
 * 6) Number
 * closeTo - test floating point values are close to a given value
 * greaterThan, greaterThanOrEqualTo, lessThan, lessThanOrEqualTo - test ordering
 * <p>
 * 7) Text
 * equalToIgnoringCase - test string equality ignoring case
 * equalToIgnoringWhiteSpace - test string equality ignoring differences in runs of whitespace
 * containsString, endsWith, startsWith - test string matching
 */
public class HamcrestTest {

    /**
     * 1. 运用assertThat
     * 2. equalTo 判定相等
     */
    @Test
    public void testEquals() {
        String theBiscuit = new String("Ginger");
        String myBiscuit = new String("Ginger");
        assertThat(theBiscuit, equalTo(myBiscuit));
        //加入标记
        assertThat("chocolate chip", theBiscuit, equalTo(myBiscuit));
    }


    /**
     * 使用自定义匹配规则
     */
    @Test
    public void testSquareRootOfMinusOneIsNotANumber() {
        assertThat(Math.sqrt(-1), is(IsNotANumber.notANumber()));
    }

    /**
     * 自定义匹配规则
     */
    public static class IsNotANumber extends TypeSafeMatcher<Double> {

        @Override
        public boolean matchesSafely(Double number) {
            return number.isNaN();
        }

        public void describeTo(Description description) {
            description.appendText("not a number");
        }

        @Factory
        public static Matcher notANumber() {
            return new IsNotANumber();
        }

    }
}
