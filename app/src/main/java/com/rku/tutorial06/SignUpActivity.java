package com.rku.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

public class SignUpActivity extends AppCompatActivity {

    EditText edtFirstname, edtPassword, edtLastname, edtEmail;
    Spinner citySpinner;
    Switch switchBranch;
    CheckBox CheckBoxStatus;
    RadioGroup rdgGender;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Register = findViewById(R.id.btnRegister);
        edtFirstname = findViewById(R.id.edtfname);
        edtLastname = findViewById(R.id.edtlname);
        edtEmail = findViewById(R.id.edtemail);
        edtPassword = findViewById(R.id.edtpass);
        rdgGender = findViewById(R.id.rdgGender);
        switchBranch = findViewById(R.id.branchSwitch);
        CheckBoxStatus = findViewById(R.id.checkBoxStatus);

        citySpinner = findViewById(R.id.cityspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.city_name,R.layout.support_simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname,lname,email,pass,city,status,gender,branch;
                fname = edtFirstname.getText().toString();
                lname = edtLastname.getText().toString();
                email = edtEmail.getText().toString();
                pass = edtPassword.getText().toString();
                branch = switchBranch.isChecked()?"IT":"CE";
                status = CheckBoxStatus.isChecked()?"Active":"Inactive";
                city = citySpinner.getSelectedItem().toString();
                RadioButton radioButton = findViewById(rdgGender.getCheckedRadioButtonId());
                gender = radioButton.getText().toString();

                if(fname.isEmpty()){
                    edtFirstname.requestFocus();
                    edtFirstname.setError("Please Enter first name");
                    return;
                }
                if(lname.isEmpty()){
                    edtLastname.requestFocus();
                    edtLastname.setError("Please Enter last name");
                    return;
                }
                if(email.isEmpty()){
                    edtEmail.requestFocus();
                    edtEmail.setError("Please Enter Username");
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    edtEmail.requestFocus();
                    edtEmail.setError("Please Enter Valid Username");
                    return;
                }
                if(pass.isEmpty()){
                    edtPassword.requestFocus();
                    edtPassword.setError("Please Enter Password");
                    return;
                }

                Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
                intent.putExtra("fname", fname);
                intent.putExtra("lname", lname);
                intent.putExtra("username", email);
                intent.putExtra("pass", pass);
                intent.putExtra("city", city);
                intent.putExtra("gender", gender);
                intent.putExtra("status", status);
                intent.putExtra("branch", branch);
                startActivity(intent);
                finish();
            }
        });
    }
}