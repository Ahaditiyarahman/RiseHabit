package com.example.risehabit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class TodoActivity extends AppCompatActivity {

    final int PAGE_INI = 3;

    ImageView imgBebek;
    ImageView btnNavHome, btnNavDaily, btnNavProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        imgBebek = findViewById(R.id.bebekView);
        btnNavHome = findViewById(R.id.btnNavHome);
        btnNavDaily = findViewById(R.id.btnNavDaily);
        btnNavProfile = findViewById(R.id.btnNavProfile);

        btnNavHome.setOnClickListener(v -> pindahHalaman(1, HomeActivity.class));
        btnNavDaily.setOnClickListener(v -> pindahHalaman(2, DailyActivity.class));
        btnNavProfile.setOnClickListener(v -> pindahHalaman(4, ProfileActivity.class));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
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