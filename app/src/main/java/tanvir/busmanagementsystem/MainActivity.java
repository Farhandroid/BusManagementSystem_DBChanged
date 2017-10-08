package tanvir.busmanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }






    public void clickedOnFrom(View view) {

        TastyToast.makeText(getApplicationContext(), "Clicked On from !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    public void clickedOnTo(View view) {

        TastyToast.makeText(getApplicationContext(), "Clicked On To !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }
}
