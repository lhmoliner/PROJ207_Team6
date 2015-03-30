package com.sait.team6.travelexperts.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sait.team6.travelexperts.classes.Customer;

import java.util.ArrayList;

/*
 * Project Workshop CMPP264 Java - Team 6
 * Instructor: Harvey Peters
 * Created by: Joel D'Arnot
 * Date: March/23/2015
 *
 */

 /* customer tables:
  * CUSTOMERID
  * CUSTFIRSTNAME
  * CUSTLASTNAME
  * CUSTCITY
  * CUSTPOSTAL
  * CUSTCOUNTRY
  * CUSTHOMEPHONE
  * CUSTBUSPHONE
  * CUSTEMAIL
  */
public class CustomerDB {

    // database constants
    public static final String DB_NAME = "travelexperts.db";
    public static final int    DB_VERSION = 1;

    // customer table constants
    public static final String CUSTOMER_TABLE = "customer";

    public static final String CUSTOMERID = "customerid";
    public static final int    CUSTOMERID_COL = 0;

    public static final String CUSTFIRSTNAME = "custfirstname";
    public static final int    CUSTFIRSTNAME_COL = 1;

    public static final String CUSTLASTNAME = "custlastname";
    public static final int    CUSTLASTNAME_COL = 1;

    public static final String CUSTCITY = "custcity";
    public static final int    CUSTCITY_COL = 1;

    public static final String CUSTPOSTAL = "custpostal";
    public static final int    CUSTPOSTAL_COL = 1;

    public static final String CUSTCOUNTRY = "custcountry";
    public static final int    CUSTCOUNTRY_COL = 1;

    public static final String CUSTHOMEPHONE = "custhomephone";
    public static final int    CUSTHOMEPHONE_COL = 1;

    public static final String CUSTBUSPHONE = "custbusphone";
    public static final int    CUSTBUSPHONE_COL = 1;

    public static final String CUSTEMAIL = "custemail";
    public static final int    CUSTEMAIL_COL = 1;


    // CREATE and DROP TABLE statements
    public static final String CREATE_CUSTOMER_TABLE =
            "CREATE TABLE " + CUSTOMER_TABLE + " (" +
                    CUSTOMERID   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CUSTFIRSTNAME + " TEXT NOT NULL," +
                    CUSTLASTNAME + "TEXT NOT NULL," +
                    CUSTCITY + "TEXT NOT NULL);" +
                    CUSTPOSTAL + "TEXT );" +
                    CUSTCOUNTRY + "TEXT );" +
                    CUSTHOMEPHONE + "TEXT );" +
                    CUSTBUSPHONE + "TEXT );" +
                    CUSTEMAIL + "TEXT );";


    public static final String DROP_CUSTOMER_TABLE =
            "DROP TABLE IF EXISTS " + CUSTOMER_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL(CREATE_CUSTOMER_TABLE);
            // db.execSQL(CREATE_TASK_TABLE);

            // insert default lists
            db.execSQL("INSERT INTO agent VALUES (1, 'First', 'Last', 'City', 'Postal' , 'Country', 'Home Phone', 'Bus Phone', 'email@email.com')");
            db.execSQL("INSERT INTO agent VALUES (2, 'Darth', 'Varder' 'Deathstar', '3V1L 0N3', 'Tatooine', '666-666-1234', '1-800-Kill-Jedi', 'jedislayer13@deathstardialup.gal')");
            db.execSQL("INSERT INTO agent VALUES (3, 'Luke', 'Skywalker', 'P.O. Box 000010', 'R2D2 C3P0', 'Tatooine', '587-555-5331', '1-800-Skywalker', 'daddyissues@lightsabremagazine.gal')");
            db.execSQL("INSERT INTO agent VALUES (4, 'Master', 'Yoda', 'Green Swamp', 'L1L N1NJ4', 'Dagobah', '', '', '' )");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Agent list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(CustomerDB.DROP_CUSTOMER_TABLE);
            // db.execSQL(TaskListDB.DROP_TASK_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public CustomerDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    public ArrayList<Customer> getCustomers() {
        String where = "";
//        int customerID = getCustomer(customerFirst).getId();
//        String[] whereArgs = { Integer.toString(listID) };

        this.openReadableDB();
        Cursor cursor = db.query(CUSTOMER_TABLE, null, null, null,
                null, null, null);
        ArrayList<Customer> customers = new ArrayList<Customer>();
        while (cursor.moveToNext()) {
            customers.add(getCustomerFromCursor(cursor));
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return customers;
    }

    public Customer getCustomer(int id) {
        String where = CUSTOMERID + "= ?";
        String[] whereArgs = { Integer.toString(id) };

        this.openReadableDB();
        Cursor cursor = db.query(CUSTOMER_TABLE,
                null, where, whereArgs, null, null, null);
        cursor.moveToFirst();
        Customer customer= getCustomerFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return customer;
    }

    private static Customer getCustomerFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                Customer customer = new Customer(
                        cursor.getInt(CUSTOMERID_COL),
                        cursor.getString(CUSTFIRSTNAME_COL),
                        cursor.getString(CUSTLASTNAME_COL),
                        cursor.getString(CUSTCITY_COL),
                        cursor.getString(CUSTPOSTAL_COL),
                        cursor.getString(CUSTCOUNTRY_COL),
                        cursor.getString(CUSTHOMEPHONE_COL),
                        cursor.getString(CUSTBUSPHONE_COL),
                        cursor.getString(CUSTEMAIL_COL));
                return customer;
            }
            catch(Exception e) {
                return null;
            }
        }
    }
/*

    public long insertCustomer(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMERID, customer.getCustomerid());
        cv.put(CUSTFIRSTNAME, customer.getCustfirstname());
        cv.put(CUSTLASTNAME, customer.getCustlastname());
        cv.put(CUSTCITY, customer.getCustcity());
        cv.put(CUSTPOSTAL, customer.getCustpostal());
        cv.put(CUSTCOUNTRY, customer.getCustcountry());
        cv.put(CUSTHOMEPHONE, customer.getCusthomephone());
        cv.put(CUSTBUSPHONE, customer.getCustbusphone());
        cv.put(CUSTEMAIL, customer.getCustemail());

        this.openWriteableDB();
        long rowID = db.insert(CUSTOMER_TABLE, null, cv);
        this.closeDB();

        return rowID;
    }

    public int updateCustomer(Customer customer) {
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMERID, customer.getCustomerid());
        cv.put(CUSTFIRSTNAME, customer.getCustfirstname());
        cv.put(CUSTLASTNAME, customer.getCustlastname());
        cv.put(CUSTCITY, customer.getCustcity());
        cv.put(CUSTPOSTAL, customer.getCustpostal());
        cv.put(CUSTCOUNTRY, customer.getCustcountry());
        cv.put(CUSTHOMEPHONE, customer.getCusthomephone());
        cv.put(CUSTBUSPHONE, customer.getCustbusphone());
        cv.put(CUSTEMAIL, customer.getCustemail());

        String where = CUSTOMERID + "= ?";
        String[] whereArgs = { String.valueOf(customer.getCustomerid()) };

        this.openWriteableDB();
        int rowCount = db.update(CUSTOMER_TABLE, cv, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

    public int deleteCustomer(long id) {
        String where = CUSTOMERID + "= ?";
        String[] whereArgs = { String.valueOf(id) };

        this.openWriteableDB();
        int rowCount = db.delete(CUSTOMER_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }
*/

}