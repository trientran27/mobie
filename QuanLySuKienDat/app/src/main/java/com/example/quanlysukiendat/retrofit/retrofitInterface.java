package com.example.quanlysukiendat.retrofit;

import com.example.quanlysukiendat.Model.createDondatResp;
import com.example.quanlysukiendat.Model.diadiemModel;
import com.example.quanlysukiendat.Model.lichtrinhModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface retrofitInterface {
    @POST("diadiem/search")
    Call<responseApi<List<diadiemModel>>> searchDiadiem(@Body RequestBody body);


    @GET("sukien/listlt/{eventId}")
    Call<responseApi<List<lichtrinhModel>>> getLichtrinh(
            @Path("eventId") int eventId
    );

    @GET("sukien/listddiem/{eventId}")
    Call<responseApi<List<diadiemModel>>> getlistdiadiem(
            @Path("eventId") int eventId
    );

    @POST("dondat/create")
    Call<responseApi<createDondatResp>> createDondat(@Body RequestBody body);
}
