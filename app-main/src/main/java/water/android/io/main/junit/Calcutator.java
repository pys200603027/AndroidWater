package water.android.io.main.junit;

/**
 * JUnit 4官方 Get Start例子
 * https://github.com/junit-team/junit4/wiki/Getting-started
 */
public class Calcutator {

    public int evaluate(String expresstion) {
        int sum = 0;
        for (String summand : expresstion.split("\\+")) {
            sum += Integer.valueOf(summand);
        }

        return sum;
    }

    public int substraction(String expresstion) {
        int result = 0;
        for (String summand : expresstion.split("\\-")) {
            result -= Integer.valueOf(summand);
        }
        return result;
    }
}
