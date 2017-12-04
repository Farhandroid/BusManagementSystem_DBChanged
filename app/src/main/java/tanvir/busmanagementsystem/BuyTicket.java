package tanvir.busmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import java.security.SecureRandom;
import java.util.ArrayList;

import tanvir.busmanagementsystem.Database.DatabaseHelper;
import tanvir.busmanagementsystem.MOdelClass.SeatInfoMC;

public class BuyTicket extends AppCompatActivity {

    ArrayList<String> seatNumber;
    String randomNumber;
    TextView selectedSeatTV, totalSeatPriceTV, ticketCancellTokenTV;
    EditText customerNameET, customerrEmailET, customerContactNoET;
    int scheduleId;
    String busSeatPrice;
    int totalSeat;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);

        selectedSeatTV = (TextView) findViewById(R.id.selectedSeatTV);
        totalSeatPriceTV = (TextView) findViewById(R.id.totalSeatPriceTV);
        ticketCancellTokenTV = (TextView) findViewById(R.id.ticketCancelTokenTV);

        customerNameET = (EditText) findViewById(R.id.customerNameET);
        customerrEmailET = (EditText) findViewById(R.id.customerEmailET);
        customerContactNoET = (EditText) findViewById(R.id.customerMobileNoET);

        databaseHelper = new DatabaseHelper(this);

        seatNumber = (ArrayList<String>) getIntent().getSerializableExtra("seatNumber");

        Intent mIntent = getIntent();
        scheduleId = mIntent.getIntExtra("scheduleId", 0);
        totalSeat = mIntent.getIntExtra("totalSeat", 0);


        ///if (mIntent.hasExtra("seatPrice"))
        busSeatPrice = mIntent.getStringExtra("seatPrice");
        ///else
        ///Toast.makeText(this, "busSeatPrice not found", Toast.LENGTH_SHORT).show();

        ///Toast.makeText(this, "price : "+busSeatPrice, Toast.LENGTH_SHORT).show();

        int price = Integer.parseInt(busSeatPrice);
        price = seatNumber.size() * price;

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

        if (customerName.length() > 0 && customerContactNo.length() > 0 && customerEmail.length() > 0) {

             if (customerEmail.contains("@") && customerEmail.contains("."))
            {
                for (int i = 0; i < seatNumber.size(); i++) {
                    SeatInfoMC seatInfoMC = new SeatInfoMC(scheduleId, seatNumber.get(i), customerName, customerContactNo, customerEmail, randomNumber);
                    seatInfoMCS.add(seatInfoMC);
                }


                boolean b = databaseHelper.addSeatInDatabase(seatInfoMCS);

                if (b) {
                    TastyToast.makeText(getApplicationContext(), "Your ticket purchased successfully ", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    startMainActivity();
                } else
                    TastyToast.makeText(getApplicationContext(), "Ticket Purchased Failed ", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);

            }
             else
             {
                 TastyToast.makeText(getApplicationContext(), "Please insert valid email .", TastyToast.LENGTH_LONG, TastyToast.WARNING);

             }


        } else {
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }

        ///Toast.makeText(this, "Seat Size : "+Integer.toString(), Toast.LENGTH_SHORT).show();


    }

    public String getRandomNumber() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        return formatted;
    }

    public void onBackPressed() {
        super.onBackPressed();

        totalSeat = totalSeat * 4;


        Intent myIntent = new Intent(this, SeatViewActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        myIntent.putExtra("seatPrice", busSeatPrice);
        myIntent.putExtra("scheduleId", scheduleId);
        myIntent.putExtra("totalSeat", Integer.toString(totalSeat));
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();

    }

    public void startMainActivity()
    {
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }
}
