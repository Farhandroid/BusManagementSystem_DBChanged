package tanvir.busmanagementsystem;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tanvir.busmanagementsystem.Database.DatabaseHelper;

public class SignUp extends AppCompatActivity {

    @BindView(R.id.userNameInSignUpActivity)EditText userNameET;
    @BindView(R.id.passwordInSignUP)EditText passwordET;
    @BindView(R.id.emailInSignUP)EditText emailET;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);
    }

    public void startSignInPage(View view) {

        Intent myIntent = new Intent(SignUp.this, SignIn.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        SignUp.this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();

    }

    public void onBackPressed()
    {
        super.onBackPressed();

        Intent myIntent = new Intent(SignUp.this, MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        SignUp.this.startActivity(myIntent);
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        finish();

    }


    public void createAccount(View view) {

        boolean result = databaseHelper.insertDataInDatabase(userNameET.getText().toString(),passwordET.getText().toString(),emailET.getText().toString());

        ///Toast.makeText(this, userNameET.getText().toString()+"\n"+passwordET.getText().toString(), Toast.LENGTH_SHORT).show();

        if (result)
        {
            TastyToast.makeText(getApplicationContext(), "Sign up success", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            userNameET.getText().clear();
            passwordET.getText().clear();
            emailET.getText().clear();

        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "Sorry this username already taken .", TastyToast.LENGTH_LONG, TastyToast.ERROR);

        }

    }
}
