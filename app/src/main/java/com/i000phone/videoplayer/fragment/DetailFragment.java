package com.i000phone.videoplayer.fragment;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.i000phone.tools.network.NetworkLib;
import com.i000phone.tools.network.NetworkTask;
import com.i000phone.videoplayer.R;
import com.i000phone.videoplayer.entities.Belong;
import com.i000phone.videoplayer.entities.Category;
import com.i000phone.videoplayer.entities.Country;
import com.i000phone.videoplayer.entities.Movie;
import com.i000phone.videoplayer.utils.CollectionUtil;
import com.i000phone.videoplayer.utils.MovieService;
import com.i000phone.videoplayer.utils.MyImageView;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements NetworkTask.Callback<Movie>, CompoundButton.OnCheckedChangeListener{
    private View view;
    private Movie movie;
    private String l;
    public DetailFragment() {
    }

    public static DetailFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong("id",id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String language = Locale.getDefault().toString();
        if (language.contains("zh")) {
            l = "zh";
        }else if(language.contains("en")){
            l = "en";
        }else if (language.contains("ja")){
            l = "ja";
        }
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.view = view;
        long id = getArguments().getLong("id", -1);
        MovieService service = NetworkLib.getService(MovieService.class);
        NetworkTask<Movie> task = service.getMovie(id, l);
        task.execute(this);
    }

    @Override
    public void doResponse(Movie movie) {
        this.movie = movie;
        MyImageView detail_img = (MyImageView) view.findViewById(R.id.detail_img);
        String poster_path = movie.getPoster_path();
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w342" + poster_path)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.loading).into(detail_img);
        String title = movie.getTitle();
        TextView detail_title = (TextView) view.findViewById(R.id.detail_title);
        detail_title.setText(title);
        Belong belongs_to_collection = movie.getBelongs_to_collection();
        if (belongs_to_collection!=null) {
            String belong = belongs_to_collection.getName();
            TextView detail_belong = (TextView) view.findViewById(R.id.detail_belong);
            detail_belong.setText(belong);
        }
        List<Category> genres = movie.getGenres();
        StringBuilder sb = new StringBuilder();
        for (Category category : genres) {
            sb.append(category.getName()).append(" ");
        }
        TextView detail_category = (TextView) view.findViewById(R.id.detail_category);
        detail_category.setText(sb.toString());
        double vote_average = movie.getVote_average();
        TextView detail_vote_avg = (TextView) view.findViewById(R.id.detail_vote_avg);
        detail_vote_avg.setText("评分：" + vote_average);
        int vote_count = movie.getVote_count();
        TextView detail_vote_count = (TextView) view.findViewById(R.id.detail_vote_count);
        detail_vote_count.setText(vote_count + "人评");

        List<Country> production_countries = movie.getProduction_countries();
        StringBuilder builder = new StringBuilder("产点：");
        for (Country country : production_countries) {
            builder.append(country.getName()).append(" ");
        }
        TextView detail_country = (TextView) view.findViewById(R.id.detail_country);
        detail_country.setText(builder.toString());

        String original_language = movie.getOriginal_language();
        TextView detail_language = (TextView) view.findViewById(R.id.detail_language);
        detail_language.setText("语言："+original_language);

//        List<Company> production_companies = movie.getProduction_companies();
//        StringBuilder sb1 = new StringBuilder("出品：");
//        for (Company company : production_companies) {
//            sb1.append(company.getName()).append(" ");
//        }
//        TextView detail_company = (TextView) findViewById(R.id.detail_company);
//        detail_company.setText(sb1.toString());

        String release_date = movie.getRelease_date();
        TextView detail_time = (TextView) view.findViewById(R.id.detail_time);
        detail_time.setText(release_date);

        String overview = movie.getOverview();
        TextView detail_overview = (TextView) view.findViewById(R.id.detail_override);
        detail_overview.setText(overview);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.collection);
        checkBox.setVisibility(View.VISIBLE);
        boolean isCollectioned = CollectionUtil.isCollectioned(movie.getId(), getContext());
        if (isCollectioned) {
            checkBox.setChecked(true);
            checkBox.setTextColor(Color.RED);
        }else{
            checkBox.setChecked(false);
            checkBox.setTextColor(Color.BLACK);
        }
        checkBox.setOnCheckedChangeListener(this);
        view.findViewById(R.id.bar).setVisibility(View.VISIBLE);
        view.findViewById(R.id.play).setVisibility(View.VISIBLE);
    }

    @Override
    public void doFailure(Exception e) {
        Toast.makeText(getContext(), "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            buttonView.setTextColor(Color.RED);
            CollectionUtil.saveMovie(movie, getContext());
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
        }else{
            buttonView.setTextColor(Color.BLACK);
            CollectionUtil.deleteMovie(movie, getContext());
            Toast.makeText(getContext(), "取消收藏", Toast.LENGTH_SHORT).show();
        }
    }
}
