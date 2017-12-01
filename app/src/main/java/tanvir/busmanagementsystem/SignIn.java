package tanvir.busmanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tanvir.busmanagementsystem.Database.DatabaseHelper;

public class SignIn extends AppCompatActivity {


    @BindView(R.id.input_username)EditText userNameET;
    @BindView(R.id.input_password)EditText passwordET;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
    }

    public void startSignUpPage(View view) {

        Intent myIntent = new Intent(SignIn.this, SignUp.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        SignIn.this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(SignIn.this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        SignIn.this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }


    public void login(View view) {

        boolean result=databaseHelper.checkLogin(userNameET.getText().toString(),passwordET.getText().toString());

        ///Toast.makeText(this, userNameET.getText().toString()+"\n"+passwordET.getText().toString(), Toast.LENGTH_SHORT).show();

        if (result)
        {
            TastyToast.makeText(getApplicationContext(), "Welcome  "+userNameET.getText().toString(), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
           userNameET.getText().clear();
            passwordET.getText().clear();



            Intent myIntent = new Intent(SignIn.this, AdminActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            SignIn.this.startActivity(myIntent);
            overridePendingTransition(R.anim.left_in,R.anim.left_out);
            finish();
        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "Login failed. \n Please Check Usename and password", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        }




    }
}
