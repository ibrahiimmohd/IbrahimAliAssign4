package ibrahim.ali.s301022172;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class AliShowListActivity extends AppCompatActivity {
    ArrayList<Patient> patients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_show_list);
        PatientManager patientManager = new PatientManager(this);

        try {
            patients = patientManager.getPatientsList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ibrahimLayout);

        for(int i=0; i< patients.size(); i++) {
            TextView id = new TextView(this);
            id.setText("ID:                           " + Integer.toString(patients.get(i).getPatientId()));
            id.setTextSize(20);
            id.setGravity(Gravity.LEFT);
            id.setPadding(5,50,0,10);
            linearLayout.addView(id);

            TextView name = new TextView(this);
            name.setText("Name:                    " + patients.get(i).getPatientName());
            name.setTextSize(20);
            name.setGravity(Gravity.LEFT);
            name.setPadding(5,10,0,10);
            linearLayout.addView(name);

            TextView gender = new TextView(this);
            String gr = patients.get(i).getPatientGender() == 1 ? "Male" : "Female";
            gender.setText("Gender:                  " + gr);
            gender.setTextSize(20);
            gender.setPadding(5,10,0,10);
            gender.setGravity(Gravity.LEFT);
            linearLayout.addView(gender);

            TextView deparment = new TextView(this);
            deparment.setText("Department:         " + patients.get(i).getPatientDepartment());
            deparment.setTextSize(20);
            deparment.setGravity(Gravity.LEFT);
            deparment.setPadding(5,10,0,10);
            linearLayout.addView(deparment);
        }
    }
}