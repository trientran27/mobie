package com.example.quanlysukiendat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysukiendat.Adapter.ListLichTrinhAdapter;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;
import com.example.quanlysukiendat.retrofit.responseApi;
import com.example.quanlysukiendat.retrofit.retrofitInstance;
import com.example.quanlysukiendat.retrofit.retrofitInterface;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietSuKienActivity extends AppCompatActivity {
    Button btnKM, btnlichtrinh, btndiadiem;
    RelativeLayout relativeLayout;
    private int idsukien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsukien_activity);

        getInit();
        btnKM.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),KhachMoiQuanLyActivity.class);
            startActivity(intent);
        });

        btnlichtrinh.setOnClickListener(view -> {
            getTendiadiem();
        });

        btndiadiem.setOnClickListener(view -> {
            relativeLayout.setVisibility(View.VISIBLE);
            getListtendiadiem();
        });
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    private void getInit(){
        btnKM = findViewById(R.id.btn_khachmoi);
        btnlichtrinh = findViewById(R.id.btn_lichtrinh);
        btndiadiem = findViewById(R.id.btn_diadiem);
        relativeLayout = findViewById(R.id.overlayLayout);
        relativeLayout.setVisibility(View.GONE);
    }
    public void getListtendiadiem(){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    retrofitInterface retrofit = retrofitInstance.getService();
                    Call<responseApi<List<diadiemModel>>> call = retrofit.getlistdiadiem(1);
                    call.enqueue(new Callback<responseApi<List<diadiemModel>>>() {
                        @Override
                        public void onResponse(Call<responseApi<List<diadiemModel>>> call, Response<responseApi<List<diadiemModel>>> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getCode() == 200) {
                                    Log.d("ngu", String.valueOf(response.body().getData().toString()));
                                    List<diadiemModel> listdd1 = (List<diadiemModel>) response.body().getData();
                                    relativeLayout.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(),DiaDiemQuanLyActivity.class);
                                    intent.putExtra("listdiadiem", (Serializable) listdd1);
                                    startActivity(intent);
                                } else if (response.body().getCode() == 400){
                                    Toast.makeText(getApplicationContext(), "Địa điểm đã được đặt vào thời gian này!", Toast.LENGTH_SHORT).show();
                                }
                                Log.d("ngu", String.valueOf(response.body().getCode()));
                                // Xử lý phản hồi thành công ở đây
                            } else {
                                Log.d("ngu", response.toString());
                                // Xử lý phản hồi lỗi ở đây
                            }
                        }

                        @Override
                        public void onFailure(Call<responseApi<List<diadiemModel>>> call, Throwable t) {
                            Log.d("ngu", "lỗi là:" + t.toString());
                            Log.d("ngu", "loiiii");
                            // Xử lý lỗi kết nối ở đây
                        }
                    });
                }
            }, 2000);
    }
    public void getTendiadiem(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                retrofitInterface retrofit = retrofitInstance.getService();
                Call<responseApi<List<diadiemModel>>> call = retrofit.getlistdiadiem(1);
                call.enqueue(new Callback<responseApi<List<diadiemModel>>>() {
                    @Override
                    public void onResponse(Call<responseApi<List<diadiemModel>>> call, Response<responseApi<List<diadiemModel>>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 200) {
                                Log.d("ngu", String.valueOf(response.body().getData().toString()));
                                List<diadiemModel> listdd1 = (List<diadiemModel>) response.body().getData();
                                relativeLayout.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(),LichtrinhActivity.class);
                                intent.putExtra("listdiadiem", (Serializable) listdd1);
                                intent.putExtra("idsukien", 1);
                                startActivity(intent);
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
                    public void onFailure(Call<responseApi<List<diadiemModel>>> call, Throwable t) {
                        Log.d("ngu", "lỗi là:" + t.toString());
                        Log.d("ngu", "loiiii");
                        // Xử lý lỗi kết nối ở đây
                    }
                });
            }
        }, 2000);
    }
}