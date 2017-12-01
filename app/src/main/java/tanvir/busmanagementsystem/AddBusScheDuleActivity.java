package tanvir.busmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.Database.TableAttribute;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;

public class AddBusScheDuleActivity extends AppCompatActivity {

    EditText departureDateET , departureTimeET , arrivalDateET , arrivalTimeET ;
    AutoCompleteTextView busIdET , busNameET;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_sche_dule);

        databaseHelper = new DatabaseHelper(this);

        departureDateET = (EditText) findViewById(R.id.departureDateET);
        departureTimeET = (EditText) findViewById(R.id.departureTimeET);
        arrivalDateET = (EditText) findViewById(R.id.arrivalDateET);
        arrivalTimeET= (EditText) findViewById(R.id.arrivalTimeET);
        busNameET= (AutoCompleteTextView) findViewById(R.id.busNameETSQ);
        busIdET= (AutoCompleteTextView) findViewById(R.id.busIdETSQ);

        setAutoComplete();
    }

    public void selectDate(View view) {

        DatePickerDialog datePickerDialog;

        final String[] Date = {""};

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);



        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                departureDateET.setText((dayOfMonth + "/"
                        + (month + 1) + "/" + year));


            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        datePickerDialog.show();


    }





    public void selectDateArrival(View view) {

        DatePickerDialog datePickerDialog;

        final String[] Date = {""};

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);



        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                arrivalDateET.setText((dayOfMonth + "/"
                        + (month + 1) + "/" + year));


            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        datePickerDialog.show();


    }


    public void setAutoComplete()
    {

        ArrayList<String> busNameArrayList = new ArrayList<>();
        ArrayList<String> busIdArrayList=new ArrayList<>();

        Cursor  cursor = databaseHelper.getBusNameId();

        if (cursor.getCount()>0)
        {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                busNameArrayList.add(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_NAME)));
                busIdArrayList.add(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_ID)));

                cursor.moveToNext();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,busNameArrayList);
        busNameET.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,busIdArrayList);
        busIdET.setAdapter(adapter2);


    }

    public void addBusSchedule(View view) {

        String busId = busIdET.getText().toString();
        String busName = busNameET.getText().toString();
        String departureDate = departureDateET.getText().toString();
        String departureTime = departureTimeET.getText().toString();
        String arrivalDate = arrivalDateET.getText().toString();
        String arrivalTime = arrivalTimeET.getText().toString();

        BusScheduleInfoMC busScheduleInfoMC = new BusScheduleInfoMC(busId,busName,departureDate,departureTime,arrivalDate,arrivalTime);

        ///Toast.makeText(this, busName+"\n"+busId+"\n"+departureDate+"\n"+departureTime+"\n"+arrivalDate+"\n"+arrivalTime+"\n", Toast.LENGTH_LONG).show();

        Boolean aBoolean = databaseHelper.addBusScheduleInDatabase(busScheduleInfoMC);

        if (aBoolean)
            Toast.makeText(this, "SckSex", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Fuck", Toast.LENGTH_SHORT).show();
    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(this, AdminActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }

    public void selectDepartureTime(View view) {
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);



        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hour = Integer.toString(hourOfDay);
                String mint = Integer.toString(minute);


                if (hourOfDay<10)
                    hour="0"+hour;

                if (minute<10)
                    mint="0"+mint;

                departureTimeET.setText(hour+" : "+mint + " ");


            }
        },hour,minute,true);

        timePickerDialog.show();



    }

    public void selectArrivalTime(View view) {

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);





        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String hour = Integer.toString(hourOfDay);
                String mint = Integer.toString(minute);


                if (hourOfDay<10)
                    hour="0"+hour;

                if (minute<10)
                    mint="0"+mint;

                arrivalTimeET.setText(hour+" : "+mint + " ");


            }
        },hour,minute,true);

        timePickerDialog.show();


    }
}
