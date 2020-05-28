package com.iai.nurulimammobileapps.activity.guru.loginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.iai.nurulimammobileapps.R;
import com.iai.nurulimammobileapps.activity.model.Guru;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText nama, email, nik, tmt, password;
    private Button daftar;
    private TextView masuk;
    private FirebaseFirestore firestore;
    private CollectionReference reference;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RadioButton radioButton;
    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guru);

        // ui reference
        uiReference();
        // Initialize Firebase


    }

    public void registerOnClickButton(View view) {
        switch (view.getId()) {
            case R.id.masuk:
                Intent loginContainer = new Intent(getApplicationContext(), Login.class);
                loginContainer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginContainer);
                finish();
                break;
            case R.id.btnDaftar:
                startRegister();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void uiReference() {
        nama = findViewById(R.id.namaRegister);
        email = findViewById(R.id.emailLogin);
        nik = findViewById(R.id.nikRegister);
        password = findViewById(R.id.passwordRegister);
        daftar = findViewById(R.id.btnDaftar);
        masuk = findViewById(R.id.masuk);
        tmt = findViewById(R.id.tmtRegister);
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("loginGuru");
        radioGroup = findViewById(R.id.radio);
    }

    public void startRegister() {
        // get selected from radio Group
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedId);

        final String emailRegister = email.getText().toString().trim();
        final String passwordRegister = password.getText().toString().trim();
        final String nikRegister = nik.getText().toString().trim();
        final String namaRegister = nama.getText().toString().trim();
        final String tmtRegister = tmt.getText().toString().trim();
        final String status = radioButton.getText().toString().trim();

        if (emailRegister.isEmpty()) {
            Toast.makeText(this, "Masukan Email", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailRegister).matches()) {
            Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
        } else if (passwordRegister.isEmpty()) {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_SHORT).show();
        } else if (passwordRegister.length() < 6) {
            Toast.makeText(this, "Password Terlalu Lemah", Toast.LENGTH_SHORT).show();
        } else if (nikRegister.length() < 10) {
            Toast.makeText(this, "NIK Tidak Valid", Toast.LENGTH_SHORT).show();
        } else if (nikRegister.isEmpty() || tmtRegister.isEmpty()) {
            Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_SHORT).show();
        } else if (status.isEmpty()) {
            Toast.makeText(this, "Pilih Status", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Mendaftar...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(emailRegister, passwordRegister).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Guru guru = new Guru(
                                emailRegister, namaRegister, nikRegister, tmtRegister, passwordRegister, status
                        );
                        FirebaseDatabase.getInstance().getReference("loginGuru")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(guru).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                Toast.makeText(Register.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Register.this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "Email Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    public boolean cekEmail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.dismiss();
        mAuth.fetchSignInMethodsForEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean cek = task.getResult().getSignInMethods().isEmpty();
                if (cek) {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Register.this, "Email Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
        progressDialog.dismiss();
        return false;

    }


}
