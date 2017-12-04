package tanvir.busmanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tanvir.busmanagementsystem.Database.DatabaseHelper;

public class SignIn extends AppCompatActivity {


    @BindView(R.id.input_username)EditText userNameET;
    @BindView(R.id.input_password)EditText passwordET;

    DatabaseHelper databaseHelper;
    View dialogView;
    AlertDialog alertDialog;

    EditText forgotPasswordUserNameET,answerForgotPasswordET;

    TextView questionForgotPassword;

    String userNameForgotPassword,answerForgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ButterKnife.bind(this);
        databaseHelper = new DatabaseHelper(this);

        SharedPreferences prefs = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String logged = prefs.getString("isLogged?", "");

        if (logged.contains("yes"))
        {
            startAdminActivity();
        }

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

        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        if (userName.length()>0 && password.length()>0)
        {
            boolean result=databaseHelper.checkLogin(userNameET.getText().toString(),passwordET.getText().toString());

            ///Toast.makeText(this, userNameET.getText().toString()+"\n"+passwordET.getText().toString(), Toast.LENGTH_SHORT).show();

            if (result)
            {
                userNameET.getText().clear();
                passwordET.getText().clear();
                TastyToast.makeText(getApplicationContext(), "Welcome  "+userNameET.getText().toString(), TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);


                SharedPreferences.Editor editor = getSharedPreferences("loginInfo", MODE_PRIVATE).edit();
                editor.putString("isLogged?", "yes");
                editor.apply();



                startAdminActivity();
            }
            else
            {
                TastyToast.makeText(getApplicationContext(), "Login failed. \n Please Check Usename and password", TastyToast.LENGTH_LONG, TastyToast.ERROR);

            }
        }
        else
        {
            TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
        }



    }

    public void startAdminActivity()
    {
        Intent myIntent = new Intent(SignIn.this, AdminActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        SignIn.this.startActivity(myIntent);
        overridePendingTransition(R.anim.left_in,R.anim.left_out);
        finish();
    }

    public void userNameForForgotPassword(View view) {

       userNameForgotPassword=forgotPasswordUserNameET.getText().toString();




       if (userNameForgotPassword.length()>0)
       {
           String question = databaseHelper.getQuestion(userNameForgotPassword);


           if (question.length()>0)
           {
               alertDialog.dismiss();

               AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
               LayoutInflater inflater = this.getLayoutInflater();
               dialogView = inflater.inflate(R.layout.question_forgot_password, null);

               questionForgotPassword=dialogView.findViewById(R.id.questionInForgotPassword);
               answerForgotPasswordET=dialogView.findViewById(R.id.answerInForgotPassword);

               questionForgotPassword.setText(question);

               dialogBuilder.setView(dialogView);
               alertDialog = dialogBuilder.create();
               alertDialog.setCancelable(true);
               alertDialog.show();

           }
           else
           {
               TastyToast.makeText(getApplicationContext(), "User Name not found  ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
           }



       }
       else
       {
           TastyToast.makeText(getApplicationContext(), "PLease fill up all field ", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
       }

    }

    public void clickOnForgotPassword(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.insert_username_for_forgetpassword, null);


        forgotPasswordUserNameET=dialogView.findViewById(R.id.userNameInForgotPassword);


        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }


    public void findPassword(View view) {

        answerForgotPassword=answerForgotPasswordET.getText().toString();


        String password =databaseHelper.getPassword(userNameForgotPassword,answerForgotPassword);

       /// Toast.makeText(this, "userName : "+userNameForgotPassword+"\nanswer"+answerForgotPassword, Toast.LENGTH_SHORT).show();

        if (password.length()>0)
        {
            alertDialog.dismiss();
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.password_show, null);


            TextView passwoedView = dialogView.findViewById(R.id.showPassword);

            passwoedView.setText(password);


            dialogBuilder.setView(dialogView);
            alertDialog = dialogBuilder.create();
            alertDialog.setCancelable(true);
            alertDialog.show();
            ///Toast.makeText(this, "password "+password, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "password not found ", Toast.LENGTH_SHORT).show();
        }




    }

    public void passwordFounded(View view) {
        alertDialog.dismiss();
    }
}
