package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Adapter.ListDiaDiemAdapter;
import com.example.quanlysukiendat.Adapter.ListTenDiaDiemAdapter;
import com.example.quanlysukiendat.Model.diadiemModel;

import java.util.List;

public class DiaDiemQuanLyActivity extends AppCompatActivity {
    Button btnthemdiadiem, btnDSkm;
    ListTenDiaDiemAdapter listDiaDiemAdapter;
    RecyclerView recyclerView;
    ImageView btnback;
    List<diadiemModel> listDD ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diadiemactivity);
        getInit();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        btnthemdiadiem.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TimKiemDiaDiemPhuHopActivity.class);
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
        recyclerView = findViewById(R.id.rc_listdiadiem);
        btnthemdiadiem = findViewById(R.id.btn_themdiadiem);
        btnDSkm = findViewById(R.id.btn_danhsach);
        btnback = findViewById(R.id.iv_back);
        Intent intent = getIntent();
        listDD = (List<diadiemModel>) intent.getSerializableExtra("listdiadiem");
        if (listDD != null) {
            Log.d("ngu1", listDD.size() + "size");
            listDiaDiemAdapter = new ListTenDiaDiemAdapter(listDD);
            recyclerView.setAdapter(listDiaDiemAdapter);
            listDiaDiemAdapter.notifyDataSetChanged();
        } else {
            Log.d("ngu1", "listDD is null");
        }
    }

}