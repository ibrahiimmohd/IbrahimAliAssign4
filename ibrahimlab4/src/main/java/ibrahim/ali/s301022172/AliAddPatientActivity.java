/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AliAddPatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private PatientManager patientManager;
    private EditText txtId, txtPatientName;
    private Spinner spinner, txtPatientDepartment;
    private RadioButton txtPatientGenderM, txtPatientGenderF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_add_patient);

        getSupportActionBar().setTitle("Add Patient");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        patientManager = new PatientManager(this);


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
    }

    public void addPatient(View view)
    {
        if(txtId.getText().toString().isEmpty() != true && txtPatientName.getText().toString().isEmpty() != true){
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
                if(txtId.getText().toString().trim().length() == 4){
                    ArrayList<Patients> patients = patientManager.getPatientsList();
                    for(int i=0; i< patients.size(); i++) {
                        if(patients.get(i).getPatientId() == Integer.parseInt(txtId.getText().toString())){
                            Toast.makeText(AliAddPatientActivity.this,"Id must be unique", Toast.LENGTH_SHORT).show();
                            break;
                        }else if(patients.size() == i+1){
                            if(patientName.length() >= 2){
                                patientManager.addRow(contentValues);
                                Toast.makeText(AliAddPatientActivity.this,"New patient has been added", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AliAddPatientActivity.this,"Name must be 2 or more letters", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }else{
                    txtId.setError("Must be 4 unique digits");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(AliAddPatientActivity.this,"Please insert new patient values", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) { }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliAddPatientActivity.this,IbrahimActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.search) {
            Intent intent = new Intent(this, AliSearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}