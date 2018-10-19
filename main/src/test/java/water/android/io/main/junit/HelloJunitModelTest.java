package water.android.io.main.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class HelloJunitModelTest {

    HelloJunitModel helloJunitModel = new HelloJunitModel();

    @Before
    public void setUp() throws Exception {
        helloJunitModel.setName("HelloJunit");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getName() {
        assertEquals(helloJunitModel.getName(), "Hello");
    }
}