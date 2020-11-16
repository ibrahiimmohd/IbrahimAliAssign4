package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
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

        try {
            tests = patientManager.getPatientTests(2, "patientId");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ibrahimShowTestLayout);

        for(int i = 0; i< tests.size(); i++) {
            TextView id = new TextView(this);
            id.setText("ID:                           " + Integer.toString(tests.get(i).getBloodPressure()));
            id.setTextSize(20);
            id.setGravity(Gravity.LEFT);
            id.setPadding(5,50,0,10);
            linearLayout.addView(id);

//            TextView name = new TextView(this);
//            name.setText("Name:                    " + tests.get(i).getPatientName());
//            name.setTextSize(20);
//            name.setGravity(Gravity.LEFT);
//            name.setPadding(5,10,0,10);
//            linearLayout.addView(name);
//
//            TextView gender = new TextView(this);
//            String gr = tests.get(i).getPatientGender() == 1 ? "Male" : "Female";
//            gender.setText("Gender:                  " + gr);
//            gender.setTextSize(20);
//            gender.setPadding(5,10,0,10);
//            gender.setGravity(Gravity.LEFT);
//            linearLayout.addView(gender);
//
//            TextView deparment = new TextView(this);
//            deparment.setText("Department:          " + tests.get(i).getPatientDepartment());
//            deparment.setTextSize(20);
//            deparment.setGravity(Gravity.LEFT);
//            deparment.setPadding(5,10,0,10);
//            linearLayout.addView(deparment);
//
//            Button btnShowTests = new Button(this);
//            btnShowTests.setText("Show Test(s)");
//            btnShowTests.setOnClickListener(v -> {
//
//                Intent intent = new Intent(AliShowPatientsTestsListActivity.this,AliAddTestActivity.class);
//                //patients.get(i).getPatientId()
//                startActivity(intent);
//            });
//            linearLayout.addView(btnShowTests);
        }
    }
}