package tanvir.busmanagementsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
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

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.Database.TableAttribute;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;

public class AddBusScheDuleActivity extends AppCompatActivity {

    EditText departureDateET, departureTimeET, arrivalDateET, arrivalTimeET;
    AutoCompleteTextView busIdET, busNameET, departureLocationET, arrivalLocationET;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_sche_dule);

        databaseHelper = new DatabaseHelper(this);

        departureDateET = (EditText) findViewById(R.id.departureDateET);
        departureTimeET = (EditText) findViewById(R.id.departureTimeET);
        arrivalDateET = (EditText) findViewById(R.id.arrivalDateET);
        arrivalTimeET = (EditText) findViewById(R.id.arrivalTimeET);
        busNameET = (AutoCompleteTextView) findViewById(R.id.busNameETSQ);
        busIdET = (AutoCompleteTextView) findViewById(R.id.busIdETSQ);
        departureLocationET = (AutoCompleteTextView) findViewById(R.id.departureLocationSC);
        arrivalLocationET = (AutoCompleteTextView) findViewById(R.id.arrivalLocationSC);

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


    public void setAutoComplete() {

        ArrayList<String> busNameArrayList = new ArrayList<>();
        ArrayList<String> busIdArrayList = new ArrayList<>();

        Cursor cursor = databaseHelper.getBusNameId();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                busNameArrayList.add(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_NAME)));
                busIdArrayList.add(cursor.getString(cursor.getColumnIndex(TableAttribute.COL_BUS_ID)));

                cursor.moveToNext();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busNameArrayList);
        busNameET.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, busIdArrayList);
        busIdET.setAdapter(adapter2);

        ArrayList<String> countryNameArrayList = new ArrayList<String>();
        Resources res = getResources();
        Collections.addAll(countryNameArrayList, res.getStringArray(R.array.country_array));


        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryNameArrayList);
        departureLocationET.setAdapter(adapter3);
        arrivalLocationET.setAdapter(adapter3);


    }

    public void addBusSchedule(View view) {

        String busId = busIdET.getText().toString();
        String busName = busNameET.getText().toString();
        String departureDate = departureDateET.getText().toString();
        String departureTime = departureTimeET.getText().toString();
        String arrivalDate = arrivalDateET.getText().toString();
        String arrivalTime = arrivalTimeET.getText().toString();
        String departureLocation = departureLocationET.getText().toString();
        String arrivalLocation = arrivalLocationET.getText().toString();

        boolean res = getArrivalTime(busId,busName,departureTime);




        if (busId.length() > 0 && busName.length() > 0 && departureDate.length() > 0 && departureTime.length() > 0 && arrivalDate.length() > 0 && arrivalTime.length() > 0 && departureLocation.length() > 0 && arrivalLocation.length() > 0) {
            if (new String(departureDate).equals(arrivalDate) && new String(departureTime).equals(arrivalTime))

                TastyToast.makeText(getApplicationContext(), "Bus Can't be Scheduled\n Possible reason : departure time and arrival time can't be same ", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            else if (res==false)
            {
                TastyToast.makeText(getApplicationContext(), "Bus Can't be Scheduled\n Possible reason : This time slot is already used ", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }
            else {
                boolean result = compareTime(departureTime, arrivalTime,departureDate,arrivalDate);


                if (result) {
                    BusScheduleInfoMC busScheduleInfoMC = new BusScheduleInfoMC(busId, busName, departureLocation, arrivalLocation, departureDate, departureTime, arrivalDate, arrivalTime);

                    ///Toast.makeText(this, busName+"\n"+busId+"\n"+departureDate+"\n"+departureTime+"\n"+arrivalDate+"\n"+arrivalTime+"\n", Toast.LENGTH_LONG).show();

                    Boolean aBoolean = databaseHelper.addBusScheduleInDatabase(busScheduleInfoMC);

                    if (aBoolean) {
                        TastyToast.makeText(getApplicationContext(), "Bus Scheduled successfully  ", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                        startAdminActivity();
                    } else
                        TastyToast.makeText(getApplicationContext(), "Bus Can't be Scheduled\n possible reason :  This bus in't added\n Please add the bus first then try again  ", TastyToast.LENGTH_LONG, TastyToast.WARNING);


                } else {
                    TastyToast.makeText(getApplicationContext(), "Arril time can't be lower than departure time", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                }

            }
        } else {
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }


    }


    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(this, AdminActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
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


                if (hourOfDay < 10)
                    hour = "0" + hour;

                if (minute < 10)
                    mint = "0" + mint;

                departureTimeET.setText(hour + " : " + mint + " ");


            }
        }, hour, minute, true);

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


                if (hourOfDay < 10)
                    hour = "0" + hour;

                if (minute < 10)
                    mint = "0" + mint;

                arrivalTimeET.setText(hour + " : " + mint + " ");


            }
        }, hour, minute, true);

        timePickerDialog.show();


    }

    public void startAdminActivity() {
        Intent myIntent = new Intent(this, AdminActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();

    }

    public boolean compareTime(String departureTime, String arrivalTime,String departureDate,String arrivalDate) {

        boolean result = true;

        String departureTimeHour, departureTimeMinut, arrivalTimeHour, arrivalTimeMinute;
        String array1[] = departureTime.split(":");

        departureTimeHour = array1[0];
        departureTimeMinut = array1[1];

        String array2[] = arrivalTime.split(":");

        arrivalTimeHour = array2[0];
        arrivalTimeMinute = array2[1];

        ///Toast.makeText(this, "deHour : "+departureTimeHour+"\ndemnt :   "+departureTimeMinut+"\nArhour :   "+arrivalTimeHour+"\nARmnt :   "+arrivalTimeMinute, Toast.LENGTH_LONG).show();

        int departureHourInt, departureMinuteInt, arrivalHourInt, arrivalMinuteInt;

        departureHourInt = Integer.parseInt(departureTimeHour.trim());


        ///Toast.makeText(this, "hour int : "+Integer.toString(departureHourInt), Toast.LENGTH_SHORT).show();
        departureMinuteInt = Integer.parseInt(departureTimeMinut.trim());
        arrivalHourInt = Integer.parseInt(arrivalTimeHour.trim());
        arrivalMinuteInt = Integer.parseInt(arrivalTimeMinute.trim());

        if (arrivalHourInt < departureHourInt)
            result = false;
        else if (new String(departureDate).equals(arrivalDate) && arrivalMinuteInt < departureMinuteInt)
            result = false;


        return result;
    }

    public boolean getArrivalTime(String busId , String busName,String departureTime)
    {
        ArrayList<String> busArrivalTimeInfo = databaseHelper.getArrivalTimeFromcheduleTable(busId,busName);

        ArrayList<Integer> busArrivalTimeInfoINt = new ArrayList<>();

        boolean res = true;

        if (busArrivalTimeInfo.size()>0)
        {


            for (int i=0;i<busArrivalTimeInfo.size();i++)
            {
                String s = busArrivalTimeInfo.get(i).replace(":","");
                s=s.replaceAll("\\s+","");

                busArrivalTimeInfoINt.add(Integer.parseInt(s));

                ///Toast.makeText(this, "val : "+busArrivalTimeInfo.get(i), Toast.LENGTH_SHORT).show();
            }
        }

        if (busArrivalTimeInfo.size()>0)
        {
            String s = departureTime.replace(":","");
            s=s.replaceAll("\\s+","");
            int arvl = Integer.parseInt(s);


            for (int i=0;i<busArrivalTimeInfoINt.size();i++)
            {
                if (arvl<=busArrivalTimeInfoINt.get(i))
                {
                    res=false;
                }

            }


        }
        return res;



    }
}
