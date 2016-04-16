package com.infinitydeltax.a20hours;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ToggleButton;

public class ProgressActivity extends AppCompatActivity {

    private Task task;
    private boolean counting = false;
    long counterStarted = 0;

    public static String ACTIVE_TASK = "com.infinitydeltax.a20hours.ProgressingTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        task = (Task) getIntent().getSerializableExtra(ACTIVE_TASK);

        ProgressView progressView = (ProgressView) findViewById(R.id.progressView);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeRemaining();
                counting = !counting;
            }
        });
    }

    void updateTimeRemaining(){
        if(counting) {
            task.setTimeRemaining(task.getTimeRemaining() + System.currentTimeMillis() - counterStarted);
        }
        counterStarted = System.currentTimeMillis();
    }

    long getTimeSoFar(){
        updateTimeRemaining();
        return task.getTimeRemaining();
    }
}
