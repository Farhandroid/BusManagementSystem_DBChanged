package tanvir.busmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.ArrayList;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.SeatInfoMC;

public class BuyTicket extends AppCompatActivity {

    ArrayList<String> seatNumber;
    String randomNumber ;
    TextView selectedSeatTV , totalSeatPriceTV,ticketCancellTokenTV;
    EditText customerNameET , customerrEmailET , customerContactNoET  ;
    int scheduleId;
    String busSeatPrice;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        selectedSeatTV= (TextView) findViewById(R.id.selectedSeatTV);
        totalSeatPriceTV= (TextView) findViewById(R.id.totalSeatPriceTV);
        ticketCancellTokenTV= (TextView) findViewById(R.id.ticketCancelTokenTV);

        customerNameET = (EditText) findViewById(R.id.customerNameET);
        customerrEmailET = (EditText) findViewById(R.id.customerEmailET);
        customerContactNoET= (EditText) findViewById(R.id.customerMobileNoET);

        databaseHelper = new DatabaseHelper(this);

        seatNumber =  (ArrayList<String>)getIntent().getSerializableExtra("seatNumber");

        Intent mIntent = getIntent();
        scheduleId = mIntent.getIntExtra("scheduleId", 0);


        ///if (mIntent.hasExtra("seatPrice"))
            busSeatPrice = mIntent.getStringExtra("seatPrice");
        ///else
            ///Toast.makeText(this, "busSeatPrice not found", Toast.LENGTH_SHORT).show();

        ///Toast.makeText(this, "price : "+busSeatPrice, Toast.LENGTH_SHORT).show();

        int price = Integer.parseInt(busSeatPrice);
        price = seatNumber.size()*price;

        selectedSeatTV.setText(getArrayListAsString());
        totalSeatPriceTV.setText(Integer.toString(price));

        randomNumber = getRandomNumber();
        ticketCancellTokenTV.setText(randomNumber);






        ///Toast.makeText(this, "seat  number Size : "+Integer.toString(seatNumber.size()), Toast.LENGTH_SHORT).show();
    }

    public String getArrayListAsString() {
        String s = "";

        for (int i = 0; i < seatNumber.size(); i++) {
            s += seatNumber.get(i);

            if (seatNumber.size() != 1 && seatNumber.size() != (i + 1))
                s += " , ";
        }
        return s;
    }

    public void addSeatInfoInDatabase(View view) {

        String customerName = customerNameET.getText().toString();
        String customerContactNo = customerContactNoET.getText().toString();
        String customerEmail = customerrEmailET.getText().toString();

        ArrayList<SeatInfoMC> seatInfoMCS = new ArrayList<>();

        ///Toast.makeText(this, "Seat Size : "+Integer.toString(), Toast.LENGTH_SHORT).show();

        for (int i=0;i<seatNumber.size();i++)
        {

            SeatInfoMC seatInfoMC = new SeatInfoMC(scheduleId,seatNumber.get(i),customerName,customerContactNo,customerEmail,randomNumber);
            seatInfoMCS.add(seatInfoMC);
        }


        boolean b = databaseHelper.addSeatInDatabase(seatInfoMCS);

        if (b)
            Toast.makeText(this, "SckSex", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Fuck", Toast.LENGTH_SHORT).show();




    }

    public String getRandomNumber()
    {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        return formatted;
    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(this, SeatViewActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        myIntent.putExtra("seatPrice",busSeatPrice);
        myIntent.putExtra("scheduleId",scheduleId);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }
}
