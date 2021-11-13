package com.masai.nalini.ui.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.masai.nalini.R;

import java.util.concurrent.TimeUnit;


public class SignInActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn);
        progressBar = findViewById(R.id.ProgressBar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(View.INVISIBLE);

                progressBar.setVisibility(View.VISIBLE);
                if (!editText.getText().toString().isEmpty()) {
                    if (editText.getText().toString().length() == 10) {
                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91"+editText.getText().toString(), 60, TimeUnit.SECONDS, SignInActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressBar.setVisibility(View.INVISIBLE);
                                        btn.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(View.INVISIBLE);
                                        btn.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(SignInActivity.this, OTPVarification.class);
                                        //intent.putExtra("OTPNumber", s);
                                        intent.putExtra("Number", editText.getText().toString());
                                        startActivity(intent);

                                        Toast.makeText(SignInActivity.this, "Please Check Internet Connection", Toast.LENGTH_LONG).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);

                                        progressBar.setVisibility(View.INVISIBLE);
                                        btn.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(SignInActivity.this, OTPVarification.class);
                                        intent.putExtra("OTPNumber", s);
                                        intent.putExtra("Number", editText.getText().toString());
                                        startActivity(intent);
                                    }
                                });


                    } else {
                        Toast.makeText(SignInActivity.this, "Please Enter all the digit", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SignInActivity.this, "Please Enter Mobile No.", Toast.LENGTH_LONG).show();
                }


            }

        });

    }
}


