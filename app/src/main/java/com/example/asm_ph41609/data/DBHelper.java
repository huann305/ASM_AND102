package com.example.asm_ph41609.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "ASM.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("CHECK", "CHECK");

        String CREATE_TBL_USER = "CREATE TABLE USERACCOUNT (id INTEGER  PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT, fullname TEXT)";
        String CREATE_TBL_PRODUCT = "CREATE TABLE PRODUCT (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cost INTEGER, quantity INTEGER)";


        db.execSQL(CREATE_TBL_USER);
        db.execSQL(CREATE_TBL_PRODUCT);


        String INSERT_DATA_USER = "INSERT INTO USERACCOUNT VALUES (1, 'user1', 'user1', 'user1'), (2, 'user1', 'user1', 'user1')";
        String INSERT_DATA_PRODUCT = "INSERT INTO PRODUCT VALUES (1, 'Product 1', 100000, 10)," +
                "(2, 'Product 2', 100000, 10)," +
                "(3, 'Product 3', 100000, 10)," +
                "(4, 'Product 4', 100000, 10)," +
                "(5, 'Product 5', 100000, 10)";

        db.execSQL(INSERT_DATA_PRODUCT);
        db.execSQL(INSERT_DATA_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS USER_ACCOUNT");
            db.execSQL("DROP TABLE IF EXISTS PRODUCT");
            onCreate(db);
        }
    }
}
