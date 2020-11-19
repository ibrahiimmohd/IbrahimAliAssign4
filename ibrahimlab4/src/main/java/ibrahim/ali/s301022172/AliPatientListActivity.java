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

import java.util.ArrayList;

public class AliPatientListActivity extends AppCompatActivity {
    ArrayList<Patients> patients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_show_list);

        getSupportActionBar().setTitle("Patients List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        PatientManager patientManager = new PatientManager(this);

        try {
            patients = patientManager.getPatientsList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ibrahimShowPatientsLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(150,10,0,15);

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
            deparment.setText("Department:          " + patients.get(i).getPatientDepartment());
            deparment.setTextSize(20);
            deparment.setGravity(Gravity.LEFT);
            deparment.setPadding(5,10,0,10);
            linearLayout.addView(deparment);

            int patientId = i;
            Button btnShowTests = new Button(this);
            btnShowTests.setText("Show Test(s)");
            btnShowTests.setBackgroundColor(getResources().getColor(R.color.btnColor));
            btnShowTests.setTextColor(getResources().getColor(R.color.white));
            btnShowTests.setLayoutParams(params);
            btnShowTests.setPadding(250,25,250,25);
            btnShowTests.setOnClickListener(v -> {

                Intent intent = new Intent(AliPatientListActivity.this,AliShowPatientsTestsListActivity.class);
                intent.putExtra("id", String.valueOf(patients.get(patientId).getPatientId()));
                startActivity(intent);
            });
            linearLayout.addView(btnShowTests);

            Button btnAddTests = new Button(this);
            btnAddTests.setText("Add Test(s)");
            btnAddTests.setBackgroundColor(getResources().getColor(R.color.btnColor));
            btnAddTests.setTextColor(getResources().getColor(R.color.white));
            btnAddTests.setLayoutParams(params);
            btnAddTests.setPadding(250,25,290,25);
            btnAddTests.setOnClickListener(v -> {

                Intent intent = new Intent(AliPatientListActivity.this,AliAddTestActivity.class);
                intent.putExtra("id", String.valueOf(patients.get(patientId).getPatientId()));
                intent.putExtra("name", String.valueOf(patients.get(patientId).getPatientName()));
                startActivity(intent);
            });
            linearLayout.addView(btnAddTests);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            Intent intent = new Intent(AliPatientListActivity.this,IbrahimActivity.class);
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