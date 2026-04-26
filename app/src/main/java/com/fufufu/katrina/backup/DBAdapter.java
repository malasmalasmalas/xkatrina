package com.fufufu.katrina.backup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Map;

public class DBAdapter {
    private static final String DATABASE_NAME = "katrinakill";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DBAdapter";
    private DatabaseHelper DBHelper;
    private final Context context;

        private SQLiteDatabase f802db;
    private int openedConnections = 0;

    public DBAdapter(Context context) {
        this.context = context;
        this.DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DBAdapter.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Statics.LogD("DatabaseHelper -> onCreate");
            sQLiteDatabase.execSQL("create table blackList ([packageName] nvarchar(50));");
            sQLiteDatabase.execSQL("create table whiteList ([packageName] nvarchar(50));");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.chrome');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.docs');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.books');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.maps');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.apps.plus');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.browser');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.calculator2');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.gallery3d');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gm');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.music');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.talk');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.vending');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.android.vending');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gsf');");
            sQLiteDatabase.execSQL("insert into blackList([packageName]) values('com.google.android.gms');");
            sQLiteDatabase.execSQL("insert into whiteList([packageName]) values('com.fufufu.katrina.backup');");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Statics.LogD("DatabaseHelper -> onUpgrade");
            onCreate(sQLiteDatabase);
        }
    }

    public synchronized DBAdapter open() throws SQLException {
        DBAdapter dBAdapter;
        try {
            this.openedConnections++;
            this.f802db = this.DBHelper.getWritableDatabase();
            dBAdapter = this;
        } catch (Exception e) {
            e.printStackTrace();
            dBAdapter = null;
        }
        return dBAdapter;
    }

    public synchronized void close() {
        int i = this.openedConnections - 1;
        this.openedConnections = i;
        if (i == 0) {
            this.DBHelper.close();
        }
    }

    public long insert(String str, ContentValues contentValues) {
        long jInsertOrThrow;
        try {
            open();
            jInsertOrThrow = this.f802db.insertOrThrow(str, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            jInsertOrThrow = -1;
        }
        close();
        return jInsertOrThrow;
    }

    public int update(String str, ContentValues contentValues, ContentValues contentValues2) {
        String string;
        Exception e;
        String str2 = "";
        if (contentValues2 != null) {
            try {
                string = "";
                for (String str3 : contentValues2.keySet()) {
                    try {
                        StringBuilder sb = new StringBuilder(String.valueOf(string));
                        sb.append(string == "" ? "" : " AND ");
                        sb.append(str3);
                        sb.append("=?");
                        string = sb.toString();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                string = "";
                e = e3;
            }
            str2 = string;
        }
        String[] strArr = null;
        if (contentValues2 != null) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, Object> entry : contentValues2.valueSet()) {
                entry.getKey().toString();
                Object value = entry.getValue();
                arrayList.add(value == null ? null : value.toString());
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        open();
        int iUpdate = this.f802db.update(str, contentValues, str2, strArr);
        close();
        return iUpdate;
    }

    public int delete(String str, ContentValues contentValues) {
        String string;
        Exception e;
        String str2 = "";
        if (contentValues != null) {
            try {
                string = "";
                for (String str3 : contentValues.keySet()) {
                    try {
                        StringBuilder sb = new StringBuilder(String.valueOf(string));
                        sb.append(string == "" ? "" : " AND ");
                        sb.append(str3);
                        sb.append("=?");
                        string = sb.toString();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (Exception e3) {
                string = "";
                e = e3;
            }
            str2 = string;
        }
        String[] strArr = null;
        if (contentValues != null) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                entry.getKey().toString();
                Object value = entry.getValue();
                arrayList.add(value == null ? null : value.toString());
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        open();
        int iDelete = this.f802db.delete(str, str2, strArr);
        close();
        return iDelete;
    }

    public Cursor select(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return this.f802db.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}