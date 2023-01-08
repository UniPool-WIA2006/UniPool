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
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "Profile.db";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME1 = "UserProfile";

    //Column name for TABLE 1 "UserProfile"
    private static final String PREV_USERNAME = "prev_name";
    private static final String USERNAME = "name"; //PK
    private static final String BIO = "bio";
    private static final String GENDER = "gender";
    private static final String CONTACT_NO = "contact_no";
    private static final String EMAIL = "email";
    private static final String EMERGENCY_CONT_NAME = "emer_cont_name";
    private static final String EMERGENCY_CONT_NUM = "emer_cont_num";
    private static final String PASSWORD = "password"; //current password
    private static final String STATUS = "driver_status";
    private static final String CAR_MODEL = "car_model";
    private static final String VEH_COLOR = "vehicle_color";
    private static final String PLATE = "plate_number";
    private static final String LICENSE_EXP = "license_expiry";
    private static final String TRUST_POINT = "trust_point";

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
                + PREV_USERNAME + " TEXT,"
                + USERNAME + " TEXT PRIMARY KEY,"
                + BIO + " TEXT,"
                + GENDER + " TEXT,"
                + CONTACT_NO + " TEXT,"
                + EMAIL + " TEXT,"
                + EMERGENCY_CONT_NAME + " TEXT,"
                + EMERGENCY_CONT_NUM + " TEXT,"
                + PASSWORD + " TEXT,"
                + STATUS + " TEXT,"
                + CAR_MODEL + " TEXT,"
                + VEH_COLOR + " TEXT,"
                + PLATE + " TEXT,"
                + LICENSE_EXP + " TEXT,"
                + TRUST_POINT + " INT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query1);
    }

    // this method is use to update user profile information to our sqlite database.
    public void AddUserProfile(String prev_name, String username, String user_bio, String user_gender, String user_contact,
                               String user_email, String user_emer_cont_name, String user_emer_cont_num, String password, String status,
                               String car_model, String veh_color, String plate_no, String license_exp, int trust_point) {

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a  variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(PREV_USERNAME, prev_name);
        values.put(USERNAME, username);
        values.put(BIO, user_bio);
        values.put(GENDER, user_gender);
        values.put(CONTACT_NO, user_contact);
        values.put(EMAIL, user_email);
        values.put(EMERGENCY_CONT_NAME, user_emer_cont_name);
        values.put(EMERGENCY_CONT_NUM, user_emer_cont_num);
        values.put(PASSWORD, password);
        values.put(STATUS, status);
        values.put(CAR_MODEL, car_model);
        values.put(VEH_COLOR, veh_color);
        values.put(PLATE, plate_no);
        values.put(LICENSE_EXP, license_exp);
        values.put(TRUST_POINT, trust_point);

        // after adding all values we are passing content values to our table.
        db.insert(TABLE_NAME1, null, values);
        // at last we are closing our database after adding database.
        db.close();
    }

    //Update specific row of user profile.
    public void UpdateUserProfile(String username, String user_bio, String gender, String user_contact,
                                  String user_email, String user_emer_cont_name, String user_emer_cont_num) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(BIO, user_bio);
        values.put(GENDER, gender);
        values.put(CONTACT_NO, user_contact);
        values.put(EMAIL, user_email);
        values.put(EMERGENCY_CONT_NAME, user_emer_cont_name);
        values.put(EMERGENCY_CONT_NUM, user_emer_cont_num);
        //db.update(TABLE_NAME1, values, "name=?", new String[]{originalUsername});
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
        db.close();
    }

    public void UpdatePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD,password);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }

    public void UpdateGender(String username, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GENDER, gender);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }

    public void UpdateStatus(String username, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS, status);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }

    public void UpdateCarInfo(String username, String car_model, String veh_color, String plate_no, String license_exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_MODEL, car_model);
        values.put(VEH_COLOR, veh_color);
        values.put(PLATE, plate_no);
        values.put(LICENSE_EXP, license_exp);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }

    public void UpdateTrustPoint(String username, int trust_point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRUST_POINT, trust_point);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }


    public void UpdateOriginalUsername(String username, String original_username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PREV_USERNAME, original_username);
        db.update(TABLE_NAME1, values, USERNAME + " = " + username, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    //retrieve all user info.
    public ArrayList<String> searchUserInfo(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE name='" + username + "'", null);
        ArrayList<String> arr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arr.add(cursor.getString(0)); //prev_username
                arr.add(cursor.getString(1)); //username
                arr.add(cursor.getString(2)); //bio
                arr.add(cursor.getString(3)); //gender
                arr.add(cursor.getString(4)); //contact_no
                arr.add(cursor.getString(5)); //email
                arr.add(cursor.getString(6)); //emergency_contact_name
                arr.add(cursor.getString(7)); //emergency_contact_no
                arr.add(cursor.getString(8)); //password
                arr.add(cursor.getString(9)); //status (driver/ not a driver)
                arr.add(cursor.getString(10)); //car_model
                arr.add(cursor.getString(11)); //vehicle_color
                arr.add(cursor.getString(12)); //plate_number
                arr.add(cursor.getString(13)); //license_expiry_date
                arr.add(cursor.getString(14)); //trust_point
                arr.add(cursor.getString(15)); //online_status
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    public String getOnlineUsername(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + TABLE_NAME1 + " WHERE online_status = 'Online' " , null);
        return cursor.getString(0);
    }


    //For user registration and login
    public Boolean insertData(String username, String password, String email, String contact){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(EMAIL, email);
        contentValues.put(CONTACT_NO, contact);
//        contentValues.put(BIO, "temp");
//        contentValues.put(GENDER, "temp");
//        contentValues.put(EMERGENCY_CONT_NAME, "temp");
//        contentValues.put(EMERGENCY_CONT_NUM, "temp");
        long result = MyDB.insert(TABLE_NAME1, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_NAME1 + " where " + USERNAME + " = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + TABLE_NAME1 + " where " + USERNAME + " = ? and " + PASSWORD + " = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
