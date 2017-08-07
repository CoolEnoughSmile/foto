package com.coolenoughsmile.foto.liftRecieveActivity.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coolenoughsmile.foto.R;
import com.coolenoughsmile.foto.liftActivity.model.Order;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by CoolEnoughSmile on 2017/8/5.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    private List<Order> list;
    private Context context;
    private LayoutInflater layoutInflater;
    public OrderAdapter(List<Order> list,Context context){
        this.list=list;
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.order_item,parent,false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.logo.setImageURI(list.get(position).getLogo());
        viewHolder.departure.setText(list.get(position).getDeparture());
        viewHolder.destination.setText(list.get(position).getDestination());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView logo;
        TextView departure,destination;
        public MyViewHolder(View itemView) {
            super(itemView);
            logo= (SimpleDraweeView) itemView.findViewById(R.id.order_logo);
            departure= (TextView) itemView.findViewById(R.id.departure_tv);
            destination= (TextView) itemView.findViewById(R.id.destination_tv);
        }
    }
}
