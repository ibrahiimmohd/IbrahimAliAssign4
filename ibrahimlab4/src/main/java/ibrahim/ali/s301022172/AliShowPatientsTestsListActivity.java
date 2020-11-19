/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AliShowPatientsTestsListActivity extends AppCompatActivity {
    ArrayList<Tests> tests;
    PatientManager patientManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_show_patients_tests_list);

        getSupportActionBar().setTitle("Tests List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        patientManager = new PatientManager(this);

        String id = getIntent().getExtras().getString("id");

        try {
            tests = patientManager.getPatientTests(Integer.parseInt(id), "patientId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ibrahimShowTestLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(150,10,0,15);

        for(int i = 0; i< tests.size(); i++) {
            TextView deparment = new TextView(this);
            deparment.setText("Test Date:                            " + tests.get(i).getCreatedAt());
            deparment.setTextSize(20);
            deparment.setGravity(Gravity.LEFT);
            deparment.setPadding(5,10,0,10);
            linearLayout.addView(deparment);

            TextView name = new TextView(this);
            name.setText("Patient ID:                            " + tests.get(i).getPatientId());
            name.setTextSize(20);
            name.setGravity(Gravity.LEFT);
            name.setPadding(5,10,0,10);
            linearLayout.addView(name);

            TextView gender = new TextView(this);
            String gr = tests.get(i).getTestCovid19() == 1 ? "Positive" : "Negative";
            gender.setText("Covid:                                   " + gr);
            gender.setTextSize(20);
            gender.setPadding(5,10,0,10);
            gender.setGravity(Gravity.LEFT);
            linearLayout.addView(gender);

            TextView bloodPressure = new TextView(this);
            bloodPressure.setText("Blood Pressure:                  " + tests.get(i).getBloodPressure());
            bloodPressure.setTextSize(20);
            bloodPressure.setGravity(Gravity.LEFT);
            bloodPressure.setPadding(5,10,0,10);
            linearLayout.addView(bloodPressure);

            TextView respRate = new TextView(this);
            respRate.setText("Respiratory Rate:                " + tests.get(i).getRespiratoryRate());
            respRate.setTextSize(20);
            respRate.setGravity(Gravity.LEFT);
            respRate.setPadding(5,10,0,10);
            linearLayout.addView(respRate);

            TextView bloodOxyLvl = new TextView(this);
            bloodOxyLvl.setText("Blood Oxygen Level:          " + tests.get(i).getBloodOxygenLevel());
            bloodOxyLvl.setTextSize(20);
            bloodOxyLvl.setGravity(Gravity.LEFT);
            bloodOxyLvl.setPadding(5,10,0,10);
            linearLayout.addView(bloodOxyLvl);

            TextView heartRate = new TextView(this);
            heartRate.setText("Heart Rate:                          " + tests.get(i).getHeartBeatRate());
            heartRate.setTextSize(20);
            heartRate.setGravity(Gravity.LEFT);
            heartRate.setPadding(5,10,0,10);
            linearLayout.addView(heartRate);

            int testId = i;
            Button btnShowTests = new Button(this);
            btnShowTests.setText("Delete Test");
            btnShowTests.setBackgroundColor(getResources().getColor(R.color.btnColor));
            btnShowTests.setTextColor(getResources().getColor(R.color.white));
            btnShowTests.setLayoutParams(params);
            btnShowTests.setPadding(250,25,250,25);
            btnShowTests.setOnClickListener(v -> {
                try {
                    patientManager.deleteTest(tests.get(testId).getTestId());

                    Toast.makeText(AliShowPatientsTestsListActivity.this,
                            "Test has been deleted", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            linearLayout.addView(btnShowTests);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliShowPatientsTestsListActivity.this,IbrahimActivity.class);
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