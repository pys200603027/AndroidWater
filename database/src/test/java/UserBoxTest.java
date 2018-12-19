import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import water.android.io.objetbox.MyObjectBox;
import water.android.io.objetbox.User;

public class UserBoxTest {
    BoxStore boxStore;
    Box<User> userBox;

    @Before
    public void setUp() {
        boxStore = MyObjectBox.builder().name("objectbox-user-db").build();
        userBox = boxStore.boxFor(User.class);
    }

    @After
    public void close() {
        if (boxStore != null) {
            boxStore.close();
        }
    }

    @Test
    public void addTest1() {
        User user = new User("Test4");
        user.setUid("002");
        user.setTempUsageCount(4);
        userBox.put(user);
    }

    /**
     * 查找所有
     */
    @Test
    public void queryTest1() {
        List<User> users = userBox.query().build().find();
        for (User u : users) {
            System.out.println(u);
        }
    }


}
