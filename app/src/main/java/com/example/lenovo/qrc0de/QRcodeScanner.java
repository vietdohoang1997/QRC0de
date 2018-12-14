package com.example.lenovo.qrc0de;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRcodeScanner  extends AppCompatActivity {
    TextView ketqua;
    Context context;
    private Button btnScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_main);
        setTitle("QR Scanner");
        init();
        btnScan = findViewById(R.id.btnScan);
        
        btnScan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                openQRcodeScanner();}
        });

    }

    public void init() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Wait.......");
        integrator.setCameraId(0);
        // beep khi scan qr thành công
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultcode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultcode, intent);
        if (result != null) {
            String contents = result.getContents();
            ketqua = (TextView) findViewById(R.id.txtKetqua);
            ketqua.setText(contents);
            // lấy hiệu ứng rung khi scan thành công.
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // SET RUNG 400 MILLISECONDS
            v.vibrate(400);
        }
    }

    // Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //
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

    public void openQRcodeGenerator(){
        Intent intent = new Intent(this, QRcodeGenerator.class);
        startActivity(intent);
    }

    public void openQRcodeScanner(){
        Intent intent = new Intent(this, QRcodeScanner.class);
        startActivity(intent);
    }
}