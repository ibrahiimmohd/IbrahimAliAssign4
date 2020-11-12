/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
package ibrahim.ali.s301022172;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class IbrahimActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private PatientManager patientManager;
    private EditText txtId, txtPatientName;
    private Spinner spinner, txtPatientDepartment;
    private RadioButton txtPatientGenderM, txtPatientGenderF;
    private Button btnAdd, btnShow, btnEdit;
    private final static String TABLE_NAME = "Patients";
    private int spinnerPosition;
    //sql string to create the table
    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (patientId integer primary key, patientName text, patientGender boolean, patientDepartment text);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibrahim);

        spinner = (Spinner) findViewById(R.id.ibrahimSpinnerInsert);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.deparments_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        //
        txtId = (EditText) findViewById(R.id.ibrahimIdInsert);
        txtPatientName = (EditText) findViewById(R.id.ibrahimNameInsert);
        txtPatientGenderM = (RadioButton) findViewById(R.id.ibrahimGenderM);
        txtPatientGenderF = (RadioButton) findViewById(R.id.ibrahimGenderF);
        txtPatientDepartment = (Spinner) findViewById(R.id.ibrahimSpinnerInsert);
        //
        btnAdd = (Button)findViewById(R.id.ibrahimBtnAdd);
        btnShow = (Button)findViewById(R.id.ibrahimBtnShow);
        btnEdit = (Button)findViewById(R.id.ibrahimBtnEdit);
        // instantiate the StudentManager
        // initialize the tables
        try {
            patientManager = new PatientManager(this);
            //create the table
            patientManager.dbInitialize(TABLE_NAME, tableCreatorString);
        }
        catch(Exception exception)
        {
            Toast.makeText(IbrahimActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }


    }

    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) { }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void addPatient(View view)
    {
        //read values for text fields
        int patientId = Integer.parseInt(txtId.getText().toString());
        String patientName = txtPatientName.getText().toString();
        int patientGender = 0;
        if (txtPatientGenderM.isChecked()) {
            patientGender = 1;
        }
        if (txtPatientGenderF.isChecked()) {
            patientGender = 2;
        }

        //initialize ContentValues object with the new student
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientId",patientId);
        contentValues.put("patientName",patientName);
        contentValues.put("patientGender",patientGender);    //check
        contentValues.put("patientDepartment",txtPatientDepartment.getSelectedItem().toString()); //check
        //
        try {
            patientManager.addRow(contentValues);
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(IbrahimActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }

    public void showPatientsList(View view){
        Intent intent = new Intent(this, AliShowListActivity.class);
        startActivity(intent);
    }

    public void showPatient(View view)
    {
        try {
            Patient patient = patientManager.getPatientById(txtId.getText().toString(), "patientId");
            txtPatientName.setText(patient.getPatientName());
            if (patient.getPatientGender() == 1) {
                txtPatientGenderM.setChecked(true);
            }
            if (patient.getPatientGender() == 2) {
                txtPatientGenderF.setChecked(true);
            }
            txtPatientDepartment.setSelection(getIndex(txtPatientDepartment, patient.getPatientDepartment()));
        }
        catch (Exception exception)
        {
            Toast.makeText(IbrahimActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }
    //
    public void editStudent(View view)
    {
        int patientId = Integer.parseInt(txtId.getText().toString());
        String patienttName = txtPatientName.getText().toString();
        int patientGender = 0;
        if (txtPatientGenderM.isChecked()) {
            patientGender = 1;
        }
        if (txtPatientGenderF.isChecked()) {
            patientGender = 2;
        }

        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("patientId",patientId);
            contentValues.put("patientName",patienttName);
            contentValues.put("patientGender",patientGender);    //check
            contentValues.put("patientDepartment",txtPatientDepartment.getSelectedItem().toString()); //check
            //edit the row
            boolean b= patientManager.editRow(patientId, "patientId", contentValues);
        }
        catch(Exception exception)
        {
            Toast.makeText(IbrahimActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    //If back btn pressed, display alert dialog
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}