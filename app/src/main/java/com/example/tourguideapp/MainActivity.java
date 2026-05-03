// MainActivity.java
package com.example.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide default action bar (we use custom header)
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Card click listeners
        CardView cardMunnar = findViewById(R.id.cardMunnar);
        CardView cardOoty   = findViewById(R.id.cardOoty);
        CardView cardCoorg  = findViewById(R.id.cardCoorg);

        cardMunnar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Munnar", "Idukki, Kerala", "Summer",
                        "Munnar is a beautiful hill station located in the Idukki district of Kerala, " +
                                "situated at an altitude of 1,600 meters above sea level. It is famous for its " +
                                "vast stretches of tea plantations, misty mountains, exotic wildlife, and refreshing climate.");
            }
        });

        cardOoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Ooty", "Nilgiris, Tamil Nadu", "Spring",
                        "Ooty, known as the Queen of Hill Stations, is nestled in the Nilgiri Hills of " +
                                "Tamil Nadu. Famous for its Botanical Gardens, the Nilgiri Mountain Railway (a UNESCO " +
                                "World Heritage Site), and scenic Ooty Lake, it attracts millions of tourists each year.");
            }
        });

        cardCoorg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail("Coorg", "Kodagu, Karnataka", "Winter",
                        "Coorg, also called Kodagu, is a scenic hill district in Karnataka known as the " +
                                "'Scotland of India'. It is famous for its coffee and spice plantations, misty hills, " +
                                "thundering waterfalls, and the warm hospitality of the Kodava people.");
            }
        });
    }

    private void openDetail(String name, String location, String season, String description) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("location", location);
        intent.putExtra("season", season);
        intent.putExtra("description", description);
        startActivity(intent);
    }
}