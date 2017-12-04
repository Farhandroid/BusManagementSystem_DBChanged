package tanvir.busmanagementsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class AdminActivity extends AppCompatActivity {

    AutoCompleteTextView fromLocationET , toLocationET;
    EditText selectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        ArrayList<String> countryNameArrayList = new ArrayList<String>();
        Resources res = getResources();
        Collections.addAll(countryNameArrayList, res.getStringArray(R.array.country_array));

        fromLocationET = (AutoCompleteTextView) findViewById(R.id.fromLocationETAD);
        toLocationET= (AutoCompleteTextView) findViewById(R.id.toLocationETAD);
        selectDate = (EditText) findViewById(R.id.selectJourneyDateETAD);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countryNameArrayList);
        toLocationET.setAdapter(adapter);
        fromLocationET.setAdapter(adapter);
    }

    public void startAddBusActivity(View view) {

        Intent myIntent = new Intent(this, AddBusActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
      this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();

    }

    public void startAddBusScheduleActivity(View view) {

        Intent myIntent = new Intent(this, AddBusScheDuleActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();

    }

    public void startMainActivity(View view) {
        SharedPreferences.Editor editor = getSharedPreferences("loginInfo", MODE_PRIVATE).edit();
        editor.putString("isLogged?", "no");
        editor.apply();


        Intent myIntent = new Intent(this, MainActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }



    public void selectDateAD(View view) {
        DatePickerDialog datePickerDialog;

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);



        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                selectDate.setText(dayOfMonth + "/"
                        + (month + 1) + "/" + year);

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        datePickerDialog.show();
    }


    public void searchBusAD(View view) {

        Intent intent = new Intent(this, BustListAV.class);

        //Toast.makeText(this, "Select date : "+selectDate.getText().toString(), Toast.LENGTH_SHORT).show();

        if (fromLocationET.getText().toString().length()>0 && toLocationET.getText().toString().length()>0 && selectDate.getText().toString().length()>0 && selectDate.getText().toString().contains("/") )
        {
            SharedPreferences.Editor editor = getSharedPreferences("bus_data",MODE_PRIVATE).edit();

            editor.putString("startFrom",fromLocationET.getText().toString());
            editor.putString("destination",toLocationET.getText().toString());
            editor.putString("departureDate",selectDate.getText().toString());
            editor.apply();


            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            this.startActivity(intent);
            overridePendingTransition(R.anim.left_in,R.anim.left_out);
            finish();
        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }


    }
}
