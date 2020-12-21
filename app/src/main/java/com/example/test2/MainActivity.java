package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText uname,pword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //object initialisations
        uname = findViewById(R.id.username);
        pword = findViewById(R.id.password);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    //on login button click, the user name and password is validated

    @Override
    public void onClick(View v) {

        String username = uname.getText().toString();
        System.out.println(username);
        String pwd = pword.getText().toString();
        final Toast toastobject;

        //Checks if the login is valid. compares the login username and password and throws error if not correct or empty

            if (username.equals("user1") && pwd.equals("password1")) {
                Intent intent = new Intent(this, CountryDetails.class);
                //passing the username to the next activity.
                startActivity(intent);
                toastobject = Toast.makeText(MainActivity.this, "",
                        Toast.LENGTH_LONG);
                toastobject.cancel();
                //checks empty condition
            } else if (username.equals("") || pwd.equals("")) {
               toastobject = Toast.makeText(MainActivity.this, "UserName or Password Cannot be empty",
                        Toast.LENGTH_LONG);
                toastobject.show();

            } else {
                toastobject = Toast.makeText(MainActivity.this, "Invalid UserName or Password",
                        Toast.LENGTH_LONG);
                toastobject.show();

            }


    }
}