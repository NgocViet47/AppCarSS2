package com.example.mypc.appcarss2.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mypc.appcarss2.R;
import com.example.mypc.appcarss2.adapter.ItemRCVAdapter;
import com.example.mypc.appcarss2.object.Age;
import com.example.mypc.appcarss2.object.City;
import com.example.mypc.appcarss2.object.Gear;
import com.example.mypc.appcarss2.object.ItemPost;
import com.example.mypc.appcarss2.object.ItempostLoadKey;
import com.example.mypc.appcarss2.object.Maker;
import com.example.mypc.appcarss2.object.Specie;
import com.example.mypc.appcarss2.object.User;
import com.example.mypc.appcarss2.singleton.DataListSGT;
import com.example.mypc.appcarss2.singleton.DataSGT;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    //Mặc định tạo navicationView.
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawer, drawerLayoutRight;
    private ListView lvRightDrawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Button btnSearchCity, btnSearchMaker, btnSearchPrice, btnLogin;
    private TextView txtFullNameAccount;
    //Firebase
    DatabaseReference mData;
    private ArrayList<ItemPost> listItemPost = new ArrayList<>();
    private ArrayList<ItempostLoadKey> listItemPostLoadKey = new ArrayList<>();
    private ArrayList<String> arrayList;
    //
    private RecyclerView recyclerView;
    private ItemRCVAdapter adapter = null;
    //
    private ListView listViewSearch;
    private static int TAG = 0;
    private static int POSITION_CITY = 4;
    private static String TOTAL_CITY = "Toàn Quốc";
    private static String TOTAL_MAKER = "Tất Cả";
    private static String KEY_IDCITY = "idCity";
    private static String KEY_MAKER = "idMaker";
    private static String NOTE_LOGIN_POST = "You can login for post.....!";
    private static String LOGIN = "LOG IN";
    private static String LOGOUT = "LOG OUT";
    private StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();


    }

    private void addEvent() {
        //Mặc định tạo navicationView.
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                if (DataSGT.getInstance().getUser() != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, NOTE_LOGIN_POST, Toast.LENGTH_SHORT).show();
                }
            }
        });

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //Add
        btnSearchCity.setOnClickListener(this);
        btnSearchMaker.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        //FireBase

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ItemRCVAdapter(this, listItemPostLoadKey);
        recyclerView.setAdapter(adapter);
        loadRecyclerview();

        //Load list
        loadListSGT();
    }

    private void loadListSGT() {
        final ArrayList<Age> ageArrayList = new ArrayList<>();
        final ArrayList<Gear> gearArrayList = new ArrayList<>();
        final ArrayList<Maker> makerArrayList = new ArrayList<>();
        final ArrayList<Specie> specieArrayList = new ArrayList<>();
        final ArrayList<User> userArrayList = new ArrayList<>();
        final ArrayList<City> cityArrayList = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mData = FirebaseDatabase.getInstance().getReference();
                mData.child("User").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        User user = dataSnapshot.getValue(User.class);
                        userArrayList.add(user);
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
                mData.child("Year").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Age age = dataSnapshot.getValue(Age.class);
                        ageArrayList.add(age);
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
                mData.child("City").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        City city = dataSnapshot.getValue(City.class);
                        cityArrayList.add(city);
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
                mData.child("Gear").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Gear gear = dataSnapshot.getValue(Gear.class);
                        gearArrayList.add(gear);
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
                mData.child("Maker").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Maker maker = dataSnapshot.getValue(Maker.class);
                        makerArrayList.add(maker);
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
                mData.child("Specie").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Specie specie = dataSnapshot.getValue(Specie.class);
                        specieArrayList.add(specie);
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
                DataListSGT.getInstance().setUserArrayList(userArrayList);
                DataListSGT.getInstance().setAgeArrayList(ageArrayList);
                DataListSGT.getInstance().setCityArrayList(cityArrayList);
                DataListSGT.getInstance().setGearArrayList(gearArrayList);
                DataListSGT.getInstance().setMakerArrayList(makerArrayList);
                DataListSGT.getInstance().setSpecieArrayList(specieArrayList);
            }

        });
        thread.start();
    }

    private void loadRecyclerview() {
        mData.child("ItemsPost").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ItemPost itemPost = dataSnapshot.getValue(ItemPost.class);
                listItemPost.add(itemPost);

                String key = dataSnapshot.getKey();
                ItempostLoadKey itempostLoadKey = new ItempostLoadKey();
                itempostLoadKey.setIdAccount(itemPost.getIdAccount());
                itempostLoadKey.setIdAge(itemPost.getIdAge());
                itempostLoadKey.setIdCity(itemPost.getIdCity());
                itempostLoadKey.setIdGear(itemPost.getIdGear());
                itempostLoadKey.setIdKeyPost(key);
                itempostLoadKey.setIdMaker(itemPost.getIdMaker());
                itempostLoadKey.setIdSpecie(itemPost.getIdSpecie());
                itempostLoadKey.setInFormation(itemPost.getInFormation());
                itempostLoadKey.setPrice(itemPost.getPrice());
                itempostLoadKey.setTitle(itemPost.getTitle());
                itempostLoadKey.setDateTime(itemPost.getDateTime());

                itempostLoadKey.setLinkFirstImage(itemPost.getLinkImageFirst());
                listItemPostLoadKey.add(itempostLoadKey);

                adapter.notifyDataSetChanged();
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
        DataListSGT.getInstance().setArrayLisItems(listItemPostLoadKey);
    }

    private void addControl() {
        //Mặc định tạo navicationView.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        lvRightDrawer = (ListView) findViewById(R.id.lvright_drawer);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //Add
        btnSearchCity = (Button) findViewById(R.id.btnSearchCity);
        btnSearchMaker = (Button) findViewById(R.id.btnSearchMaker);
        recyclerView = (RecyclerView) findViewById(R.id.cardListItemNews);
        //Button trong navication
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        btnLogin = (Button) headerview.findViewById(R.id.btnLogin);
        txtFullNameAccount = (TextView) headerview.findViewById(R.id.txtAccountName);
        //test
        mData = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DataSGT.getInstance().getUser() != null) {
            btnLogin.setText(LOGOUT);
            txtFullNameAccount.setText(DataSGT.getInstance().getUser().getFullName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            Intent intent = new Intent(this, RegisterActitvity.class);
            startActivity(intent);
        } else if (id == R.id.nav_google) {

        } else if (id == R.id.nav_facebook) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            finish();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchCity:
                btnSearchCityClick();
                break;
            case R.id.btnSearchMaker:
                btnSearchMakerClick();
                break;
            case R.id.btnLogin:
                btnLoginClick();
                break;

        }
    }

    private void btnLoginClick() {
        if (DataSGT.getInstance().getUser() != null) {
            FirebaseAuth.getInstance().signOut();
            DataSGT.getInstance().setUser(null);
            btnLogin.setText(LOGIN);
            txtFullNameAccount.setText("Name");
            drawer.closeDrawer(Gravity.LEFT);
        } else {
            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intentLogin);
            drawer.closeDrawer(Gravity.LEFT);
        }
    }

    private void btnSearchMakerClick() {
        TAG = 2;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < DataListSGT.getInstance().getMakerArrayList().size(); i++) {
            list.add(DataListSGT.getInstance().getMakerArrayList().get(i).getNameMaker());
        }
        list.add(TOTAL_MAKER);
        loadListItemDialog(list);
        openDrawerRight();
    }

    private void btnSearchCityClick() {
        TAG = 1;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < DataListSGT.getInstance().getCityArrayList().size(); i++) {
            list.add(DataListSGT.getInstance().getCityArrayList().get(i).getNameCity());
        }
        list.add(TOTAL_CITY);
        loadListItemDialog(list);
        openDrawerRight();
    }

    private void openDrawerRight() {
        drawer.openDrawer(Gravity.RIGHT);
    }

    private void closeDrawerRight() {
        drawer.closeDrawer(Gravity.RIGHT);
    }

    private void loadListItemDialog(ArrayList<String> arrayList) {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lvRightDrawer.setAdapter(adapter);
        lvRightDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadTextButton(position);
            }
        });
    }

    private ArrayList<ItempostLoadKey> arrayListItemsLoadKey = DataListSGT.getInstance().getArrayLisItems();

    private void loadTextButton(int position) {
        if (TAG == 1) {
            arrayListItemsLoadKey = new ArrayList<>();
            POSITION_CITY = position;
            if (position == DataListSGT.getInstance().getCityArrayList().size()) {
                btnSearchCity.setText(TOTAL_CITY);
                loadRecyclerview();
            } else {
                viewByCity(position, KEY_IDCITY);
                btnSearchCity.setText(DataListSGT.getInstance().getCityArrayList().get(position).getNameCity());
            }
        } else if (TAG == 2) {
            if (position == DataListSGT.getInstance().getCityArrayList().size()) {
                btnSearchMaker.setText(TOTAL_MAKER);
                if (POSITION_CITY == DataListSGT.getInstance().getCityArrayList().size()) {
                    loadRecyclerview();
                } else
                    viewByCity(POSITION_CITY, KEY_IDCITY);
            } else {
                btnSearchMaker.setText(DataListSGT.getInstance().getMakerArrayList().get(position).getNameMaker());
                if(POSITION_CITY == DataListSGT.getInstance().getCityArrayList().size()){
                    viewByMaker(position,KEY_MAKER);
                }else
                viewByMakerForCity(POSITION_CITY, position, KEY_IDCITY);
            }
        }
        closeDrawerRight();
    }

    private void viewByCity(int position, String key) {
        listItemPostLoadKey.clear();
        adapter.notifyDataSetChanged();
        mData.child("ItemsPost").orderByChild(key).equalTo(position).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ItemPost itemPost = dataSnapshot.getValue(ItemPost.class);
                listItemPost.add(itemPost);

                String key = dataSnapshot.getKey();
                ItempostLoadKey itempostLoadKey = new ItempostLoadKey();
                itempostLoadKey.setIdAccount(itemPost.getIdAccount());
                itempostLoadKey.setIdAge(itemPost.getIdAge());
                itempostLoadKey.setIdCity(itemPost.getIdCity());
                itempostLoadKey.setIdGear(itemPost.getIdGear());
                itempostLoadKey.setIdKeyPost(key);
                itempostLoadKey.setIdMaker(itemPost.getIdMaker());
                itempostLoadKey.setIdSpecie(itemPost.getIdSpecie());
                itempostLoadKey.setInFormation(itemPost.getInFormation());
                itempostLoadKey.setPrice(itemPost.getPrice());
                itempostLoadKey.setTitle(itemPost.getTitle());
                itempostLoadKey.setDateTime(itemPost.getDateTime());

                itempostLoadKey.setLinkFirstImage(itemPost.getLinkImageFirst());
                listItemPostLoadKey.add(itempostLoadKey);

                adapter.notifyDataSetChanged();
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

    private void viewByMakerForCity(int positionCity, final int positionMaker, String keyCity) {
        listItemPostLoadKey.clear();
        adapter.notifyDataSetChanged();
        mData.child("ItemsPost").orderByChild(keyCity).equalTo(positionCity).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ItemPost itemPost = dataSnapshot.getValue(ItemPost.class);
                listItemPost.add(itemPost);

                String key = dataSnapshot.getKey();
                ItempostLoadKey itempostLoadKey = new ItempostLoadKey();
                itempostLoadKey.setIdAccount(itemPost.getIdAccount());
                itempostLoadKey.setIdAge(itemPost.getIdAge());
                itempostLoadKey.setIdCity(itemPost.getIdCity());
                itempostLoadKey.setIdGear(itemPost.getIdGear());
                itempostLoadKey.setIdKeyPost(key);
                itempostLoadKey.setIdMaker(itemPost.getIdMaker());
                itempostLoadKey.setIdSpecie(itemPost.getIdSpecie());
                itempostLoadKey.setInFormation(itemPost.getInFormation());
                itempostLoadKey.setPrice(itemPost.getPrice());
                itempostLoadKey.setTitle(itemPost.getTitle());
                itempostLoadKey.setDateTime(itemPost.getDateTime());

                itempostLoadKey.setLinkFirstImage(itemPost.getLinkImageFirst());
                if (itempostLoadKey.getIdMaker() == positionMaker) {
                    listItemPostLoadKey.add(itempostLoadKey);
                }
                adapter.notifyDataSetChanged();
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
    private void viewByMaker(int positionMaker, String keyMaker) {
        listItemPostLoadKey.clear();
        adapter.notifyDataSetChanged();
        mData.child("ItemsPost").orderByChild(keyMaker).equalTo(positionMaker).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ItemPost itemPost = dataSnapshot.getValue(ItemPost.class);
                listItemPost.add(itemPost);

                String key = dataSnapshot.getKey();
                ItempostLoadKey itempostLoadKey = new ItempostLoadKey();
                itempostLoadKey.setIdAccount(itemPost.getIdAccount());
                itempostLoadKey.setIdAge(itemPost.getIdAge());
                itempostLoadKey.setIdCity(itemPost.getIdCity());
                itempostLoadKey.setIdGear(itemPost.getIdGear());
                itempostLoadKey.setIdKeyPost(key);
                itempostLoadKey.setIdMaker(itemPost.getIdMaker());
                itempostLoadKey.setIdSpecie(itemPost.getIdSpecie());
                itempostLoadKey.setInFormation(itemPost.getInFormation());
                itempostLoadKey.setPrice(itemPost.getPrice());
                itempostLoadKey.setTitle(itemPost.getTitle());
                itempostLoadKey.setDateTime(itemPost.getDateTime());

                itempostLoadKey.setLinkFirstImage(itemPost.getLinkImageFirst());
                listItemPostLoadKey.add(itempostLoadKey);
                adapter.notifyDataSetChanged();
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
}