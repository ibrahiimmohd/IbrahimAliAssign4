package ibrahim.ali.s301022172;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AliAddTestActivity extends AppCompatActivity {
    PatientManager patientManager;
    Patients test;
    DatePickerDialog picker;
    EditText datePicker;
    Button btn;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_add_test);

        patientManager = new PatientManager(this);

        datePicker = (EditText) findViewById(R.id.editTextDate);
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
    }

    public void addPatientTest(View view)
    {
        //read values for text fields
        //initialize ContentValues object with the new student
        ContentValues contentValues = new ContentValues();
        contentValues.put("patientId",2);
        contentValues.put("covid19",1); //check
        contentValues.put("createdAt","Hi");    //check
        contentValues.put("bloodPressure",200); //check
        contentValues.put("respiratoryRate",50); //check
        contentValues.put("bloodOxygenLevel",100); //check
        contentValues.put("heartBeatRate",100); //check

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
}