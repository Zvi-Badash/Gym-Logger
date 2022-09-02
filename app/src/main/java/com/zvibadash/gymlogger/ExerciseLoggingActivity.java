package com.zvibadash.gymlogger;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ExerciseLoggingActivity extends AppCompatActivity {
    Button btLog;
    Spinner spArea, spExercise;
    EditText etReps, etSets, etWeight;

    String exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_logging);

        btLog = findViewById(R.id.btLogExercise);
        spArea = findViewById(R.id.spArea);
        spExercise = findViewById(R.id.spExercise);
        etReps = findViewById(R.id.etReps);
        etSets = findViewById(R.id.etSets);
        etWeight = findViewById(R.id.etWeight);

        ArrayAdapter<CharSequence> adapterArea = ArrayAdapter.createFromResource(this,
                R.array.body_parts_array, android.R.layout.simple_spinner_item);
        adapterArea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(adapterArea);
        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = adapterView.getSelectedItem().toString();
                ArrayAdapter<CharSequence> adapter;

                switch (selected) {
                    case "Legs":
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.leg_exercises_array,
                                android.R.layout.simple_spinner_item);
                        break;
                    case "Back":
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.back_exercises_array,
                                android.R.layout.simple_spinner_item);
                        break;
                    case "Chest":
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.chest_exercises_array,
                                android.R.layout.simple_spinner_item);
                        break;
                    case "Arms":
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.arms_exercises_array,
                                android.R.layout.simple_spinner_item);
                        break;
                    case "Shoulders":
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.shoulder_exercises_array,
                                android.R.layout.simple_spinner_item);
                        break;
                    default:
                        adapter = ArrayAdapter.createFromResource(
                                getApplicationContext(),
                                R.array.non_chosen_array,
                                android.R.layout.simple_spinner_item);
                        break;
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spExercise.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapterExercise = ArrayAdapter.createFromResource(this,
                R.array.non_chosen_array, android.R.layout.simple_spinner_item);
        adapterExercise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spExercise.setAdapter(adapterExercise);
        spExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
                exercise = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btLog.setOnClickListener(view -> {
            float weight = (!etWeight.getText().toString().equals("")) ? Float.parseFloat(etWeight.getText().toString()) : -1;
            int reps = (!etReps.getText().toString().equals("")) ? Integer.parseInt(etReps.getText().toString()) : -1;
            int sets = (!etSets.getText().toString().equals("")) ? Integer.parseInt(etSets.getText().toString()) : -1;

            (new DataLogger(getApplicationContext())).logExerciseData(exercise, weight, reps, sets);

            Toast.makeText(this, "Nice work on the " + exercise + "!", Toast.LENGTH_SHORT).show();
        });
    }
}