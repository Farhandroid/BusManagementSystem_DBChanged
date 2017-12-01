package tanvir.busmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.BusINfoMC;
import tanvir.busmanagementsystem.R;
import tanvir.busmanagementsystem.RecyclerAdapter.RecyclerAdapterForBusList;

public class BustListAV extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bust_list_av);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences prefs = getSharedPreferences("bus_data", MODE_PRIVATE);

        String startFrom = prefs.getString("startFrom","");
        String destination = prefs.getString("destination","");
        String departureDate = prefs.getString("departureDate","");

        //ArrayList<BusINfoMC> busINfoMCArrayList = databaseHelper.getBusListFromBusTable(startFrom, destination, departureDate);
        ArrayList<BusINfoMC> busINfoMCArrayList = new ArrayList<>();
        ///if (busINfoMCArrayList.size() > 0) {
            ///Toast.makeText(this, "data found", Toast.LENGTH_SHORT).show();

            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

            adapter = new RecyclerAdapterForBusList(this,busINfoMCArrayList);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            recyclerView.setAdapter(adapter);

        //} else
            ///TastyToast.makeText(getApplicationContext(), "Sorry , No trip founded ", TastyToast.LENGTH_SHORT, TastyToast.ERROR);



    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }
}
