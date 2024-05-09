package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Adapter.ListDiaDiemAdapter;
import com.example.quanlysukiendat.Adapter.ListLichTrinhAdapter;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class danhsachdiadiemActivity extends AppCompatActivity {
    ListDiaDiemAdapter listDiaDiemAdapter;
    RecyclerView recyclerView;
    List<diadiemModel> listDD ;
    ImageView ivBack;
    Button btn_sort;
    boolean isPriceAscending = true;
    private AutoCompleteTextView autoSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listdiadiemactivity);
        getInit();
//        listDD.add(new diadiemModel(1,"Mường Thanh Garden ",2000000));
//        listDD.add(new diadiemModel(2,"Âm thực Sài Gòn",20000000));
//        listDD.add(new diadiemModel(3,"Văn nghệ Garden",2000000));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        listener();
    }

    private void getInit(){
        recyclerView = findViewById(R.id.recycler_view_listdiadiem);
        ivBack = findViewById(R.id.iv_back);
        btn_sort = findViewById(R.id.btn_sortGiatien);
        autoSearch=findViewById( R.id.auto_search );
        Intent intent = getIntent();
        listDD = (List<diadiemModel>) intent.getSerializableExtra("listdiadiem");
        if (listDD != null) {
            Log.d("ngu1", listDD.size() + "size");
            listDiaDiemAdapter = new ListDiaDiemAdapter(this,listDD);
            recyclerView.setAdapter(listDiaDiemAdapter);
            listDiaDiemAdapter.notifyDataSetChanged();
        } else {
            Log.d("ngu1", "listDD is null");
        }
    }
    private void sortListByPrice(List<diadiemModel> listDD) {
        if (listDD != null && !listDD.isEmpty()) {
            Collections.sort(listDD, new Comparator<diadiemModel>() {
                @Override
                public int compare(diadiemModel d1, diadiemModel d2) {
                    if (isPriceAscending) {
                        return Double.compare(d1.getGiadiadiem(), d2.getGiadiadiem());
                    } else {
                        return Double.compare(d2.getGiadiadiem(), d1.getGiadiadiem());
                    }
                }
            });
            listDiaDiemAdapter.notifyDataSetChanged();
        }
    }
    public void sortByPrice(View view) {
        isPriceAscending = !isPriceAscending;
        sortListByPrice(listDD);
    }
    public void listener(){
        autoSearch.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()==0){
                    listDiaDiemAdapter.setFilter(listDD);
                    listDiaDiemAdapter.notifyDataSetChanged();
                }else {
                    getDataSearch(s.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
    }
    public void getDataSearch(String searchString) {
        List<diadiemModel> filteredList = new ArrayList<>();
        for (diadiemModel item : listDD) {
            if (item.getTendiadiem().toLowerCase().contains(searchString.toLowerCase())) {
                filteredList.add(item);
            }
        }
        listDiaDiemAdapter.setFilter(filteredList);
    }
}