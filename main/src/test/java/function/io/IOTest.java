package function.io;

import org.junit.Test;

import java.io.File;
import java.sql.SQLSyntaxErrorException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
}
