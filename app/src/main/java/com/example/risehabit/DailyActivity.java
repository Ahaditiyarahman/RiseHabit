package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class DailyActivity extends AppCompatActivity {

    // --- SETTING HALAMAN INI ---
    final int PAGE_INI = 2;

    ImageView imgBebek;
    ImageView btnNavHome, btnNavTodo, btnNavProfile; // Daily tidak perlu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        imgBebek = findViewById(R.id.bebekView);
        btnNavHome = findViewById(R.id.btnNavHome);
        btnNavTodo = findViewById(R.id.btnNavTodo);
        btnNavProfile = findViewById(R.id.btnNavProfile);

        // Navigasi dari Daily (2)
        btnNavHome.setOnClickListener(v -> pindahHalaman(1, HomeActivity.class));
        btnNavTodo.setOnClickListener(v -> pindahHalaman(3, TodoActivity.class));
        btnNavProfile.setOnClickListener(v -> pindahHalaman(4, ProfileActivity.class));

        // Setting Tombol Back (Mundur)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Balik badan dulu sebelum mundur
                imgBebek.setScaleX(-1f);
                supportFinishAfterTransition();
            }
        });
    }

    private void pindahHalaman(int pageTujuan, Class<?> classTujuan) {
        if (pageTujuan > PAGE_INI) {
            imgBebek.setScaleX(1f);
        } else {
            imgBebek.setScaleX(-1f);
        }
        Intent intent = new Intent(this, classTujuan);
        intent.putExtra("DARI_PAGE", PAGE_INI);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this, imgBebek, "transisiBebek");
        startActivity(intent, options.toBundle());
    }
}