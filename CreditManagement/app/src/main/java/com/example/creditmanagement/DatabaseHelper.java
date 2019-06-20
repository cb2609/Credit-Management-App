package com.example.creditmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String LOG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 2;


    private static final String DATABASE_NAME = "CreditManagement";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_TRANSFER = "transfers";

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CREDITS = "credits";

    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_TO_ID = "t_id";

    public static final String CREATE_TABLE_USERS = " CREATE TABLE " + TABLE_USERS + " (" + KEY_ID + " INTEGER PRIMARY KEY , " + KEY_NAME + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_CREDITS + " INTEGER " + ") ";

    public static final String CREATE_TABLE_TRANSFERS = " CREATE TABLE " + TABLE_TRANSFER  + " (" + KEY_TO_ID + " INTEGER PRIMARY KEY, " + KEY_AMOUNT + " INTEGER " + ") ";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);



    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_TRANSFERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSFER);

        onCreate(db);
    }

    /*
     * Creating a todo
     */
    public void createToDo(DummyDatabase todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, todo.getId());
        values.put(KEY_NAME, todo.getName());
        values.put(KEY_EMAIL, todo.getEmail());
        values.put(KEY_CREDITS, todo.getCredits());

        // insert row

        long todo_id = db.insert(TABLE_USERS, null, values);

    }

    public DummyDatabase getTodo(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = " SELECT * FROM " + TABLE_USERS + " WHERE "
                + KEY_ID + " = " + todo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        DummyDatabase td = new DummyDatabase();
        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        td.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
        td.setCredits(c.getInt(c.getColumnIndex(KEY_CREDITS)));

        return td;
    }


    public int updateToDo(DummyDatabase todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, todo.getName());
        values.put(KEY_EMAIL, todo.getEmail());
        values.put(KEY_ID, todo.getId());
        values.put(KEY_CREDITS, todo.getCredits());

        // updating row
        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}



