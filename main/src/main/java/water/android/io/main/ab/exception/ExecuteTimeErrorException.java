package water.android.io.main.ab.exception;

/**
 * AB测试执行时间不对
 */
public class ExecuteTimeErrorException extends Exception {

    public ExecuteTimeErrorException() {
        super();
    }

    public ExecuteTimeErrorException(String message) {
        super(message);
    }
}
