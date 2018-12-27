package function.io;

import org.junit.Test;

import java.io.File;
import java.sql.SQLSyntaxErrorException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class IOTest {

    /**
     * 测试当前目录是哪里
     */
    @Test
    public void testCurrentDir() {
        File file = new File("main");
        Map<String, String> getenv = System.getenv();
        Set<Map.Entry<String, String>> entries = getenv.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue());
        }
        System.out.println("======================================================");
        Properties properties = System.getProperties();
        Enumeration<String> enumNames = (Enumeration<String>) properties.propertyNames();
        while (enumNames.hasMoreElements()) {
            String s = enumNames.nextElement();
            System.out.println(s + ":" + properties.getProperty(s));
        }
    }

    /**
     * 通过user.dir属性获取
     */
    @Test
    public void testUserDir() {
        System.out.println(System.getProperty("user.dir"));
        String path = System.getProperty("user.dir") + File.separator + "build.gradle";
        File file = new File(path);

        System.out.println(file.getAbsolutePath());
        assertTrue(file.exists());
    }

    public int[] getImageSize(String url) {
        int[] size = new int[2];
        try {
            String pattern = "\\s*_w(\\d+)_h(\\d+)\\s*";

            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(url);

            String matcherStr = "";
            while (matcher.find()) {
                matcherStr = matcher.group();
            }
            System.out.println(matcherStr);

            String[] split = matcherStr.split("_");

            split[1] = split[1].replace("w", "");
            split[2] = split[2].replace("h", "");

            size[0] = Integer.parseInt(split[1]);
            size[1] = Integer.parseInt(split[2]);
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @Test
    public void testString() {
        String resourceId = "chat/5a2cea1fddb4f2d0d70378a5d5cb03e6_w600_h628.jpeg";
        String pattern = "\\s*_w(\\d+)_h(\\d+)\\s*";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(resourceId);

        String matcherStr = "";
        while (matcher.find()) {
            matcherStr = matcher.group();
        }
        System.out.println(matcherStr);

        String[] split = matcherStr.split("_");

        split[1] = split[1].replace("w", "");
        split[2] = split[2].replace("h", "");

        int[] size = new int[2];
        size[0] = Integer.parseInt(split[1]);
        size[1] = Integer.parseInt(split[2]);

        System.out.println(size[0] + "," + size[1]);
    }
}
