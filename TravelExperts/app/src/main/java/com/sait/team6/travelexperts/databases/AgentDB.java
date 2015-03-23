package com.sait.team6.travelexperts.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sait.team6.travelexperts.classes.Agent;

import java.util.ArrayList;

/**
 * Created by 677958 on 3/23/2015.
 */
public class AgentDB {
    // database constants
    public static final String DB_NAME = "travelExperts.db";
    public static final int DB_VERSION = 5;

    // Agents table constants
    public static final String AGENTS_TABLE = "agents";

    public static final String AGENT_ID = "agentId";
    public static final int AGENT_ID_COL = 0;

    public static final String AGENT_FIRST_NAME = "agtFirstName";
    public static final int AGENT_FIRST_NAME_COL = 1;

    public static final String AGENT_MIDDLE_INITIAL = "agtMiddleInitial";
    public static final int AGENT_MIDDLE_INITIAL_COL = 2;

    public static final String AGENT_LAST_NAME = "agtLastName";
    public static final int AGENT_LAST_NAME_COL = 3;

    public static final String AGENT_PHONE = "agtBusPhone";
    public static final int AGENT_PHONE_COL = 4;

    public static final String AGENT_EMAIL = "agtEmail";
    public static final int AGENT_EMAIL_COL = 5;

    public static final String AGENT_POSITION = "agtPosition";
    public static final int AGENT_POSITION_COL = 6;

    public static final String AGENCY_ID = "agencyId";
    public static final int AGENCY_ID_COL = 7;

    public static final String PROFILE = "profile";
    public static final int PROFILE_COL = 8;


    public static final String CREATE_AGENTS_TABLE =
            "CREATE TABLE " + AGENTS_TABLE + " (" +
                    AGENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AGENT_FIRST_NAME + " TEXT    NOT NULL, " +
                    AGENT_MIDDLE_INITIAL + " TEXT, " +
                    AGENT_LAST_NAME + " TEXT  NOT NULL, " +
                    AGENT_PHONE + " TEXT  NOT NULL, " +
                    AGENT_EMAIL + " TEXT  NOT NULL, " +
                    AGENT_POSITION + " TEXT  NOT NULL, " +
                    AGENCY_ID + " TEXT  NOT NULL, " +
                    PROFILE + " TEXT NOT NULL);";


