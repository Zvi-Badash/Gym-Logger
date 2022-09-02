package com.zvibadash.gymlogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    SeekBar sbBusy;
    Button btnLogEntrance, btnResumeSession, btnFinishSession;
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

        sbBusy = findViewById(R.id.sbBusy);
        btnLogEntrance = findViewById(R.id.btnLogEntrance);
        btnResumeSession = findViewById(R.id.btnResumeSession);
        btnFinishSession = findViewById(R.id.btnFinishSession);

        sbBusy.setOnSeekBarChangeListener(sbChangeListener);
        btnLogEntrance.setOnClickListener(view -> {
            if (busyness == -1) {
                Toast.makeText(this, "Please select busyness level", Toast.LENGTH_SHORT).show();
            } else {
                (new DataLogger(getApplicationContext())).logEntranceData(busyness);
                startActivity(new Intent(this, ExerciseLoggingActivity.class));
            }
        });
        btnResumeSession.setOnClickListener(view -> startActivity(new Intent(this, ExerciseLoggingActivity.class)));
        btnFinishSession.setOnClickListener(view -> {
            (new DataLogger(getApplicationContext())).logExitData();
            finishAffinity();
        });
    }
}