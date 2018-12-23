package water.android.io.liquid.utils;

public class Log {

    public static void init(FlatformLog flatformLog) {
        flatformLogInternal = flatformLog;
    }

    private static FlatformLog flatformLogInternal;


    public static void d(String msg) {
        if (flatformLogInternal != null) {
            flatformLogInternal.d(msg);
        } else {
            System.out.println(msg);
        }
    }

    /**
     * 平台实现各自的log打印
     */
    public interface FlatformLog {
        void d(String msg);
    }

}
