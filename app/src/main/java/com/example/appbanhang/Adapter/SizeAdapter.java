package com.example.appbanhang.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhang.R;
import com.example.appbanhang.model.Parameter;
import com.example.appbanhang.service.OnItemClickParameter;
import com.example.appbanhang.service.SelectionEvent;
import com.example.appbanhang.service.SumEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder>  {

    private List<Parameter> parameters;
    public static int selection ;
    private OnItemClickParameter itemClickListener;
    public void setData(List<Parameter> parameters, OnItemClickParameter itemClickListener){
        this.parameters = parameters;
        this.itemClickListener =itemClickListener;
        selection = -1;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SizeAdapter.SizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.size_item,parent,false);
        return new SizeAdapter.SizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeAdapter.SizeViewHolder holder, int position) {
            Parameter parameter = parameters.get(position);
            int j = position;
            if (parameter == null)
                 return;
            holder.textView1.setText(parameter.getKich_thuoc());
            holder.textView2.setText("Kho: "+parameter.getCon_lai());
            if(selection != j){
                holder.textView1.setBackgroundResource(R.color.white);
            }
            else  holder.textView1.setBackgroundResource(R.color.custom);
            holder.textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(selection != j){
                        selection = j;
                        EventBus.getDefault().postSticky(new SelectionEvent());
                        notifyDataSetChanged();
                    }
                    else if(selection == j){
                        selection = -1;
                        notifyDataSetChanged();
                        EventBus.getDefault().postSticky(new SelectionEvent());
                    }
                }
            });

    }


    @Override
    public int getItemCount() {
        if(parameters != null) return parameters.size();
        return 0;
    }

    public class SizeViewHolder extends  RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;

        public SizeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textViewSize);
            textView2 = itemView.findViewById(R.id.soluong3);

        }
    }

}
