package com.example.email;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.bloco.faker.Faker;

public class EmailAdapter extends BaseAdapter {


    Faker faker = new Faker();

    List<EmailModel> emailModelList;

    public EmailAdapter(List<EmailModel> emailModelList) {
        this.emailModelList = emailModelList;
    }

    @Override
    public int getCount() {
        return emailModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return emailModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

       if(convertView == null){
           convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            viewHolder = new ViewHolder();

           viewHolder.txtName = convertView.findViewById(R.id.txtName);
           viewHolder.txtTitle = convertView.findViewById(R.id.txtTitle);
           viewHolder.txtDescription  = convertView.findViewById(R.id.txtDescriptions);
           viewHolder.txtTime = convertView.findViewById(R.id.txtTime);
           viewHolder.txtAvatar =  convertView.findViewById(R.id.txtAvatar);
           viewHolder.imgFavorite = convertView.findViewById(R.id.imgFavorite);
           viewHolder.imgBackground = convertView.findViewById(R.id.imgBackground);

           convertView.setTag(viewHolder);
       }else
           viewHolder = (ViewHolder) convertView.getTag();

       EmailModel emailModel =  emailModelList.get(position);

       viewHolder.txtName.setText(emailModel.getName());
       viewHolder.txtTitle.setText(emailModel.getTitle());
       viewHolder.txtDescription.setText(emailModel.getDescriptions());
       viewHolder.txtTime.setText(emailModel.getTime());
       viewHolder.txtAvatar.setText(emailModel.getName().substring(0,1));


       viewHolder.imgBackground.setColorFilter(Color.parseColor(faker.color.hexColor()));

       if(emailModel.isSelected())
           viewHolder.imgFavorite.setImageResource(R.drawable.img_favorite);
       else
           viewHolder.imgFavorite.setImageResource(R.drawable.img_normal);

       viewHolder.imgFavorite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isSelected = emailModelList.get(position).isSelected();
               emailModelList.get(position).setSelected(!isSelected);
               notifyDataSetChanged();
           }
       });
        return convertView;
    }

    class ViewHolder{
        TextView txtName;
        TextView txtTitle;
        TextView txtDescription;
        TextView txtTime;
        TextView txtAvatar;
        ImageView imgFavorite;
        ImageView imgBackground;
    }
}
