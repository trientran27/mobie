package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Adapter.ListKhachMoiAdapter;
import com.example.quanlysukiendat.Model.khachmoiModel;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKhachMoiActivity extends AppCompatActivity {
    ListKhachMoiAdapter listKhachMoiAdapter;
    RecyclerView recyclerView;
    ArrayList<khachmoiModel> listKM = new ArrayList<>();
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listkhachmoiactivity);
        getInit();
        listKM.add(new khachmoiModel(1,"Nguyễn Văn Nam","09873733322"));
        listKM.add(new khachmoiModel(2,"Trần thị như","0937223322"));
        listKM.add(new khachmoiModel(3,"Nguyễn Văn Nam","09873733322"));
        listKM.add(new khachmoiModel(4,"Trần thị như","0937223322"));
        listKM.add(new khachmoiModel(5,"Kim Văn Nam","09873733322"));
        listKM.add(new khachmoiModel(6,"Hà thị như","0937223322"));
        listKM.add(new khachmoiModel(7,"Nguyễn Văn Sáu","09873733322"));
        listKM.add(new khachmoiModel(8,"Trần thị Bảy","0937223322"));
        listKM.add(new khachmoiModel(9,"Nguyễn Thái Nam","09873733322"));
        listKM.add(new khachmoiModel(10,"Trần như","0937223322"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listKhachMoiAdapter = new ListKhachMoiAdapter( listKM);
        recyclerView.setAdapter(listKhachMoiAdapter);
        listKhachMoiAdapter.notifyDataSetChanged();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getInit(){
        recyclerView = findViewById(R.id.recycler_view_listkhach);
        ivBack = findViewById(R.id.iv_back);
    }

}