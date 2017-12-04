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
    EditText questionET , secretAnswerET ;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);

        questionET = (EditText) findViewById(R.id.questionInSignUP);
        secretAnswerET= (EditText) findViewById(R.id.answerInSignUP);
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

        boolean result=false;

        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();
        String email = emailET.getText().toString();
        String question = questionET.getText().toString();
        String secretAnswer = questionET.getText().toString();

        if (userName.length()>0 && password.length()>0 && email.length()>0 && question.length()>0 && secretAnswer.length()>0)
        {
            if (email.contains("@") && email.contains("."))
            {
                result = databaseHelper.insertSignUpDataInDatabase(userName,password,email,question,secretAnswer);


                if (result)
                {
                    TastyToast.makeText(getApplicationContext(), "Sign up success", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                    userNameET.getText().clear();
                    passwordET.getText().clear();
                    emailET.getText().clear();

                    Intent myIntent = new Intent(SignUp.this, SignIn.class);
                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    SignUp.this.startActivity(myIntent);
                    overridePendingTransition(R.anim.right_in,R.anim.right_out);
                    finish();

                }
                else
                {
                    TastyToast.makeText(getApplicationContext(), "Sorry this username already taken .", TastyToast.LENGTH_LONG, TastyToast.ERROR);

                }
            }
            else
            {
                TastyToast.makeText(getApplicationContext(), "Please insert valid email .", TastyToast.LENGTH_LONG, TastyToast.WARNING);

            }


        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field .  .", TastyToast.LENGTH_LONG, TastyToast.WARNING);

        }



        ///Toast.makeText(this, userNameET.getText().toString()+"\n"+passwordET.getText().toString(), Toast.LENGTH_SHORT).show();


    }
}
