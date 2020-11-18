package ibrahim.ali.s301022172;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AliAddTestsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    PatientManager patientManager;
    ArrayList<Patients> patients;
    DatePickerDialog picker;
    EditText datePicker;
    String id, name;
    Spinner spinner;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_add_tests);

        getSupportActionBar().setTitle("Add Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        patientManager = new PatientManager(this);

        datePicker = (EditText) findViewById(R.id.ibrahimAddTestDateInsert);
        datePicker.setInputType(InputType.TYPE_NULL);
        datePicker.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(AliAddTestsActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            datePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
        });

        spinner = (Spinner) findViewById(R.id.ibrahimPatientSpinner);
        spinner.setOnItemSelectedListener(this);

        patients = new ArrayList<>();
        try {
            patients = patientManager.getPatientsList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                patients, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");

        TextView idTV = (TextView) findViewById(R.id.ibrahimAddTestIdShow);
        TextView nameTV = (TextView) findViewById(R.id.ibrahimAddTestNameShow);

        idTV.setText(id);
        nameTV.setText(name);

    }

    public void addPatientTest(View view)
    {
        RadioButton patientCovidP = (RadioButton) findViewById(R.id.ibrahimPositiveRadioBtn);
        RadioButton patientCovidN = (RadioButton) findViewById(R.id.ibrahimNegativeRadioBtn);
        EditText createdAt = (EditText) findViewById(R.id.ibrahimAddTestDateInsert);
        EditText bloodPressure = (EditText) findViewById(R.id.ibrahimAddTestBPInsert);
        EditText respiratoryRate = (EditText) findViewById(R.id.ibrahimAddTestRRInsert);
        EditText bloodOxygenLevel = (EditText) findViewById(R.id.ibrahimAddTestBOLInsert);
        EditText heartBeatRate = (EditText) findViewById(R.id.ibrahimAddTestHRInsert);

        int patientCovidTest = 0;
        if (patientCovidP.isChecked()) {
            patientCovidTest = 1;
        }
        if (patientCovidN.isChecked()) {
            patientCovidTest = 2;
        }
        String date = createdAt.getText().toString();
        int bp = Integer.parseInt(bloodPressure.getText().toString());
        int rr = Integer.parseInt(respiratoryRate.getText().toString());
        int bop = Integer.parseInt(bloodOxygenLevel.getText().toString());
        int hr = Integer.parseInt(heartBeatRate.getText().toString());
        int patientId = Integer.parseInt(id);

        //read values for text fields
        //initialize ContentValues object with the new student
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientId",patientId);
        contentValues.put("covid19",patientCovidTest); //check
        contentValues.put("createdAt",date);    //check
        contentValues.put("bloodPressure",bp); //check
        contentValues.put("respiratoryRate",rr); //check
        contentValues.put("bloodOxygenLevel",bop); //check
        contentValues.put("heartBeatRate",hr); //check

        try {
            patientManager.addPatientTestRow(contentValues);
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(AliAddTestsActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliAddTestsActivity.this,IbrahimActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}