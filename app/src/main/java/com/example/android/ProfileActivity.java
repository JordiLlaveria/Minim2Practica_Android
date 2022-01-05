package com.example.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView usernameText;
    TextView nameText;

    ImageView imgUserView;
    ImageView imgJugonView;
    ImageView imgLentoView;
    ImageView imgEpicoView;
    ImageView imgSostenibleView;
    ImageView imgLegendarioView;
    ImageView imgMeteoricoView;


    Services API;

    public void createAPI() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Services.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(Services.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Intent intent = getIntent();

        progressBar = findViewById(R.id.progressBarProfile);
        usernameText = findViewById(R.id.usernameTxtView);
        nameText = findViewById(R.id.nameTxtView);

        imgUserView= findViewById(R.id.imgUser);
        imgJugonView = findViewById(R.id.jugonImage);
        imgEpicoView = findViewById(R.id.epicoImage);
        imgLegendarioView = findViewById(R.id.legendarioImage);
        imgLentoView = findViewById(R.id.lentoImage);
        imgMeteoricoView = findViewById(R.id.meteoricoImage);
        imgSostenibleView = findViewById(R.id.sostenibleImage);

        progressBar.setVisibility(View.VISIBLE);
        createAPI();
        doProfileCall("Jordi");
    }

    public void doProfileCall(String username) {

        Call<User> call = API.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code()!=500){
                    User userreceived = response.body();
                    usernameText.setText(userreceived.getUsername());
                    nameText.setText(userreceived.getName()+ " " + userreceived.getSurname());
                    imgUserView.setVisibility(View.VISIBLE);
                    Picasso.get()
                            .load("http://localhost:8080/public/jugon.png")
                            .into(imgJugonView);
                    setInsignias(userreceived.getBadges());
                    }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Context context = getApplicationContext();
                String text = "User not found, try again!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("Minim2","onFailure ....",t);
            }
        });
    }

    public void setInsignias(LinkedList<Badge> badges){
        int i=0;
        while (i<badges.size()){
            if (badges.get(i).getName().equals("El más jugón")){
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300,300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgJugonView);
            }
            else if (badges.get(i).getName().equals("El más lento")) {
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300, 300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgLentoView);
            }
            else if (badges.get(i).getName().equals("El más épico")) {
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300, 300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgEpicoView);
            }
            else if (badges.get(i).getName().equals("El más sostenible")) {
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300, 300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgSostenibleView);
            }
            else if (badges.get(i).getName().equals("El más legendario")) {
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300, 300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgLegendarioView);
            }
            else if(badges.get(i).getName().equals("El más meteórico")) {
                Picasso.get()
                        .load(badges.get(i).getUrl())
                        .resize(300, 300)
                        .centerCrop()
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.stat_notify_error)
                        .into(imgMeteoricoView);
            }
            i++;
            }
        }
}
