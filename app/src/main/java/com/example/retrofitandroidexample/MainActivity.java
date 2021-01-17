package com.example.retrofitandroidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitandroidexample.api.ApiService;
import com.example.retrofitandroidexample.model.Currency;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerms;
    private TextView tvSource;
    private TextView tvUsdVnd;
    private Button btnCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSource = findViewById(R.id.tv_source);
        tvTerms = findViewById(R.id.tv_term);
        tvUsdVnd = findViewById(R.id.tv_usd_vnd);
        btnCallApi = findViewById(R.id.btn_call_api);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallApi();
            }
        });
    }

    private void clickCallApi() {

        //Link API: http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
//        ApiService.apiServie.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6",
//                "VND",
//                "USD",
//                1).enqueue(new Callback<Currency>() {
//        ApiService.apiServie.convertUsdToVnd1().enqueue(new Callback<Currency>() {
        Map<String, String> options = new HashMap<>();
        options.put("access_key", "843d4d34ae72b3882e3db642c51e28e6");
        options.put("currencies", "VND");
        options.put("source", "USD");
        options.put("format", "1");
        ApiService.apiServie.convertUsdToVnd2(options).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();
                if (currency != null && currency.isSuccess()) {
                    tvTerms.setText(currency.getTerms());
                    tvSource.setText(currency.getSource());
                    tvUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}