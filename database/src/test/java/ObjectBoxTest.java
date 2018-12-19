import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.DebugFlags;
import water.android.io.objetbox.MyObjectBox;
import water.android.io.objetbox.Note;

public class ObjectBoxTest {

    @Test
    public void main() {
        BoxStore store = MyObjectBox.builder().name("objectbox-note-db").build();
        Box<Note> box = store.boxFor(Note.class);

        //保存
        Note note = new Note(0, "zxc", new Date());
        box.put(note);

        //
        System.out.println(box.count() + "notes in ObjectBox database:");
        for (Note n : box.getAll()) {
            System.out.println(note);
        }

        store.close();
    }

    private File boxStoreDir;
    private BoxStore store;

    @Before
    public void setUp() throws IOException {
        File tempFile = File.createTempFile("object-store-test", "");
        tempFile.delete();

        boxStoreDir = tempFile;

        store = MyObjectBox.builder()
                .directory(boxStoreDir)
                .debugFlags(DebugFlags.LOG_QUERIES | DebugFlags.LOG_QUERY_PARAMETERS)
                .build();

    }

    @After
    public void tearDown() throws Exception {
        if (store != null) {
            store.close();
            store.deleteAllFiles();
        }
    }

    @Test
    public void testPutAndGet() {
        Box<Note> box = store.boxFor(Note.class);
        //保存
        Note note = new Note(0, "zxc", new Date());
        note.setInnerNote(new Note.InnerNote("inner-Text"));
        box.put(note);


        System.out.println(box.count() + " notes in ObjectBox database:");
        for (Note n : box.getAll()) {
            System.out.println(note);
        }
    }
}
