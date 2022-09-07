package com.zvibadash.gymlogger;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataLogger {
    private final File dataFile;
    private final String dateFormat = "dd-MM-yyyy'@'HH:mm:ss";

    public DataLogger(Context context) {
        this.dataFile = new File(context.getFilesDir(), "gym-raw-data.txt");
    }

    private void logData(StringBuilder sb) {
        try {
            FileWriter fw = new FileWriter(dataFile, true);
            fw.append(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logEntranceData(int busyness) {
        StringBuilder sb = new StringBuilder();
        sb.append("[+] ");
        sb.append(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(new Date()));
        sb.append(",");
        sb.append(busyness);
        sb.append("\n");

        logData(sb);
    }
    public void logExerciseData(String muscleArea, String exercise, float weight, int reps, int sets) {
        StringBuilder sb = new StringBuilder();
        sb.append("[*] ");
        sb.append(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(new Date()));
        sb.append(",");
        sb.append(muscleArea);
        sb.append(",");
        sb.append(exercise);
        sb.append(",");
        sb.append(weight);
        sb.append(",");
        sb.append(reps);
        sb.append(",");
        sb.append(sets);
        sb.append("\n");

        logData(sb);
    }
    public void logExitData() {
        StringBuilder sb = new StringBuilder();
        sb.append("[-] ");
        sb.append(new SimpleDateFormat(dateFormat, Locale.getDefault()).format(new Date()));
        sb.append("\n");

        logData(sb);
    }
}
