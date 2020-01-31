package com.loveinshayari.quize;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriAdaptor extends RecyclerView.Adapter<CategoriAdaptor.ViewHolder> {

    private List<CategoriModel> categoriModelList;

    public CategoriAdaptor(List<CategoriModel> categoriModelList) {
        this.categoriModelList = categoriModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categori_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(categoriModelList.get(position).getUrl(),categoriModelList.get(position).getName(),categoriModelList.get(position).getSets());
    }

    @Override
    public int getItemCount() {
        return categoriModelList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.caImageView);
            title = itemView.findViewById(R.id.caTitle);
        }
        private void setData(String url, final String title, final int sets){
            Glide.with(itemView.getContext()).load(url).into(circleImageView);
            this.title.setText(title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent setIntent = new Intent(itemView.getContext(),SetsActivity.class);
                    setIntent.putExtra("title",title);
                    setIntent.putExtra("sets",sets);
                    itemView.getContext().startActivity(setIntent);
                }
            });
        }
    }

}
