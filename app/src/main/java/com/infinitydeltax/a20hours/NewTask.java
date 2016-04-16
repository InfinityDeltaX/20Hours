package com.infinitydeltax.a20hours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTask extends AppCompatActivity {

    static String TASK_NAME = "com.infinitydeltax.a20hours.newTaskName";
    static String TASK_DESC = "com.infinitydeltax.a20hours.newTaskDesc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final EditText nameEditText = (EditText) findViewById(R.id.nameField);
        final EditText descEditText = (EditText) findViewById(R.id.descField);
        final Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra(TASK_NAME, nameEditText.getText().toString());
                intent.putExtra(TASK_DESC, descEditText.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
