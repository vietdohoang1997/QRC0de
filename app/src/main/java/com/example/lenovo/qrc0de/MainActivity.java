package com.example.lenovo.qrc0de;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menuFlash:
                Toast.makeText(this, "Ban da chon Flash", Toast.LENGTH_SHORT).show();
                break;
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
            case  R.id.menuLike:
                Toast.makeText(this,"Like Fanpage cua chung toi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                startActivity(intent);
                break;
            case  R.id.menuShare:
                Toast.makeText(this,"Ban da chon Share", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.email:
                Toast.makeText(this,"Ban da chon share qua email", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://gmail.com"));
                startActivity(intent1);
                break;
            case  R.id.facebook:
                Toast.makeText(this,"Ban da chon share qua facebook", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtube.com"));
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openQRcodeGenerator(){
        Intent intent = new Intent(this, QRcodeGenerator.class);
        startActivity(intent);
    }

    public void openQRcodeScanner(){
        Intent intent = new Intent(this, QRcodeScanner.class);
        startActivity(intent);
    }

}
