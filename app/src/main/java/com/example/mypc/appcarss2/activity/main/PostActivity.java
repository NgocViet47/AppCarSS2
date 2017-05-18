package com.example.mypc.appcarss2.activity.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.example.mypc.appcarss2.adapter.ImgRCVAdapter;
import com.example.mypc.appcarss2.object.Image;
import com.example.mypc.appcarss2.object.ImgPost;
import com.example.mypc.appcarss2.object.ItemPost;
import com.example.mypc.appcarss2.singleton.DataListSGT;
import com.example.mypc.appcarss2.singleton.DataSGT;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListCity, btnListMaker, btnListGear, btnListSpice, btnListAge, btnPostNew, btnCamera, btnGallery;
    private EditText edtTitle, edtPrice, editDecription;
    private TextView txtNameListDialog;
    private ListView listViewCity;
    private DatabaseReference mData;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference = storage.getReference();
    private static final int REQUEST_CODE_IMAGE = 2;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<String> listImg = new ArrayList<>();
    private ArrayList<ImgPost> listItemImg = new ArrayList<>();
    private String keyItemPost = "AA";

    private int idCity,idMaker,idAge,idGear,idSpecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        btnListMaker.setOnClickListener(this);
        btnListCity.setOnClickListener(this);
        btnListAge.setOnClickListener(this);
        btnListSpice.setOnClickListener(this);
        btnListGear.setOnClickListener(this);
        btnPostNew.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }

    private void addControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //firebase
        mData = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        btnListCity = (Button) findViewById(R.id.btnAdress);
        btnListMaker = (Button) findViewById(R.id.btnMaker);
        btnListGear = (Button) findViewById(R.id.btnGear);
        btnListSpice = (Button) findViewById(R.id.btnSpecies);
        btnListAge = (Button) findViewById(R.id.btnAge);
        btnPostNew = (Button) findViewById(R.id.btnPost);
        btnCamera = (Button) findViewById(R.id.btnCameraPost);
        btnGallery = (Button) findViewById(R.id.btnGalleryPost);

        editDecription = (EditText) findViewById(R.id.edtDecription);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtPrice = (EditText) findViewById(R.id.edtPrice);

        recyclerView = (RecyclerView) findViewById(R.id.cardListImg);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMaker:
                btnListMakerClick();
                break;
            case R.id.btnAdress:
                btnListCityClick();
                break;
            case R.id.btnAge:
                btnListAgeClick();
                break;
            case R.id.btnSpecies:
                btnListSpiceClick();
                break;
            case R.id.btnGear:
                btnListGearClick();
                break;
            case R.id.btnPost:
                btnPostClick();
                break;
            case R.id.btnCameraPost:
                btnCameraClick();
                break;
            case R.id.btnGalleryPost:
                btnGalleryClick();
                break;
        }
    }

    private void btnGalleryClick() {
        listImg.clear();
        FilePickerBuilder.getInstance().setMaxCount(6).
                setSelectedFiles(listImg).
                setActivityTheme(R.style.AppTheme).
                pickPhoto(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    listImg = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    ImgPost img;
                    listItemImg = new ArrayList<>();
                    for (String path : listImg) {
                        img = new ImgPost();
                        img.setUri(Uri.fromFile(new File(path)));
                        listItemImg.add(img);
                    }
                    recyclerView.setAdapter(new ImgRCVAdapter(this, listItemImg));
                }
                break;
        }
    }

    private void btnCameraClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    private void btnPostClick() {
        UploadNewPost();
    }

    private void UploadNewPost() {
        for(int i = 0;i<listItemImg.size();i++){
            if(listItemImg.get(i).isChecked()==true){
                StorageReference filepath = storageReference.child("photofirst")
                        .child(listItemImg.get(i).getUri().getLastPathSegment());
                filepath.putFile(listItemImg.get(i).getUri())
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri uri = taskSnapshot.getDownloadUrl();
                        String link = String.valueOf(uri);
                        ItemPost itemPost = new ItemPost(edtTitle.getText().toString()
                                , Integer.valueOf(edtPrice.getText().toString())
                                , idCity
                                , idMaker
                                , idSpecie
                                , idGear
                                , DataSGT.getInstance().getUser().getAccountID()
                                , link
                                , idAge
                                , editDecription.getText().toString()
                                ,loadDateTime());
                        mData.child("ItemsPost").push().setValue(itemPost, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                keyItemPost = databaseReference.getKey();
                                UploadImgNewPost();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PostActivity.this, "Lưu Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
            //TODO Toast.makeText(this, "Bạn Chưa chọn hình đại diện cho tin", Toast.LENGTH_SHORT).show();
        }
    }
    private String loadDateTime() {
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
        String time =timeFormat.format(today.getTime());
        return time;
    }

    private void UploadImgNewPost() {
        for (int i = 0; i < listItemImg.size(); i++) {
            Uri uriImg = listItemImg.get(i).getUri();
            StorageReference filepath = storageReference.child("photo").child(keyItemPost).child(uriImg.getLastPathSegment());
            filepath.putFile(uriImg).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    String a = String.valueOf(downloadUrl);
                    Image image = new Image();
                    image.setLink(a);
                    mData.child("ImagePost").child(keyItemPost).push().setValue(image);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(PostActivity.this, "Save Error ...", Toast.LENGTH_SHORT).show();
                }
            });
        }
        Toast.makeText(this, "Post finish ... ", Toast.LENGTH_SHORT).show();
    }

    private void btnListGearClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getGearArrayList().size();i++){
            list.add(String.valueOf(DataListSGT.getInstance().getGearArrayList().get(i).getNameGear()));
        }
        loadListItemDialog("Gear",list);
    }

    private void btnListSpiceClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getSpecieArrayList().size();i++){
            list.add(String.valueOf(DataListSGT.getInstance().getSpecieArrayList().get(i).getNameSpecie()));
        }
        loadListItemDialog("Specie",list);
    }

    private void btnListAgeClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getAgeArrayList().size();i++){
            list.add(String.valueOf(DataListSGT.getInstance().getAgeArrayList().get(i).getYear()));
        }
        loadListItemDialog("Age",list);
    }

    private void btnListCityClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getCityArrayList().size();i++){
            list.add(DataListSGT.getInstance().getCityArrayList().get(i).getNameCity());
        }
        loadListItemDialog("City",list);
    }

    private void btnListMakerClick() {
        ArrayList<String> list = new ArrayList<>();
        for (int i=0;i< DataListSGT.getInstance().getMakerArrayList().size();i++){
            list.add(DataListSGT.getInstance().getMakerArrayList().get(i).getNameMaker());
        }
        loadListItemDialog("Maker",list);
    }
    private void loadListItemDialog(final String nameList, ArrayList<String> arrayList){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(PostActivity.this);
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
            idCity = position;
        }else if(name.equals("Maker")){
            btnListMaker.setText(DataListSGT.getInstance().getMakerArrayList().get(position).getNameMaker());
            idMaker = position;
        }else if(name.equals("Gear")){
            btnListGear.setText(DataListSGT.getInstance().getGearArrayList().get(position).getNameGear());
            idGear = position;
        }else if(name.equals("Age")){
            btnListAge.setText(String.valueOf(DataListSGT.getInstance().getAgeArrayList().get(position).getYear()));
            idAge = DataListSGT.getInstance().getAgeArrayList().get(position).getYear();
        }else if(name.equals("Specie")){
            btnListSpice.setText(DataListSGT.getInstance().getSpecieArrayList().get(position).getNameSpecie());
            idSpecie = position;
        }
    }
}
