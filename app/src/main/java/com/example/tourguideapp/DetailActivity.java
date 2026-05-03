// DetailActivity.java
package com.example.tourguideapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Hide default action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // --- Receive data from MainActivity ---
        String name        = getIntent().getStringExtra("name");
        String location    = getIntent().getStringExtra("location");
        String season      = getIntent().getStringExtra("season");
        String description = getIntent().getStringExtra("description");

        // --- Bind views ---
        TextView tvName        = findViewById(R.id.tvPlaceName);
        TextView tvLocation    = findViewById(R.id.tvLocation);
        TextView tvSeason      = findViewById(R.id.tvSeason);
        TextView tvDescription = findViewById(R.id.tvDescription);
        ImageButton btnBack    = findViewById(R.id.btnBack);
        Button btnMaps         = findViewById(R.id.btnOpenMaps);
        Button btnFav          = findViewById(R.id.btnFavorite);
        ImageButton btnFavHead = findViewById(R.id.btnFavHeader);

        // --- Populate text ---
        if (name != null)        tvName.setText(name);
        if (location != null)    tvLocation.setText(location);
        if (season != null)      tvSeason.setText("Best Season: " + season);
        if (description != null) tvDescription.setText(description);

        // --- Back button ---
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous screen
            }
        });

        // --- Open in Google Maps ---
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = Uri.encode(name + ", " + location);
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + query);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    // Fallback: open in browser
                    Uri browserUri = Uri.parse("https://www.google.com/maps/search/" + query);
                    startActivity(new Intent(Intent.ACTION_VIEW, browserUri));
                }
            }
        });

        // --- Add to Favorites ---
        View.OnClickListener favListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                if (isFavorite) {
                    Toast.makeText(DetailActivity.this,
                            name + " added to favorites! ★", Toast.LENGTH_SHORT).show();
                    btnFav.setText("★  SAVED TO FAVORITES");
                    btnFavHead.setImageResource(android.R.drawable.btn_star_big_on);
                } else {
                    Toast.makeText(DetailActivity.this,
                            name + " removed from favorites", Toast.LENGTH_SHORT).show();
                    btnFav.setText("ADD TO FAVORITES");
                    btnFavHead.setImageResource(android.R.drawable.btn_star_big_off);
                }
            }
        };

        btnFav.setOnClickListener(favListener);
        btnFavHead.setOnClickListener(favListener);
    }
}