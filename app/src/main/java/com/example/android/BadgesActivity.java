package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class BadgesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterBadge mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Services API;
    List<Badge> badgeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insignias);

        Intent intent = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterBadge();
        recyclerView.setAdapter(mAdapter);

        createAPI();
        doApiCall();
    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Services.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(Services.class);
    }

    public void doApiCall(){
        Call<List<Badge>> call = API.getBadges();
        call.enqueue(new Callback<List<Badge>>() {
            @Override
            public void onResponse(Call<List<Badge>> call, Response<List<Badge>> response) {
                if(response.code()==200) {
                    badgeList=response.body();
                    mAdapter.setData(badgeList);
                }
                else {
                    Toast toast = Toast.makeText(BadgesActivity.this,"Insignias no encontradas",Toast.LENGTH_SHORT);
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    toast.show();
                }}

            @Override
            public void onFailure(Call<List<Badge>> call, Throwable t) {
                Toast toast = Toast.makeText(BadgesActivity.this,"ERROR DE CONEXIÓN, no se ha podido realizar la petición.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
