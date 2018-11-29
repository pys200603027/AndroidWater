package function.network;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {
    class Person {
        private String bday;
        private String nick;
        private String name;

        public String getBirthday() {
            return bday;
        }

        public void setBirthday(String bday) {
            this.bday = bday;
        }

        public String getNickName() {
            return nick;
        }

        public void setNickName(String nick) {
            this.nick = nick;
            this.name = bday;
        }

        @Override
        public String toString() {
            return "name:" + nick + ",birthday:" + bday;
        }
    }

    /**
     * 测试：get/set方法和声明的属性不一致时，是否生成json成功
     */
    @Test
    public void test1() {
        Person person = new Person();
        person.setBirthday("1999-9-9");
        person.setNickName("hello");

        Gson gson = new Gson();
        String s = gson.toJson(person);
    }

    /**
     * 测试：get/set方法和声明的属性不一致时，生成Person是否成功
     */
    @Test
    public void test2() {
        String json = "{\"bday\":\"1999-9-9\",\"nick\":\"hello\"}";
        Gson gson = new Gson();

        Person person = gson.fromJson(json, Person.class);
        System.out.println(person);

        System.out.println(person.name);
    }

    /**
     * 数组
     */
    @Test
    public void test3() {
        List<String> urls = new ArrayList<>();

        urls.add("aaa");
        urls.add("bbb");
        urls.add("ccc");

        Gson gson = new Gson();
        String s = gson.toJson(urls);
        System.out.println(s);
    }
}
