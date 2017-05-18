package com.example.mypc.appcarss2.activity.main;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ForgotpasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnForgot;
    private EditText edtEmailForgot;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btnForgot.setOnClickListener(this);
    }

    private void addControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnForgot = (Button) findViewById(R.id.btnSenEmailForgot);
        edtEmailForgot = (EditText) findViewById(R.id.edtEmailForgot);
        mData = FirebaseDatabase.getInstance().getReference();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSenEmailForgot:
                btnForgotClick();
                break;
        }
    }

    private void btnForgotClick() {
        Query queryEmail = mData.child("User").orderByChild("userID").equalTo(edtEmailForgot.getText().toString());
        queryEmail.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue(User.class) == null) {
                    Toast.makeText(ForgotpasswordActivity.this, "Mail không tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    sendPasswordResetMail();
                }
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

    private void sendPasswordResetMail() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(edtEmailForgot.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgotpasswordActivity.this, "Bạn check mail để lấy lại password của bạn", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
