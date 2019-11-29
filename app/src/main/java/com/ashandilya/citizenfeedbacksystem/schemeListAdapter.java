package com.ashandilya.citizenfeedbacksystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class schemeListAdapter  extends RecyclerView.Adapter<schemeListAdapter.schemeListViewHolder>{

    private Context mCtx;
    private List<schemeList> schemeList;

    public schemeListAdapter(Context mCtx, List<schemeList> schemeList) {

        this.mCtx = mCtx;
        this.schemeList = schemeList;

    }

    @NonNull
    @Override
    public schemeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);
        return new schemeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull schemeListViewHolder holder, int position) {

        schemeList scheme = schemeList.get(position);

        holder.textViewName.setText(scheme.getScheme_Name());
        holder.textViewDesc.setText(scheme.getScheme_Description());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(scheme.getScheme_Image()));
//        Glide.with(mCtx)
//                .load(scheme.getScheme_Image())
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return schemeList.size();
    }

    class schemeListViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewName, textViewDesc;

        private  schemeListViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.schemeNameTV);
            textViewDesc = itemView.findViewById(R.id.schemeDescTV);

        }
    }
}
