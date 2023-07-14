package com.example.slide3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class photoAdapter extends RecyclerView.Adapter<photoAdapter.phottolistViewHolder> {
private Context context ;
    private Picasso mPicasso;
private ArrayList<photo>  photos ;
    public photoAdapter(List<photo> objects) {
        photos = (ArrayList<photo>) objects;
        mPicasso = Picasso.get();
    }

    public photoAdapter(Context context, ArrayList<photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public phottolistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview , parent , false);
        return new phottolistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull phottolistViewHolder holder, int position) {
            photo item =photos.get(position);
            if(item==null){
                return;
            }

            holder.textid.setText(String.valueOf(item.getId()));
            holder.texttitle.setText(item.getTitle());
        photo objects = photos.get(position);
        mPicasso.load(objects.getUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if (photos != null){
            return photos.size();
        }
        return 0;
    }

    public class phottolistViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView ;
        private TextView textid, texttitle;
        public phottolistViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgphoto);
            textid = itemView.findViewById(R.id.txtid);
            texttitle = itemView.findViewById(R.id.txttitle);
        }
    }
}
