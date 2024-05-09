package com.example.quanlysukiendat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlysukiendat.Adapter.GridDichvuAdapter;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.retrofit.responseApi;
import com.example.quanlysukiendat.retrofit.retrofitInstance;
import com.example.quanlysukiendat.retrofit.retrofitInterface;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimKiemDiaDiemPhuHopActivity extends AppCompatActivity {
    Button btntimkiem, btnTimebegin, btnTimefinish, btnDate;
    GridView gridView;
    ImageView btnback;
    GridDichvuAdapter grvDichvuAdapter;
    final Calendar c = Calendar.getInstance();
    private List<diadiemModel> listdd;
    RelativeLayout  relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timkiemdiadiem_activity);
        String[] dv = {"tiệc cưới", "SK doanh nghiệp", "hội thảo", "SK ngoại khóa", "lễ kỷ niệm"};
        getInit();
        relativeLayout.setVisibility(View.GONE);
        grvDichvuAdapter = new GridDichvuAdapter(this, dv);
        gridView.setAdapter(grvDichvuAdapter);
        btntimkiem.setOnClickListener(view -> {
            showSearchOptionsPopup(view);
//            Intent intent = new Intent(getApplicationContext(), danhsachdiadiemActivity.class);
//            startActivity(intent);
        });
        btnTimebegin.setOnClickListener(view -> {
            settimebegin();
        });
        btnTimefinish.setOnClickListener(view -> {
            setTimefinish();
        });
        btnDate.setOnClickListener(view -> {
            setdate();
        });
        btnback.setOnClickListener(view -> {
            finish();
            onBackPressed();
        });
    }

    private void showSearchOptionsPopup(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(this, anchorView);
        popupMenu.inflate(R.menu.search_options_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_list_search:
                        relativeLayout.setVisibility(View.VISIBLE);
                        getList();
                        return true;
                    case R.id.menu_map_search:
                        relativeLayout.setVisibility(View.VISIBLE);
                        getListforMap();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    @NonNull
    private void getList() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Tạo các MultipartBody.Part tương ứng
                MultipartBody.Part nPart = MultipartBody.Part.createFormData("startDate", btnTimebegin.getText().toString());
                MultipartBody.Part mPart = MultipartBody.Part.createFormData("endDate", btnTimefinish.getText().toString());
                MultipartBody.Part tPart = MultipartBody.Part.createFormData("id_DichvuDs", grvDichvuAdapter.getSelectedPositionsString());

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addPart(nPart)
                        .addPart(mPart)
                        .addPart(tPart);
                RequestBody requestBody = builder.build();

                retrofitInterface retrofit = retrofitInstance.getService();
                Call<responseApi<List<diadiemModel>>> call = retrofit.searchDiadiem(requestBody);
                call.enqueue(new Callback<responseApi<List<diadiemModel>>>() {
                    @Override
                    public void onResponse(Call<responseApi<List<diadiemModel>>> call, Response<responseApi<List<diadiemModel>>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 200) {
                                Log.d("ngu", String.valueOf(response.body().getData().toString()));
                                List<diadiemModel> listdd1 = (List<diadiemModel>) response.body().getData();
                                listdd = listdd1;
                                Log.d("ngu", listdd1.size() + "size");
                                for (diadiemModel diadiem : listdd1) {
                                    Log.d("ngu", "ID: " + diadiem.getIddiadiem());
                                    Log.d("ngu", "Tên: " + diadiem.getTendiadiem());
                                    Log.d("ngu", "Địa chỉ: " + diadiem.getDiachi());
                                    // Thêm các trường thông tin khác của diadiemModel tương tự ở đây
                                }
                                relativeLayout.setVisibility(View.GONE);
                                Intent intent = new Intent(TimKiemDiaDiemPhuHopActivity.this, danhsachdiadiemActivity.class);
                                intent.putExtra("listdiadiem", (Serializable) listdd);
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
        }, 3000);
    }
    @NonNull
    private void getListforMap() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Tạo các MultipartBody.Part tương ứng
                MultipartBody.Part nPart = MultipartBody.Part.createFormData("startDate", btnTimebegin.getText().toString());
                MultipartBody.Part mPart = MultipartBody.Part.createFormData("endDate", btnTimefinish.getText().toString());
                MultipartBody.Part tPart = MultipartBody.Part.createFormData("id_DichvuDs", grvDichvuAdapter.getSelectedPositionsString());

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addPart(nPart)
                        .addPart(mPart)
                        .addPart(tPart);
                RequestBody requestBody = builder.build();

                retrofitInterface retrofit = retrofitInstance.getService();
                Call<responseApi<List<diadiemModel>>> call = retrofit.searchDiadiem(requestBody);
                call.enqueue(new Callback<responseApi<List<diadiemModel>>>() {
                    @Override
                    public void onResponse(Call<responseApi<List<diadiemModel>>> call, Response<responseApi<List<diadiemModel>>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode() == 200) {
                                Log.d("ngu", String.valueOf(response.body().getData().toString()));
                                List<diadiemModel> listdd1 = (List<diadiemModel>) response.body().getData();
                                listdd = listdd1;
                                Log.d("ngu", listdd1.size() + "size");
                                for (diadiemModel diadiem : listdd1) {
                                    Log.d("ngu", "ID: " + diadiem.getIddiadiem());
                                    Log.d("ngu", "Tên: " + diadiem.getTendiadiem());
                                    Log.d("ngu", "Địa chỉ: " + diadiem.getDiachi());
                                    // Thêm các trường thông tin khác của diadiemModel tương tự ở đây
                                }
                                relativeLayout.setVisibility(View.GONE);
                                Intent intent = new Intent(TimKiemDiaDiemPhuHopActivity.this, MapsActivity.class);
                                intent.putExtra("listdiadiem", (Serializable) listdd);
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
        }, 3000);
    }
    public void settimebegin() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // String m = ConvertMonth(monthOfYear+1);

                        monthOfYear = monthOfYear + 1;
                        final Calendar c = Calendar.getInstance();
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                        String[] now = date.trim().split("/");
                        if (Integer.parseInt(now[2]) > year) {
                            Toast.makeText(getApplicationContext(), "Năm đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(now[2]) == year) {
                            if (Integer.parseInt(now[1]) > monthOfYear) {
                                Toast.makeText(getApplicationContext(), "Tháng đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                                return;
                            } else if (Integer.parseInt(now[1]) == monthOfYear) {
                                if (Integer.parseInt(now[0]) > dayOfMonth) {
                                    //  Toast.makeText(context, now[0], Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Ngày không hợp lệ", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }

                        btnTimebegin.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);


                        //   Toast.makeText(context, btn_date.getText(), Toast.LENGTH_SHORT).show();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    public void setTimefinish() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // String m = ConvertMonth(monthOfYear+1);

                        monthOfYear = monthOfYear + 1;
                        final Calendar c = Calendar.getInstance();
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                        String[] now = date.trim().split("/");
                        String datebegin = btnTimebegin.getText().toString();
                        String[] datebegintrim = datebegin.trim().split("/");
                        if (Integer.parseInt(now[2]) > year || Integer.parseInt(datebegintrim[2]) > year) {
                            Toast.makeText(getApplicationContext(), "Năm đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(now[2]) == year || Integer.parseInt(datebegintrim[2]) == year) {
                            if (Integer.parseInt(now[1]) > monthOfYear || Integer.parseInt(datebegintrim[1]) > monthOfYear) {
                                Toast.makeText(getApplicationContext(), "Tháng đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                                return;
                            } else if (Integer.parseInt(now[1]) == monthOfYear || Integer.parseInt(datebegintrim[1]) == monthOfYear) {
                                if (Integer.parseInt(now[0]) > dayOfMonth || Integer.parseInt(datebegintrim[0]) > dayOfMonth) {
                                    //  Toast.makeText(context, now[0], Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Ngày không hợp lệ", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }

                        btnTimefinish.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);


                        //   Toast.makeText(context, btn_date.getText(), Toast.LENGTH_SHORT).show();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void setdate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // String m = ConvertMonth(monthOfYear+1);

                        monthOfYear = monthOfYear + 1;
                        final Calendar c = Calendar.getInstance();
                        String date = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                        String[] now = date.trim().split("/");
                        if (Integer.parseInt(now[2]) > year) {
                            Toast.makeText(getApplicationContext(), "Năm đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (Integer.parseInt(now[2]) == year) {
                            if (Integer.parseInt(now[1]) > monthOfYear) {
                                Toast.makeText(getApplicationContext(), "Tháng đặt không hợp lệ", Toast.LENGTH_SHORT).show();
                                return;
                            } else if (Integer.parseInt(now[1]) == monthOfYear) {
                                if (Integer.parseInt(now[0]) > dayOfMonth) {
                                    //  Toast.makeText(context, now[0], Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(), "Ngày không hợp lệ", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }

                        btnDate.setText(dayOfMonth + "/" + (monthOfYear) + "/" + year);


                        //   Toast.makeText(context, btn_date.getText(), Toast.LENGTH_SHORT).show();
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getInit() {
        btntimkiem = findViewById(R.id.btn_timkiem);
        btnTimebegin = findViewById(R.id.btn_time_begin);
        btnTimefinish = findViewById(R.id.btn_time_finish);
        btnDate = findViewById(R.id.btn_date);
        gridView = findViewById(R.id.grv_dicvu);
        relativeLayout = findViewById(R.id.overlayLayout);
        btnback = findViewById(R.id.iv_back);
    }

}