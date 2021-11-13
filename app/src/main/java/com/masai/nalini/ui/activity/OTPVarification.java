package com.masai.nalini.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import com.masai.nalini.R;

public class OTPVarification extends AppCompatActivity {
    private EditText tv1, tv2, tv3, tv4, tv5, tv6;
    private TextView  tvMobile;
    private Button btn;
    private String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvarification);
        tv1 = findViewById(R.id.box1);
        tv2 = findViewById(R.id.box2);
        tv3 = findViewById(R.id.box3);
        tv4 = findViewById(R.id.box4);
        tv5 = findViewById(R.id.box5);
        tv6 = findViewById(R.id.box6);
        btn = findViewById(R.id.btn);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.ProgressBar);
        Intent intent = getIntent();
        String Mobile_Number = intent.getStringExtra("Number");
        tvMobile = findViewById(R.id.tvMobileNo);
        tvMobile.setText(Mobile_Number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tv1.getText().toString().trim().isEmpty()&&!tv2.getText().toString().trim().isEmpty()&&
                        !tv3.getText().toString().trim().isEmpty()&&
                        !tv4.getText().toString().trim().isEmpty()&&
                        !tv5.getText().toString().trim().isEmpty()&&
                        !tv6.getText().toString().trim().isEmpty()){
                    otp = tv1.getText().toString()+ tv2.getText().toString()+ tv3.getText().toString()
                            + tv4.getText().toString()+ tv5.getText().toString()+ tv6.getText().toString();
                    String Otp_Number = intent.getStringExtra("OTPNumber");
                    if(Otp_Number!= null){
                        progressBar.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(Otp_Number, otp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                btn.setVisibility(View.VISIBLE);
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(OTPVarification.this, FragmentActivity.class);
                                    //will not come to otp verification
                                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK| intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent = new Intent(OTPVarification.this, FragmentActivity.class);
                                    //will not come to otp verification
                                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK| intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                }

                            }
                        });

                    }
                    else{
                        Toast.makeText(OTPVarification.this, "Please Check Your Internet Connection!", Toast.LENGTH_LONG).show();
                    }
                  /*  if(otp.equals(Otp_Number)){
                        Toast.makeText(OTPVarification.this, "OTP Verification SuccessFull!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(OTPVarification.this, "Wrong OTP!", Toast.LENGTH_LONG).show();
                    }*/

                }
                else{
                    Toast.makeText(OTPVarification.this, "Please Enter all the Number", Toast.LENGTH_LONG).show();
                }
            }
        });
        numberMoveNext();
    }

    private void numberMoveNext() {
        tv1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    tv2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    tv3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    tv4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    tv5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tv5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().trim().isEmpty()){
                    tv6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}
