package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edit;
    ImageView btn;
    TextView wtemp,wpre,whum,wspe,wplc ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.searchBar);
        wtemp = findViewById(R.id.Temp);
        wpre = findViewById(R.id.Pressure);
        whum = findViewById(R.id.Humidity);
        wplc = findViewById(R.id.Place);
        wspe = findViewById(R.id.Speed);
        btn = findViewById(R.id.SearchButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Place = edit.getText().toString().trim();
                getWeatherData(Place);
                wplc.setText(Place);

                Toast.makeText(MainActivity.this, Place, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWeatherData(String name){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                wtemp.setText(response.body().getMain().getTemp());
                wpre.setText(response.body().getMain().getPressure());
                whum.setText(response.body().getMain().getHumidity());
                wspe.setText(response.body().getWind().getSpeed());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}