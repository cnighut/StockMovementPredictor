package com.example.qawbecrdteyf.st_war;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel>{

    private ArrayList<DataModel> dataSet;
    Context mContext;
    private View.OnClickListener updButtonClickListener;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        Button updbutton;
        //TextView txtVersion;
        ImageView info1;
        ImageView info2;
        ImageView info3;
        ImageView info4;
        ImageView info5;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    //@Override
    /*public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }*/

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.data);
            //viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.index);
            viewHolder.updbutton = (Button) convertView.findViewById(R.id.update);
            viewHolder.info1 = (ImageView) convertView.findViewById(R.id.item_info1);
            viewHolder.info2 = (ImageView) convertView.findViewById(R.id.item_info2);
            viewHolder.info3 = (ImageView) convertView.findViewById(R.id.item_info3);
            viewHolder.info4 = (ImageView) convertView.findViewById(R.id.item_info4);
            viewHolder.info5 = (ImageView) convertView.findViewById(R.id.item_info5);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;

        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getData());
       // viewHolder.txtVersion.setText(dataModel.getVersion_number());
        //viewHolder.info.setOnClickListener(this);
        viewHolder.info1.setTag(position);
        viewHolder.info2.setTag(position);
        viewHolder.info3.setTag(position);
        viewHolder.info4.setTag(position);
        viewHolder.info5.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}