package com.zvibadash.gymlogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogEntrance;
    int busyness = -1;

    SeekBar.OnSeekBarChangeListener sbChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            busyness = i;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SeekBar sbBusy = findViewById(R.id.sbBusy);
        sbBusy.setOnSeekBarChangeListener(sbChangeListener);

        btnLogEntrance = findViewById(R.id.btnWelcomeLogEnt);
        btnLogEntrance.setOnClickListener(view -> {
            if (busyness == -1) {
                Toast.makeText(this, "Please select busyness level", Toast.LENGTH_SHORT).show();
            } else {
                logEntranceToMemory(busyness);
            }
        });
    }

    public void logEntranceToMemory(int busyness) {

    }
}