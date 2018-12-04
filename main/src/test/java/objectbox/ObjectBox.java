package objectbox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.DebugFlags;

public class ObjectBox {
    private File boxStoreDir;
    private BoxStore store;

    @Before
    public void setUp() throws IOException {
        File tempFile = File.createTempFile("object-store-test", "");
        // ensure file does not exist so builder creates a directory instead
        tempFile.delete();

        boxStoreDir = tempFile;
//        store = MyObjectBox.builder()
//                // add directory flag to change where ObjectBox puts its database files
//                .directory(boxStoreDir)
//                // optional: add debug flags for more detailed ObjectBox log output
//                .debugFlags(DebugFlags.LOG_QUERIES | DebugFlags.LOG_QUERY_PARAMETERS)
//                .build();
    }

    @After
    public void tearDown() {
        if (store != null) {
            store.close();
            store.deleteAllFiles();
        }
    }


    /**
     * 官方文档：基本存取
     * https://docs.objectbox.io/tutorial-demo-project
     */
    @Test
    public void test1() {
        Box<Note> noteBox = store.boxFor(Note.class);

        //put
        Note note = new Note();
        note.id = 0;
        note.text = "hello world";
        noteBox.put(note);

        //put
        Note n2 = new Note();
        n2.id = 0;
        n2.text = "hello objectBox";
        noteBox.put(n2);

        //find
        for (Note n : noteBox.getAll()) {
            System.out.println(n);
        }

        System.out.println("==============");

        //remove
        noteBox.remove(n2);

        //update
        note.text = "hello me.";
        noteBox.put(note);

        //find
        for (Note n : noteBox.getAll()) {
            System.out.println(n);
        }
    }

    @Test
    public void test2() {
        Box<Person> personBox = store.boxFor(Person.class);

        Person person = new Person();
        person.setFirstName("Peter");
        person.setId(0);
        person.setUid(9527);

        personBox.put(person);


        System.out.println(personBox.get(9527));

    }
}
