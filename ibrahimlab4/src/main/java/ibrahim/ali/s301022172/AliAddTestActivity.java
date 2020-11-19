/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AliAddTestActivity extends AppCompatActivity {
    PatientManager patientManager;
    DatePickerDialog picker;
    EditText datePicker;
    String id, name;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_add_test);

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
            picker = new DatePickerDialog(AliAddTestActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            datePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            picker.show();
        });

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
            Toast.makeText(AliAddTestActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliAddTestActivity.this,IbrahimActivity.class);
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