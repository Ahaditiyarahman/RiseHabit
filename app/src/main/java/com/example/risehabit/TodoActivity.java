package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.DecelerateInterpolator;

public class TodoActivity extends AppCompatActivity {

    final int PAGE_INI = 3; // Ganti sesuai halaman (1, 2, 3, atau 4)

    // Variabel Pemain (Orang)
    ImageView imgPlayer;

    // Variabel Tombol
    ImageView btnNavHome, btnNavDaily, btnNavProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);
        setContentView(R.layout.activity_todo);
        int dariPage = getIntent().getIntExtra("DARI_PAGE", 0);

        if (dariPage != 0) {
            Slide slide = new Slide();
            slide.setDuration(100);
            slide.setInterpolator(new DecelerateInterpolator());

            // LOGIKA:
            // Kalau datang dari halaman kecil (misal 1 ke 2), kita masuk dari Kanan (Gravity.END)
            // Kalau datang dari halaman besar (misal 3 ke 2), kita masuk dari Kiri (Gravity.START)
            if (dariPage < PAGE_INI) {
                slide.setSlideEdge(Gravity.END); // Masuk dari Kanan
            } else {
                slide.setSlideEdge(Gravity.START); // Masuk dari Kiri
            }

            getWindow().setEnterTransition(slide);
        }

        // 1. Sambungkan dengan ID baru di XML
        imgPlayer = findViewById(R.id.playerView);

        btnNavHome = findViewById(R.id.btnNavHome);
        btnNavDaily = findViewById(R.id.btnNavDaily);
        btnNavProfile = findViewById(R.id.btnNavProfile);

        // 2. Logic Tombol Navigasi
        btnNavHome.setOnClickListener(v -> pindahHalaman(2, HomeActivity.class));
        btnNavDaily.setOnClickListener(v -> pindahHalaman(3, DailyActivity.class));
        btnNavProfile.setOnClickListener(v -> pindahHalaman(4, ProfileActivity.class));
    }

    // --- FUNGSI PINDAH HALAMAN (KHUSUS ORANG) ---
    private void pindahHalaman(int pageTujuan, Class<?> classTujuan) {
        Intent intent = new Intent(this, classTujuan);
        intent.putExtra("DARI_PAGE", PAGE_INI);

        // Siapkan Animasi Slide untuk Halaman INI (yang mau ditinggalkan)
        Slide slideKeluar = new Slide();
        slideKeluar.setDuration(500);

        // LOGIKA ARAH & GAMBAR ORANG
        if (pageTujuan > PAGE_INI) {
            // MAJU (Misal 1 ke 2)
            // Orang hadap Kanan
            imgPlayer.setScaleX(1f);

            // Halaman ini geser keluar ke Kiri
            slideKeluar.setSlideEdge(Gravity.START);
        } else {
            // MUNDUR (Misal 2 ke 1)
            // Orang hadap Kiri
            imgPlayer.setScaleX(-1f);

            // Halaman ini geser keluar ke Kanan
            slideKeluar.setSlideEdge(Gravity.END);
        }

        // Pasang animasi ke Window
        getWindow().setExitTransition(slideKeluar);

        // Jalankan dengan Shared Element
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this, imgPlayer, "transisiPemain");

        startActivity(intent, options.toBundle());
    }
}