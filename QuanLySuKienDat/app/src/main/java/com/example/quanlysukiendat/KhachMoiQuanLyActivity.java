package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class KhachMoiQuanLyActivity extends AppCompatActivity {
    Button btnKM, btnDSkm;
    ImageView btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khachmoiactivity);
        getInit();
        btnKM.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DanhSachKhachMoiActivity.class);
            startActivity(intent);
        });
        btnback.setOnClickListener(view -> {
            finish();
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getInit(){
        btnKM = findViewById(R.id.btn_moikhach);
        btnDSkm = findViewById(R.id.btn_danhsach);
        btnback = findViewById(R.id.iv_back);
    }

}