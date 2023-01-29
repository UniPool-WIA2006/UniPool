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
    private static final String TABLE_NAME2 = "ProfileImage";
    private static final String TABLE_NAME3 = "Manage";


    //Column name for TABLE 1 "UserProfile"
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

    //Column name for TABLE 2 "ProfileImage"
    private static final String PROFILE_IMAGE = "imageTEXT";

    //Column name for TABLE 3 "Manage"
    private static final String ID = "id";
    private static final String LOCATION_FROM = "location_from";
    private static final String LOCATION_TO = "location_to";
    private static final String FEE = "fee";
    private static final String DESC = "description";
    private static final String TYPE = "type";
    private static final String MANAGE_STATUS = "manage_status";
    private static final String UserAccept = "user_accept";

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

        String query2 = "CREATE TABLE " + TABLE_NAME2 + " ("
                + USERNAME + " TEXT PRIMARY KEY,"
                + PROFILE_IMAGE + " TEXT)";

        String query3 = "CREATE TABLE " + TABLE_NAME3 + " ("
                + USERNAME + " TEXT,"
                + CONTACT_NO + " TEXT,"
                + LOCATION_FROM + " TEXT,"
                + LOCATION_TO + " TEXT,"
                + FEE + " TEXT,"
                + DESC + " TEXT,"
                + TRUST_POINT + " INT,"
                + TYPE + " TEXT,"
                + MANAGE_STATUS + " TEXT,"
                + UserAccept + " TEXT,"
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT)";
        //Type = request/offer

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
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
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
        db.close();
    }

    //delete account
    public void DeleteAccount(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_NAME1,USERNAME + " = ?", new String[]{username});
        db.delete(TABLE_NAME2,USERNAME + " = ?", new String[]{username});
        db.delete(TABLE_NAME3,USERNAME + " = ?", new String[]{username});
    }

    //delete carpool
    public void DeleteCarpool(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete(TABLE_NAME3,ID + " = " + id, null);
    }

    //Update image
    public void AddProfileImage(String username, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(PROFILE_IMAGE, image);
        db.insert(TABLE_NAME2, null,values);
    }

    //Update image
    public void UpdateProfileImage(String username, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROFILE_IMAGE, image);
        db.update(TABLE_NAME2, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdatePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD,password);
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdateGender(String username, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GENDER, gender);
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdateStatus(String username, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATUS, status);
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdateCarInfo(String username, String car_model, String veh_color, String plate_no, String license_exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_MODEL, car_model);
        values.put(VEH_COLOR, veh_color);
        values.put(PLATE, plate_no);
        values.put(LICENSE_EXP, license_exp);
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdateTrustPoint(String username, int trust_point){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TRUST_POINT, trust_point);
        db.update(TABLE_NAME1, values, USERNAME + " = ?", new String[]{username});
        db.update(TABLE_NAME3, values, USERNAME + " = ?", new String[]{username});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }

    //retrieve all user info.
    public ArrayList<String> searchUserInfo(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME1 + " WHERE name='" + username + "'", null);
        ArrayList<String> arr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arr.add(cursor.getString(0)); //username
                arr.add(cursor.getString(1)); //bio
                arr.add(cursor.getString(2)); //gender
                arr.add(cursor.getString(3)); //contact_no
                arr.add(cursor.getString(4)); //email
                arr.add(cursor.getString(5)); //emergency_contact_name
                arr.add(cursor.getString(6)); //emergency_contact_no
                arr.add(cursor.getString(7)); //password
                arr.add(cursor.getString(8)); //status (driver/ not a driver)
                arr.add(cursor.getString(9)); //car_model
                arr.add(cursor.getString(10)); //vehicle_color
                arr.add(cursor.getString(11)); //plate_number
                arr.add(cursor.getString(12)); //license_expiry_date
                arr.add(cursor.getString(13)); //trust_point
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    // Add Carpool Offer/Request
    public void AddCarpool(String username,String phone_no,String location_from, String location_to, String fee, String desc, int trust_point, String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, username);
        values.put(CONTACT_NO, phone_no);
        values.put(LOCATION_FROM, location_from);
        values.put(LOCATION_TO, location_to);
        values.put(FEE, fee);
        values.put(DESC, desc);
        values.put(TRUST_POINT, trust_point);
        values.put(TYPE, type);
        values.put(MANAGE_STATUS, "false");
        values.put(UserAccept, "");
        db.insert(TABLE_NAME3, null, values);
        db.close();
    }

    //display all data of same username and can filter further by the type wanted.
    public ArrayList<String> displayMyCarpool(String username, String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE name= '" + username + "' AND type= '" + type + "'", null);
        ArrayList<String> arr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arr.add(cursor.getString(0)); //username
                arr.add(cursor.getString(1)); //phone no
                arr.add(cursor.getString(2)); //location from
                arr.add(cursor.getString(3)); //location to
                arr.add(cursor.getString(4)); //fee
                arr.add(cursor.getString(5)); //description
                arr.add(cursor.getString(6)); //trust point
                arr.add(cursor.getString(7)); //type
                arr.add(cursor.getString(8)); //manage status
                arr.add(cursor.getString(9)); //user accept
                arr.add(Integer.toString(cursor.getInt(10))); //id
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }

    //display all data by its type. ( Only for the current user's carpooling )
    public Cursor searchCarpoolingByType(String username, String type)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE type='" + type + "' AND name='" + username + "'", null);
        return cursor;
    }


    //display all data by its type. ( Except for the current user's carpooling )
    public Cursor searchNonUserCarpoolingByType(String username, String type)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE type='" + type + "' AND name!='" + username + "'", null);
        return cursor;
    }

    //second method to searchByType
