package com.example.mypc.appcarss2.activity.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.object.User;
import com.example.mypc.appcarss2.singleton.DataSGT;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnForgot,btnLogin;
    private EditText edtEmail,edtPassword;
    private ProgressDialog mProgressDialog;

    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mData;
    private String idAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        addControl();
        addEvent();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvent() {
        btnForgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        //Test
        edtEmail.setText("a@gmail.com");
        edtPassword.setText("ngocviet");
    }

    private void addControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnForgot = (Button) findViewById(R.id.btnForgotPasswordLI);
        btnLogin = (Button) findViewById(R.id.btnLogInLI);
        edtEmail = (EditText) findViewById(R.id.edtEmailLI);
        edtPassword = (EditText) findViewById(R.id.edtPasswordLI);

        mProgressDialog = new ProgressDialog(this);

        //firebase
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnForgotPasswordLI:
                btnForgotClick();
                break;
            case R.id.btnLogInLI:
                btnLoginClick();
                break;
        }
    }

    private void btnLoginClick() {
        mProgressDialog.setMessage("Being logged in ... ");
        mProgressDialog.show();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            idAccount = mAuth.getCurrentUser().getUid();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                            addUserToSGT();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    }
                });

    }

    private void addUserToSGT() {
        mData = FirebaseDatabase.getInstance().getReference();

        Query query = mData.child("User").orderByChild("accountID").equalTo(idAccount);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                DataSGT.getInstance().setUser(user);
                Toast.makeText(LoginActivity.this, "Lưu thông tin account finish!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void btnForgotClick() {
        Intent intentForgotPassword = new Intent(LoginActivity.this,ForgotpasswordActivity.class);
        startActivity(intentForgotPassword);
    }
}
