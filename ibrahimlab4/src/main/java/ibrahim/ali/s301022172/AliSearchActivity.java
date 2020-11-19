/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AliSearchActivity extends AppCompatActivity {

    PatientManager patientManager;
    Patients patient;
    private EditText txtIdInsert;
    private TextView txtId, txtPatientName, txtPatientGender, txtPatientDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_search);

        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        txtId = (TextView) findViewById(R.id.ibrahimPIdShow);
        txtPatientName = (TextView) findViewById(R.id.ibrahimPatientNShow);
        txtPatientGender = (TextView) findViewById(R.id.ibrahimPGenderShow);
        txtPatientDepartment = (TextView) findViewById(R.id.ibrahimPatientDeparShow);
    }

    public void showPatient(View view)
    {
        txtIdInsert = (EditText) findViewById(R.id.ibrahimSearchIdInsert);

        Toast.makeText(AliSearchActivity.this,
                txtIdInsert.getText().toString(), Toast.LENGTH_SHORT).show();

        try {
            patientManager = new PatientManager(this);
            patient = patientManager.getPatientById(txtIdInsert.getText().toString(), "patientId");

            txtId.setText(String.valueOf(patient.getPatientId()));
            txtPatientName.setText(patient.getPatientName());
            txtPatientGender.setText(patient.getPatientGender() == 1 ? "Male" : "Female");
            txtPatientDepartment.setText(patient.getPatientDepartment());
        }
        catch (Exception exception)
        {
            Toast.makeText(AliSearchActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliSearchActivity.this,IbrahimActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}