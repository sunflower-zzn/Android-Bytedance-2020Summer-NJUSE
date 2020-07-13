package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;
import com.byted.camp.todolist.operation.activity.DatabaseActivity;
import com.byted.camp.todolist.operation.activity.DebugActivity;
import com.byted.camp.todolist.operation.activity.SettingActivity;
import com.byted.camp.todolist.ui.NoteListAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;

    private RecyclerView recyclerView;
    private NoteListAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, NoteActivity.class),
                        REQUEST_CODE_ADD);
            }
        });

        recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
                notesAdapter.refresh(loadNotesFromDatabase());
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
                notesAdapter.refresh(loadNotesFromDatabase());
            }
        });
        notesAdapter.refresh(loadNotesFromDatabase());
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.action_debug:
                startActivity(new Intent(this, DebugActivity.class));
                return true;
            case R.id.action_database:
                startActivity(new Intent(this, DatabaseActivity.class));
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD
                && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }

    private List<Note> loadNotesFromDatabase() {
        // TODO 从数据库中查询数据，并转换成 JavaBeans
        TodoDbHelper dbHelper = new TodoDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                TodoContract.TodoEntry.COLUNM_NAME_PRIORITY,
                TodoContract.TodoEntry.COLUNM_NAME_DATE,
                TodoContract.TodoEntry.COLUMN_NAME_STATE,
                TodoContract.TodoEntry.COLUMN_NAME_CONTENT
        };
        //按照创建时间逆序
        String sortOrder = TodoContract.TodoEntry.COLUNM_NAME_DATE + " DESC";
        //相当于select *
        Cursor cursor = db.query(
                TodoContract.TodoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List<Note> notes = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do{
                long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
                Note note = new Note(id);
                note.setPriority(Priority.from(cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUNM_NAME_PRIORITY))));
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date;
                try {
                    date = formatter.parse(cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry.COLUNM_NAME_DATE)));
                } catch (ParseException e) {
                    date = null;
                    Log.d("TAG", e.getMessage());
                }
                note.setDate(date);
                note.setState(State.from(cursor.getInt(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_NAME_STATE))));
                note.setContent(cursor.getString(cursor.getColumnIndex(TodoContract.TodoEntry.COLUMN_NAME_CONTENT)));
                notes.add(note);
            }while (cursor.moveToNext());
        }
        Collections.sort(notes); //正序排序
        return notes;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        TodoDbHelper dbHelper = new TodoDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(note.id)};
        int deleteRows = db.delete(TodoContract.TodoEntry.TABLE_NAME, selection, selectionArgs);
    }

    private void updateNode(Note note) {
        // TODO 更新数据
        TodoDbHelper dbHelper = new TodoDbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(TodoContract.TodoEntry.COLUMN_NAME_STATE,note.getState().intValue);
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(note.id)};
        int count = db.update(TodoContract.TodoEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

}
