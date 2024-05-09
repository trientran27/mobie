package com.example.quanlysukiendat;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quanlysukiendat.Adapter.ImageAdapter;
import com.example.quanlysukiendat.Adapter.ListLichTrinhAdapter;
import com.example.quanlysukiendat.Model.ImageDiadiem;
import com.example.quanlysukiendat.Model.createDondatResp;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;
import com.example.quanlysukiendat.retrofit.responseApi;
import com.example.quanlysukiendat.retrofit.retrofitInstance;
import com.example.quanlysukiendat.retrofit.retrofitInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChitietdiadiemActivity extends AppCompatActivity {
    ImageView btnback;
    Button datngay;
    TextView namediadiem,diachi, giadiadiem,tieude;
    private ViewPager2 mViewPager2;
    private List<ImageDiadiem> imageList;
    private CircleIndicator3 mIndicator3;
    private int idsukien = 1;
    private diadiemModel diadiem;
    private RelativeLayout relativeLayout;
    private Handler mHandler= new Handler( Looper.getMainLooper());
    private Runnable mRunnable= new Runnable() {
        @Override
        public void run() {
            int troi =  mViewPager2.getCurrentItem();
            if (troi ==imageList.size()-1){
                mViewPager2.setCurrentItem( 0 );
            }else {
                mViewPager2.setCurrentItem( troi+1 );
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdiadiem_activity);
        diadiem = (diadiemModel) getIntent().getSerializableExtra("diadiem");
        getInit();
        relativeLayout.setVisibility(View.GONE);
        mViewPager2.setOffscreenPageLimit( 3 );
        mViewPager2.setClipToPadding( false );
        mViewPager2.setClipChildren( false );
        imageList= getListImage();
        ImageAdapter imageAdapter= new ImageAdapter(this, imageList );
        mViewPager2.setAdapter( imageAdapter );
        mIndicator3.setViewPager( mViewPager2 );
        mViewPager2.registerOnPageChangeCallback( new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected( position );
                mHandler.removeCallbacks( mRunnable );
                mHandler.postDelayed( mRunnable,3000 );
            }
        } );

        // Update UI with diadiem details
        if (diadiem != null) {
            namediadiem.setText(diadiem.getTendiadiem());
            diachi.setText(diadiem.getDiachi());
            tieude.setText(diadiem.getDecrision());
            giadiadiem.setText(String.valueOf(diadiem.getGiadiadiem())+"đ");
        }

        btnback.setOnClickListener(view -> finish());
        datngay.setOnClickListener(view -> {
            datdiadiem();
        });
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    private void getInit(){
        namediadiem = findViewById(R.id.tv_name);
        diachi = findViewById(R.id.tv_address);
        giadiadiem = findViewById(R.id.tv_giatien);
        tieude = findViewById(R.id.tv_tieude);
        mViewPager2= findViewById( R.id.view_page_top );
        mIndicator3= findViewById( R.id.circleIndicator3 );
        btnback = findViewById(R.id.iv_back);
        datngay = findViewById(R.id.btn_datngay);
        relativeLayout = findViewById(R.id.overlayLayout);
    }
    private List<ImageDiadiem> getListImage() {
        List<ImageDiadiem> list =new ArrayList<>();
        if (diadiem.getImageURLs() != null && !diadiem.getImageURLs().isEmpty()) {
            for (String image : diadiem.getImageURLs()) {
                list.add(new ImageDiadiem(image));
            }
        }else {
        list.add( new ImageDiadiem("https://tranganpalace.vn/wp-content/uploads/2019/11/hinh-anh-trung-tam-to-chuc-su-kien-so-1.jpg") );
        list.add( new ImageDiadiem("https://www.sukien-teambuilding.com/wp-content/uploads/dia-diem-to-chuc-su-kien-tai-ha-noi-ks-jw-marriott.jpg") );
        list.add( new ImageDiadiem("https://senxanhevent.vn/uploads/images/27.%20Trung%20ta%CC%82m%20to%CC%82%CC%89%20chu%CC%9B%CC%81c%20su%CC%9B%CC%A3%20kie%CC%A3%CC%82n/Trung%20ta%CC%82m%20to%CC%82%CC%89%20chu%CC%9B%CC%81c%20su%CC%9B%CC%A3%20kie%CC%A3%CC%82n%201/anh-8-trung-tam-to-chuc-su-kien-trong-dong-palace-lang-yen.jpg") );
        list.add( new ImageDiadiem("https://thongbaosukien24h.com/wp-content/uploads/2020/09/dia-diem-su-kien-100-nguoi-1.jpg") );
        list.add( new ImageDiadiem("https://phucthanhnhan.net/contents_images/images/asiana-plaza.jpg") );
        }
        return list;
    }
    private void datdiadiem(){
        relativeLayout.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            JSONObject diaDiem = new JSONObject();
            JSONObject suKien = new JSONObject();
            JSONObject json = new JSONObject();

            try {
                diaDiem.put("id", diadiem.getIddiadiem());
                suKien.put("id", idsukien);

                json.put("diaDiem", diaDiem);
                json.put("suKien", suKien);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString());
            retrofitInterface retrofit = retrofitInstance.getService();
            Call<responseApi<createDondatResp>> call = retrofit.createDondat(requestBody);
            call.enqueue(new Callback<responseApi<createDondatResp>>() {
                @Override
                public void onResponse(Call<responseApi<createDondatResp>> call, Response<responseApi<createDondatResp>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getCode() == 200) {
                            Log.d("ngu", String.valueOf(response.body().getData().toString()));
                            createDondatResp listdd1 = (createDondatResp) response.body().getData();
                            relativeLayout.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(),ChiTietSuKienActivity.class);
                            Toast.makeText(getApplicationContext(), "Thêm địa điểm thành công.", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            relativeLayout.setVisibility(View.GONE);
                            finish();
                        }
                        Log.d("ngu", String.valueOf(response.body().getCode()));
                        // Xử lý phản hồi thành công ở đây
                    } else {
                        Log.d("ngu", response.toString());
                        // Xử lý phản hồi lỗi ở đây
                    }
                }

                @Override
                public void onFailure(Call<responseApi<createDondatResp>> call, Throwable t) {
                    Log.d("ngu", "lỗi là:" + t.toString());
                    Log.d("ngu", "loiiii");
                    // Xử lý lỗi kết nối ở đây
                }
            });
        }, 2000);
    }
}