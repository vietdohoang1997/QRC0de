package com.example.lenovo.qrc0de;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(mToggle.onOptionsItemSelected(menuItem)){
                    return true;
                }
                switch (menuItem.getItemId()){
                    case  R.id.menuGenerate:
                        Toast.makeText(MainActivity.this,"Ban da chon tao ma QR", Toast.LENGTH_SHORT).show();
                        openQRcodeGenerator();
                        mDrawerLayout.closeDrawers();
                        break;
                    case  R.id.menuScanner:
                        Toast.makeText(MainActivity.this,"Ban da chon quet ma QR", Toast.LENGTH_SHORT).show();
                        openQRcodeScanner();
                        mDrawerLayout.closeDrawers();
                        break;
                    case  R.id.menuHistory:
                        Toast.makeText(MainActivity.this,"Ban da chon lich su", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.menuSettings:
                        Toast.makeText(MainActivity.this,"Ban da chon cai dat", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.menuShare:
                        Toast.makeText(MainActivity.this,"Like Fanpage cua chung toi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                        startActivity(intent);
                        break;
                    case  R.id.menuMoreApp:
                        Toast.makeText(MainActivity.this,"hihi", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    public void openQRcodeGenerator(){
        Intent intent = new Intent(this, QRcodeGenerator.class);
        startActivity(intent);
    }

    public void openQRcodeScanner(){
        Intent intent = new Intent(this, QRcodeScanner.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
//Menu phien ban cu
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //Su kien chon menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case  R.id.menuGenerate:
                Toast.makeText(this,"Ban da chon tao ma QR", Toast.LENGTH_SHORT).show();
                openQRcodeGenerator();
                break;
            case  R.id.menuScanner:
                Toast.makeText(this,"Ban da chon quet ma QR", Toast.LENGTH_SHORT).show();
                openQRcodeScanner();
                break;
            case  R.id.menuHistory:
                Toast.makeText(this,"Ban da chon lich su", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.menuSettings:
                Toast.makeText(this,"Ban da chon cai dat", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.menuShare:
                Toast.makeText(this,"Like Fanpage cua chung toi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                startActivity(intent);
                break;
            case  R.id.menuMoreApp:
                Toast.makeText(this,"hihi", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/


