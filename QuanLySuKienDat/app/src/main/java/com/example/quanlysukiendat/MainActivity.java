package com.example.quanlysukiendat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button btnKM, btnDSkm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khachmoiactivity);

        getInit();
        btnKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DanhSachKhachMoiActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    private void getInit(){
        btnKM = findViewById(R.id.btn_moikhach);
        btnDSkm = findViewById(R.id.btn_danhsach);
    }

}