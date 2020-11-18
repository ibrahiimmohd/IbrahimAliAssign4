package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
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
        patientManager = new PatientManager(this);
        String id = getIntent().getExtras().getString("id");

        try {
            tests = patientManager.getPatientTests(Integer.parseInt(id), "patientId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ibrahimShowTestLayout);

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
            btnShowTests.setPadding(5,10,0,10);
            btnShowTests.setOnClickListener(v -> {
                try {
                    patientManager.deleteTest(tests.get(testId).getTestId());

                    Toast.makeText(AliShowPatientsTestsListActivity.this,
                            String.valueOf(tests.get(testId).getTestId()), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            linearLayout.addView(btnShowTests);
        }
    }
}