package tanvir.busmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.SeatInfoMC;
import tanvir.busmanagementsystem.RecyclerAdapter.RecyclerAdapterToShowSeatList;

public class SeatViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    DatabaseHelper databaseHelper;

    ArrayList<SeatInfoMC> seatInfoMCS;

    Toolbar toolbar;
    Button button;
    TextView counterTextView;
    int scheduleId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_view);

        toolbar = (Toolbar) findViewById(R.id.toolBar);

        databaseHelper= new DatabaseHelper(this);

        button = (Button) findViewById(R.id.buyTicket);

        setSupportActionBar(toolbar);
        counterTextView = (TextView) findViewById(R.id.counterText);


        Intent mIntent = getIntent();
        scheduleId = mIntent.getIntExtra("scheduleId", 0);
        String busSeatPrice = mIntent.getStringExtra("seatPrice");
        String totalbusSeat = mIntent.getStringExtra("totalSeat");

        retriveDataFromSeatTable();

        int totalSeat  = Integer.parseInt(totalbusSeat);
        totalSeat=totalSeat/4;

        ///Toast.makeText(this, "scheduleId : "+Integer.toString(scheduleId), Toast.LENGTH_SHORT).show();


        recyclerView = (RecyclerView) findViewById(R.id.seatViewRecyclerView);

        adapter = new RecyclerAdapterToShowSeatList(SeatViewActivity.this,counterTextView,button,scheduleId,busSeatPrice,seatInfoMCS,totalSeat);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);


    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(this, BustListAV.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }
    public void retriveDataFromSeatTable()
    {
        Toast.makeText(this, "retriveDataFromSeatTable", Toast.LENGTH_SHORT).show();
        seatInfoMCS=new ArrayList<>();
        seatInfoMCS = databaseHelper.getSeatUsingScheduleId(scheduleId);

        /*if (seatInfoMCS.size()<0)
            Toast.makeText(this, "seatInfoMCS not found bus seat actvt", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "seatInfoMCS found : "+Integer.toString(seatInfoMCS.size()), Toast.LENGTH_SHORT).show();*/

    }
}
