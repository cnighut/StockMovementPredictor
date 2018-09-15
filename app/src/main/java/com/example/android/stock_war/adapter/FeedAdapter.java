package com.example.android.stock_war.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.android.stock_war.R;
import com.example.android.stock_war.model.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private Context mContext;
    private List<Feed> feedList;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView company, category, open, close, low, high;
        public ImageView compLogo, day_one, day_two, day_three, day_four, day_five;
        public MyViewHolder(View view){
            super(view);
            company = (TextView)view.findViewById(R.id.company);
            category = (TextView) view.findViewById(R.id.category);
            open = (TextView) view.findViewById(R.id.open);
            close = (TextView) view.findViewById(R.id.close);
            low = (TextView) view.findViewById(R.id.low);
            high = (TextView) view.findViewById(R.id.high);
            compLogo = (ImageView) view.findViewById(R.id.companyImage);
            day_one = (ImageView) view.findViewById(R.id.day_one);
            day_two = (ImageView) view.findViewById(R.id.day_two);
            day_three = (ImageView) view.findViewById(R.id.day_three);
            day_four = (ImageView) view.findViewById(R.id.day_four);
            day_five = (ImageView) view.findViewById(R.id.day_five);
        }
    }
    public FeedAdapter(Context mContext, List<Feed> feedList){
        this.mContext = mContext;
        this.feedList = feedList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public  void onBindViewHolder(MyViewHolder holder, int position){
        final Feed feed = feedList.get(position);
        holder.company.setText(feed.getCompany());
        holder.category.setText(feed.getCategory());
        holder.open.setText(feed.getOpen());
        holder.close.setText(feed.getClose());
        holder.low.setText(feed.getLow());
        holder.high.setText(feed.getHigh());
        Glide.with(mContext).load(feed.getImgUrl())
                .override(500, 500).fitCenter()
                .priority(Priority.LOW).into(holder.compLogo);
        if(feed.getDayOne()==1){
            holder.day_one.setImageResource(R.drawable.up_arrow);
        }else{
            holder.day_one.setImageResource(R.drawable.down_arrow);
        }
        if(feed.getDayTwo()==1){
            holder.day_two.setImageResource(R.drawable.up_arrow);
        }else{
            holder.day_two.setImageResource(R.drawable.down_arrow);
        }
        if(feed.getDayThree()==1){
            holder.day_three.setImageResource(R.drawable.up_arrow);
        }else{
            holder.day_three.setImageResource(R.drawable.down_arrow);
        }
        if(feed.getDayFour()==1){
            holder.day_four.setImageResource(R.drawable.up_arrow);
        }else{
            holder.day_four.setImageResource(R.drawable.down_arrow);
        }
        if(feed.getDayFive()==1){
            holder.day_five.setImageResource(R.drawable.up_arrow);
        }else{
            holder.day_five.setImageResource(R.drawable.down_arrow);
        }
    }

    @Override
    public int getItemCount(){
        return feedList.size();
    }
    @Override
    public long getItemId(int position){
        return super.getItemId(position);
    }
}
