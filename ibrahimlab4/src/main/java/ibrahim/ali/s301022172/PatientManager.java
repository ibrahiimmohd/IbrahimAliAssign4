package ibrahim.ali.s301022172;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PatientManager extends SQLiteOpenHelper {
    //database name and version
    private static final String DATABASE_NAME = "PatientDB";
    private static final int DATABASE_VERSION = 4;
    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    private static String tableNameP;
    private static String tableCreatorStringP;

    private static String tableNameT;
    private static String tableCreatorStringT;

    // no-argument constructor
    public PatientManager(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    // Called when the database is created for the first time.
    // This is where the creation of tables and the initial population
    // of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(tableCreatorStringP);
        db.execSQL(tableCreatorStringT);
    }
    //
    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables,
    // add tables, or do anything else it needs to upgrade
    // to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableNameP);
        db.execSQL("DROP TABLE IF EXISTS " + tableNameT);
        //recreate the table
        onCreate(db);
    }
    //
    //
    //
    // initialize table names and CREATE TABLE statement
    // called by activity to create a table in the database
    // The following arguments should be passed:
    // tableName - a String variable which holds the table name
    // tableCreatorString - a String variable which holds the CREATE Table statement

    public void dbInitialize(String TABLE_PATIENTS, String tableCreatorStringPatients, String TABLE_TESTS, String tableCreatorStringTests)
    {
        this.tableNameP =TABLE_PATIENTS;
        this.tableCreatorStringP =tableCreatorStringPatients;

        this.tableNameT =TABLE_TESTS;
        this.tableCreatorStringT =tableCreatorStringTests;

    }
    //
    // CRUD Operations
    //
    //This method is called by the activity to add a row in the table
    // The following arguments should be passed:
    // values - a ContentValues object that holds row values
    public boolean addRow  (ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr= db.insert("Patients", null, values);
        db.close(); //close database connection
        return nr> -1;
    }

    // This method returns a task object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Patients getPatientById(Object id, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from Patients where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Patients patients = new Patients(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of task object
            patients.setPatientId(cursor.getInt(0));
            patients.setPatientName(cursor.getString(1));
            patients.setPatientGender(cursor.getInt(2));
            patients.setPatientDepartment(cursor.getString(3));
            cursor.close();
        } else {
            patients = null;
        }
        db.close();
        return patients;
    }

    public ArrayList<Patients> getPatientsList() throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Patients> patientsList = new ArrayList<>();
        String query = "SELECT patientId, patientName, patientGender, patientDepartment FROM Patients";
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Patients patients = new Patients();
            patients.setPatientId(cursor.getInt(0));
            patients.setPatientName(cursor.getString(1));
            patients.setPatientGender(cursor.getInt(2));
            patients.setPatientDepartment(cursor.getString(3));

            patientsList.add(patients);
        }
        cursor.close();
        return patientsList;
    }

    public ArrayList<Tests> getPatientTests(Object id, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Tests> testsList = new ArrayList<>();
        String query = "SELECT a.patientId, a.covid19, a.createdAt, a.bloodPressure, a.respiratoryRate, a.bloodOxygenLevel, a.heartBeatRate FROM Tests a INNER JOIN Patients b ON a.patientId=b.patientId where a." + fieldName + " = " + String.valueOf(id);
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Tests tests = new Tests();
            tests.setPatientId(cursor.getInt(0));
            tests.setTestCovid19(cursor.getInt(1));
            tests.setCreatedAt(cursor.getString(2));
            tests.setBloodPressure(cursor.getInt(3));
            tests.setRespiratoryRate(cursor.getInt(4));
            tests.setBloodOxygenLevel(cursor.getInt(5));
            tests.setHeartBeatRate(cursor.getInt(6));

            testsList.add(tests);
        }
        cursor.close();
        return testsList;
    }

    public boolean addPatientTestRow  (ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr= db.insert("Tests", null, values);
        db.close(); //close database connection
        return nr> -1;
    }
    //
    //
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    // values - a ContentValues object that holds row values
    public boolean editRow (Object id, String fieldName, ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        //
        int nr = db.update("Patients", values, fieldName + " = ? ", new String[]{String.valueOf(id)});
        return nr > 0;
    }
}