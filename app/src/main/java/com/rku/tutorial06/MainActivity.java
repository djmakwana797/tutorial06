package com.rku.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    EditText edtEmail;
    EditText edtPass;
    Button login;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnRegister);

        preferences = getSharedPreferences("login",MODE_PRIVATE);
        editor = preferences.edit();

        String userPref = preferences.getString("username","");
        if(!userPref.equals("")){
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("Username",userPref);
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmail = findViewById(R.id.edtEmail);
                edtPass = findViewById(R.id.edtPass);

                String p = edtPass.getText().toString();
                String e = edtEmail.getText().toString();

                if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
                    Toast.makeText(MainActivity.this, "Invalid Eamil Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(e.equals("admin@gmail.com") && p.equals("admin")){
                    editor.putString("username",e);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("Username",e);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Username or Password is incorrect", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}