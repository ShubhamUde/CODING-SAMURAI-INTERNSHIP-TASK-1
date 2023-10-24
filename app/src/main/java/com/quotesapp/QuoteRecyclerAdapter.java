package com.quotesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuoteRecyclerAdapter extends RecyclerView.Adapter<QuoteViewHolder> {

    Context context;
    List<QuoteResponse> list;
    CopyListener listener;

    public QuoteRecyclerAdapter(Context context, List<QuoteResponse> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuoteViewHolder(LayoutInflater.from(context).inflate(R.layout.lis_quote, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteViewHolder holder, int position) {
        holder.text_quote.setText(list.get(position).getText());

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent txtIntent = new Intent(android.content.Intent.ACTION_SEND);
                txtIntent .setType("text/plain");
                txtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(txtIntent);
            }
        });

        holder.checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.checkBtn.isChecked()){
                    Toast.makeText(context, "Quote add to Favorites", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Quote remove from favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class QuoteViewHolder extends RecyclerView.ViewHolder {

    TextView text_quote;
    CheckBox checkBtn;
    ImageView btn_share;

    public QuoteViewHolder(@NonNull View itemView) {
        super(itemView);
        text_quote = itemView.findViewById(R.id.txt_quote);
        checkBtn = itemView.findViewById(R.id.checkBtn);
        btn_share = itemView.findViewById(R.id.btnShare);
    }
}


