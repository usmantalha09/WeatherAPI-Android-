package com.example.weatherapp.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?&appid=d732664f3d35960ab0c226c9a02a45b3")
    Call<Example> getWeatherData(@Query("q") String name);
}
