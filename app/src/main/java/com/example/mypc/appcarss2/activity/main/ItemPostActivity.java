package com.example.mypc.appcarss2.activity.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.adapter.CommentItemPostAdapter;
import com.example.mypc.appcarss2.adapter.ImgITPRCVAdapter;
import com.example.mypc.appcarss2.object.Image;
import com.example.mypc.appcarss2.object.ItempostLoadKey;
import com.example.mypc.appcarss2.object.UserCommentPost;
import com.example.mypc.appcarss2.singleton.DataListSGT;
import com.example.mypc.appcarss2.singleton.DataSGT;
import com.example.mypc.appcarss2.utils.BundleExtras;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItemPostActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtTitle, txtPrice, txtCity, txtAge, txtGear, txtMaker, txtSpecie, txtInformation, txtFullName, txtAddres,txtDateTime;
    private Button btnSendComment, btnConnected;
    private EditText edtComment;
    private DatabaseReference mData;
    private ItempostLoadKey itempostLoadKey;
    private ArrayList<String> linkIMGArraylist = new ArrayList<>();
    private ArrayList<UserCommentPost> listComment = new ArrayList<>();
    private RecyclerView recyclerviewImage, recyclerViewCommnet;
    private ImgITPRCVAdapter adapterImage = null;
    private CommentItemPostAdapter adapterComment = null;
    private int phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_post);

        addControl();
        addEvent();
    }

    private void addEvent() {
        mData = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt(BundleExtras.POSITIONITEM);
        itempostLoadKey = DataListSGT.getInstance().getArrayLisItems().get(position);

        loadTXTItemPost();

        btnSendComment.setOnClickListener(this);
        btnConnected.setOnClickListener(this);
    }

    private void loadTXTItemPost() {
        txtTitle.setText(itempostLoadKey.getTitle());
        txtPrice.setText(String.valueOf(itempostLoadKey.getPrice()));
        txtInformation.setText(itempostLoadKey.getInFormation());
        txtMaker.setText(DataListSGT.getInstance().getMakerArrayList().get(itempostLoadKey.getIdMaker()).getNameMaker());
        txtGear.setText(DataListSGT.getInstance().getGearArrayList().get(itempostLoadKey.getIdGear()).getNameGear());
        txtDateTime.setText(itempostLoadKey.getDateTime());

        txtAge.setText(String.valueOf(itempostLoadKey.getIdAge()));
        txtSpecie.setText(DataListSGT.getInstance().getSpecieArrayList().get(itempostLoadKey.getIdSpecie()).getNameSpecie());
        txtCity.setText(DataListSGT.getInstance().getCityArrayList().get(itempostLoadKey.getIdCity()).getNameCity());

        loadAccount();

        adapterImage = new ImgITPRCVAdapter(this, linkIMGArraylist);
        recyclerviewImage.setAdapter(adapterImage);
        adapterComment = new CommentItemPostAdapter(this, listComment);
        recyclerViewCommnet.setAdapter(adapterComment);
        loadImageItemsPost();
        loadListComment();
    }

    private void loadListComment() {
        mData.child("CommentPost").child(itempostLoadKey.getIdKeyPost()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserCommentPost userCommentPost = dataSnapshot.getValue(UserCommentPost.class);
                listComment.add(userCommentPost);
                adapterComment.notifyDataSetChanged();
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

    private void loadImageItemsPost() {
        mData.child("ImagePost").child(itempostLoadKey.getIdKeyPost()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Image image = dataSnapshot.getValue(Image.class);
                linkIMGArraylist.add(image.getLink());
                adapterImage.notifyDataSetChanged();
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

    private void loadAccount() {
        for (int i = 0; i < DataListSGT.getInstance().getUserArrayList().size(); i++) {
            if (itempostLoadKey.getIdAccount().equals(DataListSGT.getInstance().getUserArrayList().get(i).getAccountID())) {
                txtFullName.setText(DataListSGT.getInstance().getUserArrayList().get(i).getFullName());
                txtAddres.setText(DataListSGT.getInstance().getUserArrayList().get(i).getAdress());
                phoneNumber = DataListSGT.getInstance().getUserArrayList().get(i).getPhoneNumber();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void addControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSendComment = (Button) findViewById(R.id.btnSendCommentITP);
        btnConnected = (Button) findViewById(R.id.btnConnected);
        edtComment = (EditText) findViewById(R.id.edtCommentITP);
        txtTitle = (TextView) findViewById(R.id.txtTitleITP);
        txtPrice = (TextView) findViewById(R.id.txtPriceITP);
        txtDateTime = (TextView) findViewById(R.id.txtDateTimeITP);
        txtAddres = (TextView) findViewById(R.id.txtAddresITP);
        txtFullName = (TextView) findViewById(R.id.txtNameITP);
        txtAge = (TextView) findViewById(R.id.txtAgeITP);
        txtCity = (TextView) findViewById(R.id.txtCityITP);
        txtGear = (TextView) findViewById(R.id.txtGearITP);
        txtMaker = (TextView) findViewById(R.id.txtMakerITP);
        txtSpecie = (TextView) findViewById(R.id.txtSpecieITP);
        txtInformation = (TextView) findViewById(R.id.txtInformationITP);
        recyclerviewImage = (RecyclerView) findViewById(R.id.cardListImgITP);
        recyclerViewCommnet = (RecyclerView) findViewById(R.id.listCommentUserITP);

        StaggeredGridLayoutManager linearLayoutManagerImage = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager linearLayoutManagerComment = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerviewImage.setLayoutManager(linearLayoutManagerImage);
        recyclerViewCommnet.setLayoutManager(linearLayoutManagerComment);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendCommentITP:
                btnSendCommentClick();
                break;
            case R.id.btnConnected:
                btnConnectedClick();
                break;
        }
    }

    private void btnConnectedClick() {
        Uri uri = Uri.parse("tel:0" + phoneNumber);
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(i);
    }

    private void btnSendCommentClick() {
        if(DataSGT.getInstance().getUser()!=null) {
            UserCommentPost userCommetnPost = new UserCommentPost();
            userCommetnPost.setIdAccount(DataSGT.getInstance().getUser().getAccountID());
            userCommetnPost.setFullName(DataSGT.getInstance().getUser().getFullName());
            userCommetnPost.setComment(edtComment.getText().toString());
            userCommetnPost.setTime(loadDateTime());
            mData.child("CommentPost").child(itempostLoadKey.getIdKeyPost()).push().setValue(userCommetnPost).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(ItemPostActivity.this, "Send finish ... ", Toast.LENGTH_SHORT).show();
                    edtComment.setText("");
                }
            });
        }else {
            Toast.makeText(this, "Bạn cần đăng nhập để comment vào bài này", Toast.LENGTH_SHORT).show();
        }
    }
    private String loadDateTime() {
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String time =timeFormat.format(today.getTime());
        return time;
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
}
