package com.infinitydeltax.a20hours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InProgressSkills extends AppCompatActivity {

    //ArrayList<String> itemData = new ArrayList<>();
    public static final String TAG = "20hours";
    ListView listview;
    ArrayList<Task> tasks = new ArrayList<Task>();
    AdapterTask adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_progress_skills);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listview = (ListView) findViewById(R.id.skillListView);

        //tasks.add(new Task("First Task", 120, "A very important task"));
        adapter = new AdapterTask(this, android.R.layout.simple_list_item_1, tasks);
        listview.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InProgressSkills.this, NewTask.class);
                startActivityForResult(intent, 1);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Snackbar.make(view, ((Task) parent.getItemAtPosition(position)).getDescription(), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                Intent i = new Intent(InProgressSkills.this, ProgressActivity.class);
                i.putExtra(ProgressActivity.ACTIVE_TASK, (Task) parent.getItemAtPosition(position));
                startActivity(i);
                //overridePendingTransition(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
                overridePendingTransition(R.anim.enter,R.anim.exit);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(openFileOutput("tasks", MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(Task a : tasks){
            String temp = a.getName() + "!!" + a.getTimeRemaining() + "!!" + a.getDescription();
            Log.v(TAG, temp);
            pw.println(temp);
        }

        pw.close();
        tasks.clear();

    }

    @Override
    public void onStart() {
        super.onStart();
        Scanner in = null;
        try {
            in = new Scanner(openFileInput("tasks"));
            while(in.hasNextLine()){
                String current = in.nextLine();
                String[] split = current.split("!!");
                if(split.length < 3){
                    Log.e(TAG, current);
                    continue;
                }
                tasks.add(new Task(split[0], Integer.parseInt(split[1]), split[2]));
            }
            adapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==1 && resultCode== Activity.RESULT_OK){
            tasks.add(new Task(data.getStringExtra(NewTask.TASK_NAME), 100, data.getStringExtra(NewTask.TASK_DESC)));
            adapter.notifyDataSetChanged();
            listview.invalidateViews();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_progress_skills, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_delete_all) {
            tasks.clear();
            adapter.notifyDataSetChanged();
            listview.invalidateViews();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
