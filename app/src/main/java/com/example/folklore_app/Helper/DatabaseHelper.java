package com.example.folklore_app.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FolkloreApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Orders";
    private static final String COLUMN_ORDER_ID = "OrderId";
    private static final String COLUMN_FOOD_TITLE = "FoodTitle";
    private static final String COLUMN_SUBTOTAL = "Subtotal";
    private static final String COLUMN_DELIVERY = "Delivery";
    private static final String COLUMN_TAX = "Tax";
    private static final String COLUMN_TOTAL = "Total";

    private static final String USER_TABLE_NAME = "User";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FOOD_TITLE + " TEXT,"
                + COLUMN_SUBTOTAL + " REAL,"
                + COLUMN_DELIVERY + " REAL,"
                + COLUMN_TAX + " REAL,"
                + COLUMN_TOTAL + " REAL" + ")";
        db.execSQL(CREATE_ORDERS_TABLE);

        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + "("
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    public void addOrder(String foodTitle, double subtotal, double delivery, double tax, double total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOOD_TITLE, foodTitle);
        values.put(COLUMN_SUBTOTAL, subtotal);
        values.put(COLUMN_DELIVERY, delivery);
        values.put(COLUMN_TAX, tax);
        values.put(COLUMN_TOTAL, total);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getUser(String email) {
    SQLiteDatabase db = this.getReadableDatabase();
    return db.rawQuery("SELECT * FROM " + USER_TABLE_NAME + " WHERE " + COLUMN_EMAIL + " = ?", new String[]{email});
}

    public void storeUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        db.insert(USER_TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE_NAME, null);
    }
}