    public static final String DROP_AGENTS_TABLE =
            "DROP TABLE IF EXISTS " + AGENTS_TABLE;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_AGENTS_TABLE);// create table

            db.execSQL("INSERT INTO agents VALUES (1,  'Luis', 'L.', 'Fernandez', '(822) 394-8694', 'lfernandez@te.com', 'Senior Agent', 2, 'p1')");// insert default lists
            db.execSQL("INSERT INTO agents VALUES (2,  'Mayra', 'M.', 'Knit', '(855) 319-3037', 'mknit@te.com', 'Senior Agent', 1, 'p2')");
            db.execSQL("INSERT INTO agents VALUES (3,  'Jesse', 'N.', 'Richards', '(822) 051-7614', 'jrichards@te.com', 'Junior Agent', 2, 'p3')");
            db.execSQL("INSERT INTO agents VALUES (4,  'Malcolm', 'X.', 'Hines', '(833) 609-5622', 'mhines@te.com', 'Junior Agent', 1, 'p4')");
            db.execSQL("INSERT INTO agents VALUES (5,  'Randal', 'L.', 'Mcgee', '(899) 279-9641', 'rmcgee@te.com', 'Junior Agent', 1, 'p5')");
            db.execSQL("INSERT INTO agents VALUES (6,  'Joe', 'R.', 'Parker', '(811) 612-6593', 'jparker@te.com', 'Junior Agent', 1, 'p6')");
            db.execSQL("INSERT INTO agents VALUES (7,  'Hilda', 'T.', 'Parker', '(833) 553-2777', 'hparker@te.com', 'Junior Agent', 2, 'p7')");
            db.execSQL("INSERT INTO agents VALUES (8,  'Roxanne', ' ', 'Glover', '(844) 779-4925', 'rglover@te.com', 'Junior Agent', 2, 'p8')");
            db.execSQL("INSERT INTO agents VALUES (9,  'Nick', 'L.', 'Poole', '(855) 940-6925', 'npoole@te.com', 'Senior Agent', 2, 'p9')");
            db.execSQL("INSERT INTO agents VALUES (10, 'Becky', 'M.', 'Garrett', '(833) 481-9856', 'bgarrett@te.com', 'Senior Agent', 1, 'p10')");
            db.execSQL("INSERT INTO agents VALUES (11, 'Samuel', 'N.', 'Jacobs', '(844) 095-8632', 'sjacobs@te.com', 'Junior Agent', 1, 'p11')");
            db.execSQL("INSERT INTO agents VALUES (12, 'Laverne', 'X.', 'Mckinney', '(844) 128-0532', 'lmckinney@te.com', 'Junior Agent', 1, 'p12')");
            db.execSQL("INSERT INTO agents VALUES (13, 'Opal', 'L.', 'Alvarado', '(833) 349-8316', 'oalvarado@te.com', 'Junior Agent', 2, 'p13')");
            db.execSQL("INSERT INTO agents VALUES (14, 'Percy', 'R.', 'Parker', '(844) 731-0023', 'pparker@te.com', 'Junior Agent', 1, 'p14')");
            db.execSQL("INSERT INTO agents VALUES (15, 'Hilda', 'T.', 'Marc', '(844) 837-3357', 'hmarc@te.com', 'Junior Agent', 2, 'p15')");
            db.execSQL("INSERT INTO agents VALUES (16, 'Alan', ' ', 'Wilkerson', '(899) 992-9152', 'awilkerson@te.com', 'Junior Agent', 1, 'p16')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.d("Agents information", "Upgrading db from version " + oldVersion + " to " + newVersion);
            db.execSQL(AgentDB.DROP_AGENTS_TABLE);
            onCreate(db);
        }
    }

    private SQLiteDatabase db;// database and database helper objects
    private DBHelper dbHelper;

    public AgentDB(Context context) { dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION); }// constructor

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void closeDB() { if (db != null) db.close(); }

    public ArrayList<Agent> getAgents() {

        this.openReadableDB();
        Cursor cursor = db.query(AGENTS_TABLE, null, null, null, null, null, null, null);
        ArrayList<Agent> agents = new ArrayList<Agent>();

        while (cursor.moveToNext()) {
            agents.add(getAgentFromCursor(cursor));
        }
        if (cursor != null) cursor.close();

        this.closeDB();
        return agents;
    }

    public Agent getAgent(int id) {
        String where = AGENT_ID + "= ?";
        String[] whereArgs = { Integer.toString(id + 1) };

        this.openReadableDB();
        Cursor cursor = db.query(AGENTS_TABLE, null, where, whereArgs, null, null, null, null);
        cursor.moveToFirst();
        Agent agent = getAgentFromCursor(cursor);
        if (cursor != null)
            cursor.close();
        this.closeDB();

        return agent;
    }

    private static Agent getAgentFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0){
            return null;
        }
        else {
            try {
                return new Agent(
                        cursor.getInt(AGENT_ID_COL),
                        cursor.getString(AGENT_FIRST_NAME_COL),
                        cursor.getString(AGENT_MIDDLE_INITIAL_COL),
                        cursor.getString(AGENT_LAST_NAME_COL),
                        cursor.getString(AGENT_PHONE_COL),
                        cursor.getString(AGENT_EMAIL_COL),
                        cursor.getString(AGENT_POSITION_COL),
                        cursor.getInt(AGENCY_ID_COL),
                        cursor.getString(PROFILE_COL));
            }
            catch(Exception e) {
                return null;
            }
        }
    }

}