//    public ArrayList<String> searchCarpoolingByType(String username, String type) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE type='" + type + "' AND name!='" + username + "'", null);
//        ArrayList<String> arr = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                arr.add(cursor.getString(0)); //username
//                arr.add(cursor.getString(1)); //phone no
//                arr.add(cursor.getString(2)); //location from
//                arr.add(cursor.getString(3)); //location to
//                arr.add(cursor.getString(4)); //fee
//                arr.add(cursor.getString(5)); //description
//                arr.add(cursor.getString(6)); //trust point
//                arr.add(cursor.getString(7)); //type
//                arr.add(cursor.getString(8)); //manage status
//                arr.add(cursor.getString(9)); //user accept
//                arr.add(Integer.toString(cursor.getInt(10))); //id
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return arr;
//    }

    // Update Notification and add who accepted the offer/request
    // Boolean True = accepted offer/request
    public void UpdateManageStatus(String username, String userAccept){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MANAGE_STATUS, true);
        values.put(UserAccept, userAccept);
        db.update(TABLE_NAME3, values, USERNAME + " = ?", new String[]{username});
    }

    public void UpdateCarpool(String location, String destination, String note, String fee, Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOCATION_FROM, location);
        values.put(LOCATION_TO, destination);
        values.put(DESC, note);
        values.put(FEE, fee);
        db.update(TABLE_NAME3, values, ID + " = " + id, null);
    }

    //display all data by its current manage status. ( Only if related to the current user's carpooling offer/request)
    //to display notification
    public Cursor searchCarpoolingByStatus(String username, String manage_status)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE manage_status='" + manage_status + "' AND name='" + username + "'", null);
        return cursor;
    }

    //second method to searchByStatus
//    public ArrayList<String> searchCarpoolingByStatus(String username, boolean manage_status) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME3 + " WHERE manage_status='" + manage_status + "' AND name='" + username + "'", null);
//        ArrayList<String> arr = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                arr.add(cursor.getString(0)); //username
//                arr.add(cursor.getString(1)); //phone no
//                arr.add(cursor.getString(2)); //location from
//                arr.add(cursor.getString(3)); //location to
//                arr.add(cursor.getString(4)); //fee
//                arr.add(cursor.getString(5)); //description
//                arr.add(cursor.getString(6)); //trust point
//                arr.add(cursor.getString(7)); //type
//                arr.add(cursor.getString(8)); //manage status
//                arr.add(cursor.getString(9)); //user accept
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return arr;
//    }

    public ArrayList<String> searchUserProfilePicture(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME2 + " where " + USERNAME + " = ?", new String[]{username});
        ArrayList<String> arr = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arr.add(cursor.getString(1)); //profile pic byte[]
            } while (cursor.moveToNext());
        }
        cursor.close();
        return arr;
    }


    //For user registration and login
    public Boolean insertData(String username, String password, String email, String contact){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        //Default picture
        contentValues.put(PROFILE_IMAGE, "file:///storage/emulated/0/Android/data/com.example.unipool/files/DCIM/IMG_20230109_203633960.jpg");
        MyDB.insert(TABLE_NAME2, null, contentValues);

//        ContentValues table3 = new ContentValues();
//        table3.put(USERNAME, username);
//        table3.put(TRUST_POINT, 100);
//        MyDB.insert(TABLE_NAME3, null, table3);

        ContentValues values= new ContentValues();
        values.put(USERNAME, username);
        values.put(BIO, "This is a bio.");
        //default gender is Male
        values.put(GENDER, "Male");
        values.put(CONTACT_NO, contact);
        values.put(EMAIL, email);
        values.put(EMERGENCY_CONT_NAME, "not yet set");
        values.put(EMERGENCY_CONT_NUM, "not yet set");
        values.put(PASSWORD, password);
        values.put(STATUS, "");
//        values.put(CAR_MODEL, "car_model");
//        values.put(VEH_COLOR, "veh_color");
//        values.put(PLATE, "plate_no");
//        values.put(LICENSE_EXP, "license_exp");
        values.put(TRUST_POINT, 100);
//        contentValues.put(USERNAME, username);
//        contentValues.put(PASSWORD, password);
//        contentValues.put(EMAIL, email);
//        contentValues.put(CONTACT_NO, contact);
//        contentValues.put(BIO, "temp");
//        contentValues.put(GENDER, "temp");
//        contentValues.put(EMERGENCY_CONT_NAME, "temp");
//        contentValues.put(EMERGENCY_CONT_NUM, "temp");
        long result = MyDB.insert(TABLE_NAME1, null, values);
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
