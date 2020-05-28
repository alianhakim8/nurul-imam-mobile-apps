package com.iai.nurulimammobileapps.activity.guru.loginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iai.nurulimammobileapps.R;
import com.iai.nurulimammobileapps.activity.guru.home.DashboardGuru;

public class Login extends AppCompatActivity {

    Button masuk;
    TextView daftar;
    EditText email, nik, password;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);

        uiReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                }
            }
        };

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        });

    }

    public void onClickLogin(View view) {
        switch (view.getId()) {
            case R.id.btnMasuk:
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                final String emailLogin = email.getText().toString().trim();
                final String nikLogin = nik.getText().toString().trim();
                final String passLogin = password.getText().toString().trim();
                final String status = radioButton.getText().toString().trim();
                if (emailLogin.isEmpty()) {
                    daftar.setEnabled(false);
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()) {
                    Toast.makeText(this, "Alamat Email Tidak Valid", Toast.LENGTH_SHORT).show();
                } else if (nikLogin.isEmpty()) {
                    daftar.setEnabled(false);
                } else if (nikLogin.length() < 10) {
                    Toast.makeText(this, "NIK Kurang", Toast.LENGTH_SHORT).show();
                } else if (passLogin.isEmpty()) {
                    daftar.setEnabled(false);
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String mUid = user.getUid();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("loginGuru");
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Masuk...");
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    reference.child(mUid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final String nik = String.valueOf(dataSnapshot.child("nik").getValue().toString());
                            final String statusLog = String.valueOf(dataSnapshot.child("status").getValue().toString());

                            if (emailLogin.equals("culinary@mail.com") && nikLogin.equals(nik) && passLogin.equals("culinary")) {
                                if (statusLog.equals(status)) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Login.this, "Anda Kepala Sekolah", Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Login.this, "Akses Ditolak", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                firebaseAuth.signInWithEmailAndPassword(emailLogin, passLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            if (nik.equals(nikLogin)) {
                                                if (status.equals(statusLog)) {
                                                    progressDialog.dismiss();
                                                    startActivity(new Intent(getApplicationContext(), DashboardGuru.class));
                                                } else {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(Login.this, "Akses Ditolak", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(Login.this, "NIK Salah", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            progressDialog.dismiss();
                                            Toast.makeText(Login.this, "Password Salah", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                break;

            case R.id.daftar:
//                Intent loginContainer = new Intent(getApplicationContext(), Register.class);
//                loginContainer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(loginContainer);
                break;
        }
    }

    public void uiReference() {
        masuk = findViewById(R.id.btnMasuk);
        daftar = findViewById(R.id.daftar);
        email = findViewById(R.id.emailLogin);
        nik = findViewById(R.id.nikLogin);
        password = findViewById(R.id.passwordLogin);
        radioGroup = findViewById(R.id.radio);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }


}
