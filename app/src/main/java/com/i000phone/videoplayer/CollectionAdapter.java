package com.i000phone.videoplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.i000phone.videoplayer.entities.Movie;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/4/3.
 */
public class CollectionAdapter extends BaseAdapter {
    private Context context;
    private List<Movie> list;

    public CollectionAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collection,parent,false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.collection_title);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.collection_img);
            convertView.setTag(viewHolder);
        }
        Movie movie = list.get(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.textView.setText(movie.getTitle());
        Picasso.with(context).load("http://image.tmdb.org/t/p/w342"+movie.getPoster_path()).into(vh.imageView);
        convertView.findViewById(R.id.play).setVisibility(View.VISIBLE);
        return convertView;
    }

    public static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
