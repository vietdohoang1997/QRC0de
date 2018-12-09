package com.example.lenovo.qrc0de;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class QRcodeGenerator extends MainActivity{
    private EditText etInput;
    private Button btnCreate;
    private ImageView imageView;
    private Button btnSave;
    private Button btnShare;
    private Button btnReset;
    private AlertDialog dialog;
    View view;
    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.generator_main);
        setTitle("QR Generator");

        etInput = findViewById(R.id.etInput);
        btnCreate = findViewById(R.id.btnCreate);
        imageView = findViewById(R.id.imageView);
        btnSave = findViewById(R.id.btnSave);
        btnShare = findViewById(R.id.btnShare);
        btnReset = findViewById(R.id.btnReset);
        bytearrayoutputstream = new ByteArrayOutputStream();

        checkAndRequestPermissions();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etInput.getText().toString().trim();
                if (text != null) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                    Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytearrayoutputstream);
                    Random generator = new Random();
                    int n = 10000;
                    String path;
                    n = generator.nextInt(n);
                    String name = "Image-" + n + ".png";
                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + name);
                    try {
                        file.createNewFile();
                        fileoutputstream = new FileOutputStream(file);
                        fileoutputstream.write(bytearrayoutputstream.toByteArray());
                        fileoutputstream.flush();
                        fileoutputstream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
                    Toast.makeText(QRcodeGenerator.this, "Saved successfully in " + path, Toast.LENGTH_LONG).show();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                try {
                    File file = new File(getCacheDir(),"QR_image.png");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                    intent.setType("image/png");
                    startActivity(Intent.createChooser(intent, "Share image via"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    // Xin quyen trong android 6.0 tro len
    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    // Kiem tra dung luong bo nho external Storage
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }



    // Tao menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void openQRcodeGenerator() {
        Intent intent = new Intent(this, QRcodeGenerator.class);
        startActivity(intent);
    }

    public void openQRcodeScanner() {
        Intent intent = new Intent(this, QRcodeScanner.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    }

}

