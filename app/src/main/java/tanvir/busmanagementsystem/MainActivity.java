package tanvir.busmanagementsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import tanvir.busmanagementsystem.Database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)NavigationView navigationView;
    @BindView(R.id.toolbarlayoutinmainactivity)Toolbar toolbar;
    @BindView(R.id.selectJourneyDateET)EditText selectJourneyDate;
    @BindView(R.id.toLocationET)AutoCompleteTextView toLocation;
    @BindView(R.id.fromLocationTV)AutoCompleteTextView fromLocation;

    DatabaseHelper databaseHelper;


    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    private Menu appbar_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        databaseHelper=new DatabaseHelper(this);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open
                ,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.HOmeId).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.login:
                        Intent myIntent = new Intent(MainActivity.this, SignIn.class);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;
                    case R.id.cancelTicket:
                        Intent myIntent2 = new Intent(MainActivity.this, TicketCancel.class);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        myIntent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent2);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;

                }
                return  true;

            }
        });

        ArrayList<String> countryNameArrayList = new ArrayList<String>();
        Resources res = getResources();
        Collections.addAll(countryNameArrayList, res.getStringArray(R.array.country_array));



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countryNameArrayList);
        toLocation.setAdapter(adapter);
        fromLocation.setAdapter(adapter);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }


    public void selectDateForJourney(View view) {

        DatePickerDialog datePickerDialog;

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);



        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                selectJourneyDate.setText(dayOfMonth + "/"
                        + (month + 1) + "/" + year);

            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());

        datePickerDialog.show();
    }

    public void SearchBus(View view) {

        Intent intent = new Intent(this, BustListAV.class);

        if (fromLocation.getText().toString().length()>0 && toLocation.getText().toString().length()>0 && selectJourneyDate.getText().toString().length()>0)
        {
            SharedPreferences.Editor editor = getSharedPreferences("bus_data",MODE_PRIVATE).edit();

            editor.putString("startFrom",fromLocation.getText().toString());
            editor.putString("destination",toLocation.getText().toString());
            editor.putString("departureDate",selectJourneyDate.getText().toString());
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
