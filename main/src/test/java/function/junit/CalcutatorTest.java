package function.junit;

import org.junit.Test;

import water.android.io.main.junit.Calcutator;

import static org.junit.Assert.*;

/**
 * JUnit 4官方 Get Start例子
 * https://github.com/junit-team/junit4/wiki/Getting-started
 */
public class CalcutatorTest {

    @Test
    public void evaluateExpression() {
        Calcutator calcutator = new Calcutator();
        int sum = calcutator.evaluate("1+2+3");
        assertEquals(6, sum);
    }

    @Test
    public void substractionExpresstion() {
        Calcutator calcutator = new Calcutator();
        int substraction = calcutator.substraction("3-2-1");
        assertEquals(-1, substraction);
    }

}