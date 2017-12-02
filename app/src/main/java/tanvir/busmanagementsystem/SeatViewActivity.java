package tanvir.busmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import tanvir.busmanagementsystem.RecyclerAdapter.RecyclerAdapterToShowSeatList;

public class SeatViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    Button button;
    TextView counterTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_view);

        toolbar = (Toolbar) findViewById(R.id.toolBar);

        button = (Button) findViewById(R.id.buyTicket);

        setSupportActionBar(toolbar);
        counterTextView = (TextView) findViewById(R.id.counterText);


        recyclerView = (RecyclerView) findViewById(R.id.seatViewRecyclerView);

        adapter = new RecyclerAdapterToShowSeatList(SeatViewActivity.this,counterTextView);

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
}
