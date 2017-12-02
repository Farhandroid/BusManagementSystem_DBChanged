package tanvir.busmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.Collections;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.MOdelClass.BusScheduleInfoMC;
import tanvir.busmanagementsystem.R;
import tanvir.busmanagementsystem.RecyclerAdapter.RecyclerAdapterForBusList;

public class BustListAV extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<BusINfoMC> busINfoMCArrayList;
    ArrayList<BusScheduleInfoMC> busScheduleInfoMCS;

    TextView departureLOacationTV , arrivalLocationTV , departureDateTV;

    DatabaseHelper databaseHelper;

    String departureLocation, arrivalLocation, departureDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bust_list_av);

        databaseHelper = new DatabaseHelper(this);



        Toast.makeText(this, "Enter Bus List Activity ", Toast.LENGTH_SHORT).show();

        SharedPreferences prefs = getSharedPreferences("bus_data", MODE_PRIVATE);

        departureLocation = prefs.getString("startFrom", "");
        arrivalLocation = prefs.getString("destination", "");
        departureDate = prefs.getString("departureDate", "");

        departureDateTV = (TextView) findViewById(R.id.departureDateBusList);
        departureLOacationTV = (TextView) findViewById(R.id.departureLocationBusList);
        arrivalLocationTV = (TextView) findViewById(R.id.arrivalLocationBusList);

        departureLOacationTV.setText(departureLocation);
        arrivalLocationTV.setText(arrivalLocation);
        departureDateTV.setText(departureDate);

        ///Toast.makeText(this, " departureLocation : "+departureLocation+"\narrivalLocation"+arrivalLocation+"\ndepartureDate "+departureDate , Toast.LENGTH_SHORT).show();

        //ArrayList<BusINfoMC> busINfoMCArrayList = databaseHelper.getBusListFromBusTable(startFrom, destination, departureDate);
        busINfoMCArrayList = new ArrayList<>();
        busScheduleInfoMCS = new ArrayList<>();
        ///if (busINfoMCArrayList.size() > 0) {
        ///Toast.makeText(this, "data found", Toast.LENGTH_SHORT).show();

        ///new retriveDataFromDatabase().execute();
        retriveData();




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        adapter = new RecyclerAdapterForBusList(this, busINfoMCArrayList, busScheduleInfoMCS);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);

        //} else
        ///TastyToast.makeText(getApplicationContext(), "Sorry , No trip founded ", TastyToast.LENGTH_SHORT, TastyToast.ERROR);


    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();

    }

    public void retriveData() {
        busScheduleInfoMCS = databaseHelper.getDataFromBusScheduleForRV(departureLocation, arrivalLocation, departureDate);
       for (int i = 0; i < busScheduleInfoMCS.size(); i++) {

            BusINfoMC busINfoMC = new BusINfoMC();
            String busId = busScheduleInfoMCS.get(i).getBusID();
            String busName = busScheduleInfoMCS.get(i).getBusName();

            busINfoMC = databaseHelper.getbuaTypeBusSeatSeatPriceFromDB(busId, busName);

           Toast.makeText(this, "Loopd : "+busINfoMC.getBusType(), Toast.LENGTH_SHORT).show();

            busINfoMCArrayList.add(busINfoMC);
        }

        if (busINfoMCArrayList.size() > 0)
            Toast.makeText(this, " busINfoMCArrayList data found", Toast.LENGTH_SHORT).show();
        if (busScheduleInfoMCS.size() > 0)
            Toast.makeText(this, " busScheduleInfoMCS data found", Toast.LENGTH_SHORT).show();



  /* private class retriveDataFromDatabase extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            busScheduleInfoMCS = databaseHelper.getDataFromBusScheduleForRV(departureLocation, arrivalLocation, departureDate);
            for (int i = 0; i < busScheduleInfoMCS.size(); i++) {

                BusINfoMC busINfoMC = new BusINfoMC();
                String busId = busScheduleInfoMCS.get(i).getBusID();
                String busName = busScheduleInfoMCS.get(i).getBusName();

                busINfoMC = databaseHelper.getbuaTypeBusSeatSeatPriceFromDB(busId, busName);

                busINfoMCArrayList.add(busINfoMC);


            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {


        }
    }*/
    }
}
