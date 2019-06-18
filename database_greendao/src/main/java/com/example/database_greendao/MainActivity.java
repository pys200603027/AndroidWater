package com.example.database_greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText editText;
    Button addButton;
    NoteAdapter adapter;

    NoteDao noteDao;
    Query<Note> noteQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        getDaoSession();
    }

    private void getDaoSession() {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        noteDao = daoSession.getNoteDao();
        noteQuery = noteDao.queryBuilder().orderDesc(NoteDao.Properties.Text).build();
        updateNotes();
    }

    private void updateNotes() {
        List<Note> list = noteQuery.list();
        adapter.setNotes(list);
    }

    private void initView() {
        editText = findViewById(R.id.et_input);
        addButton = findViewById(R.id.btn_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        recyclerView = findViewById(R.id.rc_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new NoteAdapter(noteClickListener);
        recyclerView.setAdapter(adapter);
        addButton.setEnabled(false);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addNote();
                    return true;
                }
                return false;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean enable = s.length() != 0;
                addButton.setEnabled(enable);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void addNote() {
        String noteText = editText.getText().toString();
        editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText(noteText);
        note.setComment(comment);
        note.setDate(new Date());
        note.setType(NoteType.TEXT);

        noteDao.insert(note);
        updateNotes();
    }


    NoteAdapter.NoteClickListener noteClickListener = new NoteAdapter.NoteClickListener() {
        @Override
        public void onNoteClick(int position) {
            Note note = adapter.getNote(position);
            Long noteId = note.getId();

            noteDao.deleteByKey(noteId);
            updateNotes();
        }
    };
}
