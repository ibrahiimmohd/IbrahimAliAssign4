/**
 * Full Name: Ibrahim Ali
 * Student ID: 301022172
 * Section: COMP 304 - 002
 * */
package ibrahim.ali.s301022172;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IbrahimActivity extends AppCompatActivity {

    private PatientManager patientManager;
    FloatingActionButton startFab, addFab, testFab;
    private Boolean clicked = false;
    Intent intent;

    private final static String TABLE_PATIENTS = "Patients";
    //sql string to create the table
    private static final String tableCreatorStringPatients =
            "CREATE TABLE "+ TABLE_PATIENTS + " (patientId integer primary key, patientName text, patientGender boolean, patientDepartment text);";

    private final static String TABLE_TESTS = "Tests";
    //sql string to create the table
    private static final String tableCreatorStringTests =
            "CREATE TABLE "+ TABLE_TESTS + " ( testId integer primary key AUTOINCREMENT, patientId integer, createdAt text, bloodPressure integer, respiratoryRate integer, bloodOxygenLevel integer, heartBeatRate integer, covid19 boolean, FOREIGN KEY (patientId) REFERENCES Patients(patientId));";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibrahim);

        // instantiate the StudentManager
        // initialize the tables
        try {
            patientManager = new PatientManager(this);
            //create the table
            patientManager.dbInitialize(TABLE_PATIENTS, tableCreatorStringPatients, TABLE_TESTS, tableCreatorStringTests);
        }
        catch(Exception exception)
        {
            Toast.makeText(IbrahimActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

        startFab = (FloatingActionButton) findViewById(R.id.ibrahimFabStart);
        startFab.setOnClickListener(v -> onAddButtonClicked());

        addFab = (FloatingActionButton) findViewById(R.id.ibrahimFabAddPatient);
        addFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(IbrahimActivity.this, AliAddPatientActivity.class);
                startActivity(intent);
            }
        });

        testFab = (FloatingActionButton) findViewById(R.id.ibrahimFabAddTest);
        testFab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(IbrahimActivity.this, AliAddTestsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        setClickable(clicked);
        clicked = !clicked;
    }

    private void setVisibility(Boolean clicked){

        if(!clicked){
            addFab.setVisibility(View.VISIBLE);
            testFab.setVisibility(View.VISIBLE);
        }else{
            addFab.setVisibility(View.INVISIBLE);
            testFab.setVisibility(View.INVISIBLE);
        }
    }

    private void setAnimation(Boolean clicked){

        Animation rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open);
        Animation rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close);
        Animation rotateFromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);
        Animation rotateToBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom);

        if(!clicked){
            startFab.startAnimation(rotateOpen);
            addFab.startAnimation(rotateFromBottom);
            testFab.startAnimation(rotateFromBottom);
        }else{
            startFab.startAnimation(rotateClose);
            addFab.startAnimation(rotateToBottom);
            testFab.startAnimation(rotateToBottom);
        }
    }

    private void setClickable(Boolean clicked){
        if(!clicked){
            addFab.setClickable(true);
            testFab.setClickable(true);

        }else{
            addFab.setClickable(false);
            testFab.setClickable(false);
        }
    }

    public void showPatientsList(View view){
        Intent intent = new Intent(this, AliPatientListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search) {
            intent = new Intent(this, AliSearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //If back btn pressed, display alert dialog
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}