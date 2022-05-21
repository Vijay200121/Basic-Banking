package com.vijaygaike.basicbanking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class db extends SQLiteOpenHelper {

    String[] listAllUsers = {"Vijay Gaike",
            "Nitika Swamy",
            "Dhiraj Mutti",
            "Rehman Barad",
            "Jasmin Sengupta",
            "Padama Pillay",
            "Drishti Sankar",
            "Gunjan Kaur",
            "Pranay Som",
            "Jayshree Merchant" };

    private static final String DB_NAME = "BasicBank.db";
    private static final int DB_VERSION = 1;

    //all user table
    private static final String TABLE_NAME = "all_users";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "name";
    private static final String BALANCE_COL = "Balance";

    //history table
    private static final String HISTORY_TABLE = "history";
    private static final String SENDER_NAME = "sender";
    private static final String RECEIVER_NAME = "receiver";
    private static final String AMOUNT = "amount";

    public db(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + BALANCE_COL + " INTEGER)";

        String query1 = "CREATE TABLE " + HISTORY_TABLE + " ("
                + SENDER_NAME + " TEXT,"
                + RECEIVER_NAME + " TEXT,"
                + AMOUNT + " INTEGER)";

        db.execSQL(query);
        db.execSQL(query1);

        for (String users:
             listAllUsers) {
            this.addSetting(db, users, 10000);
        }
    }

    public void addSetting(SQLiteDatabase db, String name, int bal) {
        ContentValues values = new ContentValues();
        values.put(NAME_COL, name);
        values.put(BALANCE_COL, bal);
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public Cursor checkBalance(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.query(
                TABLE_NAME,
                new String[]{BALANCE_COL},
                ID_COL + "=" + id,
                null, null, null, null, null
        );
    }

    public Boolean update_sender(int id_s, String name, int bal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, id_s);
        values.put(NAME_COL, name);
        values.put(BALANCE_COL, bal);
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id_s)};
        long r = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        return r != -1;
    }

    public Boolean update_receiver(int id_r, String name, int bal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, id_r);
        values.put(NAME_COL, name);
        values.put(BALANCE_COL, bal);
        String whereClause = "id=?";
        String[] whereArgs = {String.valueOf(id_r)};
        long r = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        return r != -1;
    }

    public void add_history(String sender, String receiver, int amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SENDER_NAME, sender);
        values.put(RECEIVER_NAME, receiver);
        values.put(AMOUNT, amount);
        db.insert(HISTORY_TABLE, null, values);
    }

    public Cursor show_history(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ HISTORY_TABLE, null);
        return cursor;
    }
}
