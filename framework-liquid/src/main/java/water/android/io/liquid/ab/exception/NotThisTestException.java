package water.android.io.liquid.ab.exception;

/**
 * 通过判断ab测试名字
 */
public class NotThisTestException extends Exception {
    public NotThisTestException(String message) {
        super(message);
    }
}
