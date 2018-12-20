package function.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JsonTest {

    class Response<T> {
        T data;
        boolean hasMore;
    }

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

    /**
     * 数组
     */
    @Test
    public void test4() {
        List<Person> people = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.bday = "1999-1-1";
            p.name = i + "";
            p.nick = "nick:" + i;
            people.add(p);
        }

        Gson gson = new Gson();
        String s = gson.toJson(people);
        System.out.println(s);
    }

    /**
     * 模拟Http返回值
     * {"data":[{"bday":"1999-1-1","nick":"nick:0","name":"0"},{"bday":"1999-1-1","nick":"nick:1","name":"1"},{"bday":"1999-1-1","nick":"nick:2","name":"2"},{"bday":"1999-1-1","nick":"nick:3","name":"3"},{"bday":"1999-1-1","nick":"nick:4","name":"4"}],"hasMore":false}
     */
    @Test
    public void test5() {
        Response<List<Person>> response = new Response();
        response.hasMore = false;
        response.data = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.bday = "1999-1-1";
            p.name = i + "";
            p.nick = "nick:" + i;
            response.data.add(p);
        }

        Gson gson = new Gson();
        String s = gson.toJson(response);
        System.out.println(s);
    }

    /**
     * 对测试5进行验证
     */
    @Test
    public void test6() {
        String json = "{\"data\":[{\"bday\":\"1999-1-1\",\"nick\":\"nick:0\",\"name\":\"0\"},{\"bday\":\"1999-1-1\",\"nick\":\"nick:1\",\"name\":\"1\"},{\"bday\":\"1999-1-1\",\"nick\":\"nick:2\",\"name\":\"2\"},{\"bday\":\"1999-1-1\",\"nick\":\"nick:3\",\"name\":\"3\"},{\"bday\":\"1999-1-1\",\"nick\":\"nick:4\",\"name\":\"4\"}],\"hasMore\":false}";

        Gson gson = new Gson();
        Response<List<Person>> response = gson.fromJson(json, new TypeToken<Response<List<Person>>>() {
        }.getType());

        System.out.println(response.hasMore + ",size:" + response.data.size());
    }


}
