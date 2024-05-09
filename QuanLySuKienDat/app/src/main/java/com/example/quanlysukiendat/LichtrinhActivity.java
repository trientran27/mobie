package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlysukiendat.Adapter.ListLichTrinhAdapter;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;
import com.example.quanlysukiendat.retrofit.responseApi;
import com.example.quanlysukiendat.retrofit.retrofitInstance;
import com.example.quanlysukiendat.retrofit.retrofitInterface;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichtrinhActivity extends AppCompatActivity {
    ListLichTrinhAdapter listLichTrinhAdapter;
    RecyclerView recyclerView;
    ImageView ivBack;
    private int idsukien;
    RelativeLayout relativeLayout;
    TextView diachi;
    List<diadiemModel> listDD ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lichtrinhsukien_activity);
        getInit();
        relativeLayout.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        idsukien = intent.getIntExtra("idsukien",0);
        listDD = (List<diadiemModel>) intent.getSerializableExtra("listdiadiem");
        Log.d("ngu", "idsukien là: "+ idsukien);
        getList();
//        listLT.add(new lichtrinhModel(1,"Giao lưu cán bộ ","9h","28-3-2024"));
//        listLT.add(new lichtrinhModel(2,"Âm thực","9h30","28-3-2024"));
//        listLT.add(new lichtrinhModel(3,"Văn nghệ","11h","28-3-2024"));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        listLichTrinhAdapter = new ListLichTrinhAdapter( listLT);
//        recyclerView.setAdapter(listLichTrinhAdapter);
//        listLichTrinhAdapter.notifyDataSetChanged();
        ivBack.setOnClickListener(view -> finish());
    }

    private void getInit(){
        recyclerView = findViewById(R.id.recycler_view_lichtrinh);
        ivBack = findViewById(R.id.iv_back);
        relativeLayout = findViewById(R.id.overlayLayout);
        diachi = findViewById(R.id.diachi);
    }
    private void getList() {
        new Handler().postDelayed(() -> {
            retrofitInterface retrofit = retrofitInstance.getService();
            Call<responseApi<List<lichtrinhModel>>> call = retrofit.getLichtrinh(idsukien);
            call.enqueue(new Callback<responseApi<List<lichtrinhModel>>>() {
                @Override
                public void onResponse(Call<responseApi<List<lichtrinhModel>>> call, Response<responseApi<List<lichtrinhModel>>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            Log.d("ngu", String.valueOf(response.body().getData().toString()));
                            List<lichtrinhModel> listdd1 = (List<lichtrinhModel>) response.body().getData();
                            if (listdd1.size()>0 && listdd1 != null) {
                                diachi.setText(listDD.get(0).getTendiadiem());
                                listLichTrinhAdapter = new ListLichTrinhAdapter(listdd1);
                                recyclerView.setAdapter(listLichTrinhAdapter);
                                listLichTrinhAdapter.notifyDataSetChanged();
                            }
                            relativeLayout.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(getApplicationContext(), "Lỗi code rồi", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("ngu", String.valueOf(response.body().getCode()));
                        // Xử lý phản hồi thành công ở đây
                    } else {
                        Log.d("ngu", response.toString());
                        // Xử lý phản hồi lỗi ở đây
                    }
                }

                @Override
                public void onFailure(Call<responseApi<List<lichtrinhModel>>> call, Throwable t) {
                    Log.d("ngu", "lỗi là:" + t.toString());
                    Log.d("ngu", "loiiii");
                    // Xử lý lỗi kết nối ở đây
                }
            });
        }, 3000);
    }
}