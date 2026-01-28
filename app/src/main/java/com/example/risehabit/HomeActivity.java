package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    final int PAGE_INI = 1;

    ImageView imgBebek;
    ImageView btnNavDaily, btnNavTodo, btnNavProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imgBebek = findViewById(R.id.bebekView);

        btnNavDaily = findViewById(R.id.btnNavDaily);
        btnNavTodo = findViewById(R.id.btnNavTodo);
        btnNavProfile = findViewById(R.id.btnNavProfile);

        btnNavDaily.setOnClickListener(v -> pindahHalaman(2, DailyActivity.class));
        btnNavTodo.setOnClickListener(v -> pindahHalaman(3, TodoActivity.class));
        btnNavProfile.setOnClickListener(v -> pindahHalaman(4, ProfileActivity.class));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
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