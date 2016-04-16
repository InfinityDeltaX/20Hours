package com.infinitydeltax.a20hours;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Robert on 4/16/2016.
 */
public class AdapterTask extends ArrayAdapter<Task> {

    private Activity activity;
    private ArrayList<Task> tasks;
    private static LayoutInflater inflater = null;

    public AdapterTask(Activity activity, int resource, ArrayList<Task> tasks) {
        super(activity, resource, tasks);
        try{
            this.activity = activity;
            this.tasks = tasks;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ViewHolder{
        public TextView display_name;
        public TextView display_remaining;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder holder;
        try{
            if(convertView == null){
                convertView = inflater.inflate(R.layout.sample_task_list_view, null);
                holder = new ViewHolder();

                holder.display_name = (TextView)  convertView.findViewById(R.id.display_name);
                holder.display_remaining = (TextView)  convertView.findViewById(R.id.display_remaining);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.display_name.setText(tasks.get(position).getName());
            holder.display_remaining.setText(tasks.get(position).getTimeRemaining() + "");
        } catch (Exception e){

        }
        return convertView;
    }
}
