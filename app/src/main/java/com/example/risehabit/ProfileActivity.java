package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    // --- SETTING HALAMAN INI ---
    final int PAGE_INI = 4;

    ImageView imgBebek;
    ImageView btnNavHome, btnNavDaily, btnNavTodo; // Profile tidak perlu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgBebek = findViewById(R.id.bebekView);
        btnNavHome = findViewById(R.id.btnNavHome);
        btnNavDaily = findViewById(R.id.btnNavDaily);
        btnNavTodo = findViewById(R.id.btnNavTodo);

        // Navigasi dari Profile (4)
        btnNavHome.setOnClickListener(v -> pindahHalaman(1, HomeActivity.class));
        btnNavDaily.setOnClickListener(v -> pindahHalaman(2, DailyActivity.class));
        btnNavTodo.setOnClickListener(v -> pindahHalaman(3, TodoActivity.class));

        // Setting Tombol Back (Mundur)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                imgBebek.setScaleX(-1f); // Balik kiri
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