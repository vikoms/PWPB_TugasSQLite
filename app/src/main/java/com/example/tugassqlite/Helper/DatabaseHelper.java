package com.example.tugassqlite.Helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.tugassqlite.Models.Siswa;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Siswa";
    private static final String TABLE_NAME = "tbl_siswa";
    private static final String KEY_NAME = "name";
    private static final String KEY_NO = "nomor";
    private static final String KEY_TTL = "tanggal_lahir";
    private static final String KEY_GENDER = "jenkel";
    private static final String KEY_ADRESS = "alamat";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE "+TABLE_NAME+"("+KEY_NO+"INTEGER PRIMARY KEY,"+KEY_NAME+"TEXT, "+KEY_TTL+"DATE,"+KEY_GENDER+"" +
                "TEXT,"+KEY_ADRESS+"TEXT)";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = ("drop table if exists " + TABLE_NAME);
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert(Siswa siswa){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NO, siswa.getNomor());
        values.put(KEY_NAME, siswa.getNama());
        values.put(KEY_TTL, siswa.getTanggal_lahir());
        values.put(KEY_GENDER, siswa.getJenis_kelamin());
        values.put(KEY_ADRESS, siswa.getAlamat());
        db.insert(TABLE_NAME, null, values);
    }

    public List<Siswa> selectUserData(){
        ArrayList<Siswa> userList = new ArrayList<Siswa>();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_NO, KEY_NAME, KEY_TTL, KEY_GENDER, KEY_ADRESS};
        Cursor c = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (c.moveToNext()) {
            int no = c.getInt(0);
            String name = c.getString(1);
            String ttl = c.getString(2);
            String gender = c.getString(3);
            String address = c.getString(4);

            Siswa siswa = new Siswa();
            siswa.setNama(name);
            siswa.setNomor(no);
            siswa.setAlamat(address);
            siswa.setJenis_kelamin(gender);
            siswa.setTanggal_lahir(ttl);
            userList.add(siswa);
        }

        return userList;
    }

    public void delete(String nomor) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = KEY_NO + "='" + nomor + "'";
        db.delete(TABLE_NAME, whereClause, null);
    }

    public void show(String nomor){
        SQLiteDatabase db = getWritableDatabase();

    }

    public void update(Siswa siswa) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NO, siswa.getNomor());
        values.put(KEY_NAME, siswa.getNama());
        values.put(KEY_TTL, siswa.getTanggal_lahir());
        values.put(KEY_GENDER, siswa.getJenis_kelamin());
        values.put(KEY_ADRESS, siswa.getTanggal_lahir());
        String where = KEY_NO + "='" + siswa.getNomor() + "'";
        db.update(TABLE_NAME, values, where, null);
    }

}