package function.io;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SampleTest {

    /**
     * 时区格式化的问题：当两个时间相减后，得到剩余毫秒，如果想格式化，需要重置时区
     */
    @Test
    public void testTime() {
        System.out.println(System.currentTimeMillis());
        //2018-10-23 18:01:56
//        long timeLeft = 1540288916000L - System.currentTimeMillis();
        long timeLeft = 1571831637000L - System.currentTimeMillis();
        System.out.println(timeLeft);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        System.out.println(simpleDateFormat.format(timeLeft));


        while (true) {
            long l = 1571831637000L - System.currentTimeMillis();
            System.out.println(timeFormat(l));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 时间格式化显示 小时：分钟：秒
     * 大于一天累积小时
     *
     * @param l
     * @return
     */
    private String timeFormat(long l) {
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day > 0) {
            hour = hour + day * 24;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(fixNumZero(hour)).append(":");
        sb.append(fixNumZero(min)).append(":");
        sb.append(fixNumZero(s));
        return sb.toString();
    }

    private String fixNumZero(long time) {
        if (time <= 0) {
            return "00";
        } else if (time < 10) {
            return "0" + time;
        }
        return String.valueOf(time);
    }

    /**
     * 测试执行线程时
     */
    @Test
    public void testAyncThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Test aync..");
                }
                try {
                    TimeUnit.SECONDS.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(runnable).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStringToInteget() {
        long time = 1537426606;

        String s = String.valueOf(time);

        System.out.println(s);

    }

    @Test
    public void removeUnuseString() {
        String s = "{\"music_body\":\"{\\\"jump_notify\\\": {\\\"path\\\":1}}\",\"extra\":2}";
        System.out.println(s);
        if (s.contains("\\")) {
            s = s.replace("\\", "");
        }
        if (s.contains(":\"")) {
            s = s.replace(":\"", ":");
        }
        if (s.contains("}\"")) {
            s = s.replace("}\"", "}");
        }

        System.out.println(s);
    }
}
