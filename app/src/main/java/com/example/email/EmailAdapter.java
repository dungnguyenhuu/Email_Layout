package com.example.email;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<EmailModel> modelList;
    List<EmailModel> filterList;

    public EmailAdapter(List<EmailModel> modelList) {
        this.modelList = modelList;
        this.filterList = new ArrayList<>(modelList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new EmailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmailViewHolder viewHolder = (EmailViewHolder) holder;
        EmailModel item = modelList.get(position);

        viewHolder.txtLetter.setText(item.getName().substring(0,1));
        Drawable background = viewHolder.txtLetter.getBackground();
        background.setColorFilter(new PorterDuffColorFilter(item.getColor(), PorterDuff.Mode.SRC_ATOP));
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtSubject.setText(item.getSubject());
        viewHolder.txtContent.setText(item.getContent());
        viewHolder.txtTime.setText(item.getTime());
        if(item.isFavorite()){
            viewHolder.imgFavorite.setImageResource(R.drawable.img_favorite);
        }else {
            viewHolder.imgFavorite.setImageResource(R.drawable.img_normal);
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EmailModel> filteredList = new ArrayList<>();
            String charSequence = constraint.toString();
            if(charSequence.isEmpty()){
                filteredList.addAll(filterList);
            }else{
                for(EmailModel e : filterList){
                    if(e.getName().toLowerCase().contains(charSequence.toLowerCase())
                        || e.getSubject().toLowerCase().contains(charSequence.toLowerCase())
                        || e.getContent().toLowerCase().contains(charSequence.toLowerCase())){
                        filteredList.add(e);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelList.clear();
            modelList.addAll((Collection<? extends EmailModel>) results.values);
            notifyDataSetChanged();
        }
    };

    class EmailViewHolder extends RecyclerView.ViewHolder{

        TextView txtLetter;
        TextView txtName;
        TextView txtSubject;
        TextView txtContent;
        TextView txtTime;
        ImageView imgFavorite;

        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLetter = itemView.findViewById(R.id.txtAvatar);
            txtName = itemView.findViewById(R.id.txtName);
            txtSubject = itemView.findViewById(R.id.txtTitle);
            txtContent= itemView.findViewById(R.id.txtDescriptions);
            txtTime = itemView.findViewById(R.id.txtTime);
            imgFavorite = itemView.findViewById(R.id.imgFavorite);

            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isFavorite = modelList.get(getAdapterPosition()).isFavorite();
                    modelList.get(getAdapterPosition()).setFavorite(!isFavorite);
                    notifyDataSetChanged();
                }
            });
        }
    }
}



