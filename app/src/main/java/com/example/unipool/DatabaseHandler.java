package com.example.unipool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Profile";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME1 = "UserProfile";
    private static final String TABLE_NAME2 = "Password";
    private static final String TABLE_NAME3 = "Status";
    private static final String TABLE_NAME4 = "CarInfo";

    //Primary key for all tables.
    private static final String ID_COL = "user_id";

    //Column name for TABLE 1 "UserProfile"
    private static final String USERNAME = "name";
    private static final String BIO = "bio";
    private static final String GENDER = "gender";
    private static final String CONTACT_NO = "contact_no";
    private static final String EMAIL = "email";
    private static final String EMERGENCY_CONT_NAME = "emer_cont_name";
    private static final String EMERGENCY_CONT_NUM = "emer_cont_num";

    //Column name for TABLE 2 "Password"
    private static final String PASSWORD = "password"; //current password

    //Column name for TABLE 3 "Status"
    private static final String STATUS = "driver_status";

    //Column name for TABLE 4 "CarInfo"
    private static final String CAR_MODEL = "car_model";
    private static final String VEC_COLOR = "vehicle_color";
    private static final String PLATE = "plate_number";
    private static final String LICENSE_EXP = "license_expiry";

    // creating a constructor for our database handler.
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query1 = "CREATE TABLE " + TABLE_NAME1 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT,"
                + BIO + " TEXT,"
                + GENDER + " TEXT,"
                + CONTACT_NO + " TEXT,"
                + EMAIL + " TEXT,"
                + EMERGENCY_CONT_NAME + " TEXT,"
                + EMERGENCY_CONT_NUM + " TEXT)";

        String query2 = "CREATE TABLE " + TABLE_NAME2 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PASSWORD + " TEXT)";

        String query3 = "CREATE TABLE " + TABLE_NAME3 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STATUS + " TEXT)";

        String query4 = "CREATE TABLE " + TABLE_NAME4 + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_MODEL + " TEXT,"
                + VEC_COLOR + " TEXT,"
                + PLATE + " TEXT,"
                + LICENSE_EXP + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
    }

    // this method is use to update user profile information to our sqlite database.
    public void AddUserProfile(int user_id, String username, String user_bio, String user_gender, String user_contact,
                               String user_email, String user_emer_cont_name, String user_emer_cont_num) {

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a  variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(ID_COL, user_id);
        values.put(USERNAME, username);
        values.put(BIO, user_bio);
        values.put(GENDER, user_gender);
        values.put(CONTACT_NO, user_contact);
        values.put(EMAIL, user_email);
        values.put(EMERGENCY_CONT_NAME, user_emer_cont_name);
        values.put(EMERGENCY_CONT_NUM, user_emer_cont_num);

        // after adding all values we are passing content values to our table.
        db.insert(TABLE_NAME1, null, values);
        // at last we are closing our database after adding database.
        db.close();
    }
    //Update specific row of user profile.
    public void UpdateUserProfile(int user_id, String username, String user_bio, String user_gender, String user_contact,
                                  String user_email, String user_emer_cont_name, String user_emer_cont_num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIO, user_bio);
        values.put(GENDER, user_gender);
        values.put(CONTACT_NO, user_contact);
        values.put(EMAIL, user_email);
        values.put(EMERGENCY_CONT_NAME, user_emer_cont_name);
        values.put(EMERGENCY_CONT_NUM, user_emer_cont_num);
        db.update(TABLE_NAME1, values, ID_COL + " = " + user_id, null);
        db.close();
    }

    //first-time assign password for a user.
    public void AddPassword(int user_id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, user_id);
        values.put(PASSWORD, password);
        db.insert(TABLE_NAME2, null, values);
        db.close();
    }

    //update specific row on password
    public void UpdatePassword(int user_id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD, password);
        db.update(TABLE_NAME2, values, ID_COL + " = " + user_id, null);
    }

    //assign status for user.
    public void AssignStatus(int user_id, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, user_id);
        values.put(STATUS, status);
        db.insert(TABLE_NAME3, null, values);
        db.close();
    }

    //change status for a specific user.
    public void UpdateStatus(int user_id, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS, status);
        db.update(TABLE_NAME3, values, ID_COL + " = " + user_id, null);
    }

    //insert a new row.
    public void AddCarInfo(int user_id,String car_model, String vec_color, String plate_no, String license_exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_COL, user_id);
        values.put(CAR_MODEL, car_model);
        values.put(VEC_COLOR, vec_color);
        values.put(PLATE, plate_no);
        values.put(LICENSE_EXP, license_exp);
        db.insert(TABLE_NAME4, null, values);
        db.close();
    }

    //update specific row of car info.
    public void UpdateCarInfo(int user_id, String car_model, String vec_color, String plate_no, String license_exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_MODEL, car_model);
        values.put(VEC_COLOR, vec_color);
        values.put(PLATE, plate_no);
        values.put(LICENSE_EXP, license_exp);
        db.update(TABLE_NAME4, values, ID_COL + " = " + user_id, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    //retrieve all user info.
    public ArrayList<String> searchUserInfo(int user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE user_id = " + user_id, null);
        ArrayList<String> arr = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(0));
                arr.add(cursor.getString(1)); //username
                arr.add(cursor.getString(2)); //bio
                arr.add(cursor.getString(3)); //gender
                arr.add(cursor.getString(4)); //contact_no
                arr.add(cursor.getString(5)); //email
                arr.add(cursor.getString(6)); //emergency_contact_name
                arr.add(cursor.getString(7)); //emergency_contact_no
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    //retrieve user current password.
    public ArrayList<String> searchUserPassword(int user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM " + TABLE_NAME2 + " WHERE user_id = " + user_id, null);
        ArrayList<String> arr = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(0)); //password
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    //retrieve user's status (Driver/Not a Driver)
    public ArrayList<String> searchUserStatus(int user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT driver_status FROM " + TABLE_NAME3 + " WHERE user_id = " + user_id, null);
        ArrayList<String> arr = new ArrayList<>();

        if(cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(0)); //password
            }while(cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }
}