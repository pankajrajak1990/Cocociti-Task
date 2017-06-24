package com.metrohospital.tgs.cococititask.retrofit;
import com.metrohospital.tgs.cococititask.adapter.User;
import com.metrohospital.tgs.cococititask.datamodal.FeedModel;
import com.metrohospital.tgs.cococititask.datamodal.LoginModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface API {
    /* @GET url is after Base_URL.
       We are going to get List of country as response.
    */
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    @POST("/users/sign_in")
    Call<LoginModel> checkLogin(@Body User user);
    @Headers({
            "Content-Type: application/json",
            "Accept: application/json",
    })
    @GET("/get_feeds")
    Call<FeedModel> getFeed(@Header("X-ACCESS-TOKEN ") String token,
                            @Header("X-USER-EMAIL") String email);
}