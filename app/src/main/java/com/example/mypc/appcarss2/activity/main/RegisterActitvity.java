package com.example.mypc.appcarss2.activity.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.object.User;
import com.example.mypc.appcarss2.singleton.DataListSGT;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActitvity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUserEmail, edtPassword, edtFullName, edtEmail, edtAdress, edtPhoneNumber;
    private Button btnListCity, btnRegister;
    private ProgressDialog mProgressDialog;
    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private String email,password;
    //Dialog
    private ListView listViewCity;
    private int idListCity = -1;
    private TextView txtNameListDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addControl();
        addEvent();
    }

    private void addEvent() {

        btnListCity.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(DataSGT.getInstance().getCitySGT()!=null){
            btnListCity.setText(DataSGT.getInstance().getCitySGT().getNameCity().toString());
        }
        else btnListCity.setText("City");*/
    }

    private void addControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtUserEmail = (EditText) findViewById(R.id.edtEmailRG);
        edtPassword = (EditText) findViewById(R.id.edtPasswordRG);
        edtFullName = (EditText) findViewById(R.id.edtFullNameRG);
        edtAdress = (EditText) findViewById(R.id.edtAdressRG);
        edtEmail = (EditText) findViewById(R.id.edtEmailInforRG);
        edtPhoneNumber = (EditText) findViewById(R.id.edtPhoneRG);

        btnListCity = (Button) findViewById(R.id.btnCityRG);
        btnRegister = (Button) findViewById(R.id.btnRegisterRG);

        mProgressDialog = new ProgressDialog(this);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

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
        switch (v.getId()){
            case R.id.btnCityRG:
                btnListCityClick();
                break;
            case R.id.btnRegisterRG:
                btnRegisterClick();
                break;
        }
    }

    private void btnRegisterClick() {
        if(idListCity != -1) {
            mProgressDialog.setMessage("Registering....!");
            mProgressDialog.show();
            email = edtUserEmail.getText().toString();
            password = edtPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                addInformationUserToData();
                                Toast.makeText(RegisterActitvity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                                mProgressDialog.dismiss();
                                senLogin();
                            } else {
                                Toast.makeText(RegisterActitvity.this, "Lỗi!!", Toast.LENGTH_SHORT).show();
                                mProgressDialog.dismiss();
                            }
                        }
                    });
        }
        else {
            Toast.makeText(this, "Bạn Chưa Chọn City", Toast.LENGTH_SHORT).show();
        }
    }

    private void senLogin() {
        email = edtUserEmail.getText().toString();
        password = edtPassword.getText().toString();

        Intent i = new Intent(this,LoginActivity.class);
        /*AccountN account = new AccountN(email,password);
        DataSGT.getInstance().setAccountSGT(account);*/
        startActivity(i);
        finish();
    }

    private void addInformationUserToData() {
        email = edtUserEmail.getText().toString();
        password = edtPassword.getText().toString();

        String fullName = edtFullName.getText().toString();
        String emailIF = edtEmail.getText().toString();
        String adress = edtAdress.getText().toString();
        Integer idCity = idListCity;
        Integer phone = Integer.valueOf(edtPhoneNumber.getText().toString());
        String idAccount = mAuth.getCurrentUser().getUid().toString();

        User user = new User(idAccount,email,password,fullName,emailIF,adress,idCity,phone);
        mDatabase.child("User").child(idAccount).setValue(user);

    }

    private void btnListCityClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getCityArrayList().size();i++){
            list.add(DataListSGT.getInstance().getCityArrayList().get(i).getNameCity());
        }
        loadListItemDialog("City",list);
    }

    private void loadListItemDialog(final String nameList, ArrayList<String> arrayList){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(RegisterActitvity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_city,null);
        listViewCity = (ListView) mView.findViewById(R.id.lvDialog);
        txtNameListDialog = (TextView) mView.findViewById(R.id.txtNameListDialog);
        txtNameListDialog.setText(nameList);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listViewCity.setAdapter(adapter);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        listViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadTextButton(nameList,position);
                dialog.dismiss();
            }
        });
    }

    private void loadTextButton(String name,int position) {
        if(name.equals("City")){
            btnListCity.setText(DataListSGT.getInstance().getCityArrayList().get(position).getNameCity());
            idListCity = position;
        }
    }
}
