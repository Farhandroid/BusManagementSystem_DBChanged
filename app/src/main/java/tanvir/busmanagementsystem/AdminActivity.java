package tanvir.busmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void startAddBusActivity(View view) {

        Intent myIntent = new Intent(this, AddBusActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
      this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();

    }

    public void startAddBusScheduleActivity(View view) {

        Intent myIntent = new Intent(this, AddBusScheDuleActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();

    }
}
