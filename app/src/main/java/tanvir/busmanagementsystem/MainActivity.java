package tanvir.busmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)NavigationView navigationView;
    @BindView(R.id.toolbarlayoutinmainactivity)Toolbar toolbar;


    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;

    private Menu appbar_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open
                ,R.string.drawer_close);

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.HOmeId).setChecked(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.login:
                        Intent myIntent = new Intent(MainActivity.this, SignIn.class);
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        MainActivity.this.startActivity(myIntent);
                        overridePendingTransition(R.anim.left_in,R.anim.left_out);
                        finish();
                        break;

                }
                return  true;

            }
        });
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }





    public void clickedOnFrom(View view) {

        TastyToast.makeText(getApplicationContext(), "Clicked On from !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    public void clickedOnTo(View view) {

        TastyToast.makeText(getApplicationContext(), "Clicked On To !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }
}
