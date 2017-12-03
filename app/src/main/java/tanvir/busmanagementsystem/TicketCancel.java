package tanvir.busmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;

import tanvir.busmanagementsystem.Database.DatabaseHelper;

public class TicketCancel extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText tokenET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_cancel);

        databaseHelper = new DatabaseHelper(this);

        tokenET = (EditText) findViewById(R.id.input_token);
    }

    public void cancelTicket(View view) {
        String token = tokenET.getText().toString();
        if (token.length()>0)
        {
            boolean b = databaseHelper.cancelTicket(token);

            if (b)
            {
                TastyToast.makeText(getApplicationContext(), "Your ticket cancelled successfully", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                startMainActivity();
            }
            else {
                TastyToast.makeText(getApplicationContext(), "Ticket Cancell failled\nPossible reason this token doesn't exist", TastyToast.LENGTH_LONG, TastyToast.ERROR);

            }
        }
        else
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field .  .", TastyToast.LENGTH_LONG, TastyToast.WARNING);



    }

    public void onBackPressed() {
        super.onBackPressed();

        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
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
