package tanvir.busmanagementsystem;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Collections;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;

public class AddBusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner busTypeSP, busSeatSP;

    EditText busIdET , busNameET , seatPriceET;

    String busType , totalBusSeat ;

    DatabaseHelper databaseHelper;

    ArrayList<String> busSeatArrayList;
    ArrayList<String> busTypeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        databaseHelper =  new DatabaseHelper(this);


        busSeatSP = (Spinner) findViewById(R.id.busSeatSP);
        busTypeSP = (Spinner) findViewById(R.id.busTypeSP);
        busIdET = (EditText) findViewById(R.id.busIdET);
        busNameET= (EditText) findViewById(R.id.busNameET);
        seatPriceET= (EditText) findViewById(R.id.busSeatPriceET);

        initializedSpinner();


    }


    public void initializedSpinner()
    {
        busTypeArrayList = new ArrayList<String>();
        Resources res = getResources();
        Collections.addAll(busTypeArrayList, res.getStringArray(R.array.bus_type));

        ArrayAdapter<String> busTypeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, busTypeArrayList);
        busTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypeSP.setAdapter(busTypeAdapter);
        busTypeSP.setOnItemSelectedListener(AddBusActivity.this);


        busSeatArrayList = new ArrayList<String>();
        Resources res2 = getResources();
        Collections.addAll(busSeatArrayList, res2.getStringArray(R.array.bus_seat_number));

        ArrayAdapter<String> busSeatAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, busSeatArrayList);
        busSeatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busSeatSP.setAdapter(busSeatAdapter);
        busSeatSP.setOnItemSelectedListener( this);



    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id ){
        Spinner spin = (Spinner)parent;
        Spinner spin2 = (Spinner)parent;

        if(spin.getId() == R.id.busSeatSP)
        {


            if (position!=0)
            {
                totalBusSeat = busSeatArrayList.get(position);
            }
            else
                totalBusSeat="";


        }
        if(spin2.getId() == R.id.busTypeSP)
        {
            if (position!=0)
            {
                busType = busTypeArrayList.get(position);
            }
            else
                busType="";


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addBusInDB(View view) {

        String busName = busNameET.getText().toString();
        String busId = busIdET.getText().toString();
        String busSeatPrice = seatPriceET.getText().toString();

        if (busName.length()>0 && busId.length()>0 && busSeatPrice.length()>0 && busType.length()>0 && totalBusSeat.length()>0)
        {
            BusINfoMC busINfoMC = new BusINfoMC(busName,busId,busType,totalBusSeat,busSeatPrice);


            Boolean aBoolean = databaseHelper.addBusInDatabase(busINfoMC);

            if (aBoolean)
            {
                TastyToast.makeText(getApplicationContext(), "Bus added successfully  ", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                startAdminActivity();
            }

            else
                TastyToast.makeText(getApplicationContext(), "Bus Can't be added\n possible reason Bus id and bus name already taken\n  ", TastyToast.LENGTH_LONG, TastyToast.WARNING);


        }
        else
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);


    }

    public void onBackPressed()
    {
        super.onBackPressed();

        startAdminActivity();

    }

    public void startAdminActivity()
    {
        Intent myIntent = new Intent(this, AdminActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }


}
