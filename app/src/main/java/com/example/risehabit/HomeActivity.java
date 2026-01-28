package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    // --- SETTING HALAMAN INI ---
    final int PAGE_INI = 1;

    ImageView imgBebek;
    ImageView btnNavDaily, btnNavTodo, btnNavProfile; // Home tidak perlu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Kenalan dengan View
        imgBebek = findViewById(R.id.bebekView);

        // btnNavHome = findViewById(R.id.btnNavHome); // Tidak dipakai di halaman ini
        btnNavDaily = findViewById(R.id.btnNavDaily);
        btnNavTodo = findViewById(R.id.btnNavTodo);
        btnNavProfile = findViewById(R.id.btnNavProfile);

        // 2. Setting Tombol Navigasi
        // Dari Home (1) bisa ke 2, 3, atau 4
        btnNavDaily.setOnClickListener(v -> pindahHalaman(2, DailyActivity.class));
        btnNavTodo.setOnClickListener(v -> pindahHalaman(3, TodoActivity.class));
        btnNavProfile.setOnClickListener(v -> pindahHalaman(4, ProfileActivity.class));

        // 3. Setting Tombol Back (Keluar Aplikasi)
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Karena ini halaman 1, kalau diback berarti keluar aplikasi
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    // --- FUNGSI PINDAH HALAMAN (Sama untuk semua file) ---
    private void pindahHalaman(int pageTujuan, Class<?> classTujuan) {
        // Logika Arah Bebek
        if (pageTujuan > PAGE_INI) {
            imgBebek.setScaleX(1f);  // Maju (Kanan)
        } else {
            imgBebek.setScaleX(-1f); // Mundur (Kiri)
        }

        Intent intent = new Intent(this, classTujuan);
        intent.putExtra("DARI_PAGE", PAGE_INI);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this, imgBebek, "transisiBebek");

        startActivity(intent, options.toBundle());
    }
}