package com.i000phone.videoplayer.entities;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.i000phone.tools.viewutil.BindView;
import com.i000phone.videoplayer.R;
import com.i000phone.videoplayer.utils.MyImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/4/1.
 */
public class Movie implements Serializable{
    private Belong belongs_to_collection;
    private long budget;
    private List<Category> genres;
    private String homepage;
    private String imdb_id;
    private List<Company> production_companies;
    private List<Country> production_countries;
    private long revenue;
    private int runtime;
    private List<Language> spoken_languages;
    private String status;
    private String tagline;
    @BindView(resId = R.id.item_img,adapterMethod = "loadImage",viewType = MyImageView.class)
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
    private long id;
    private String original_title;
    private String original_language;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    @BindView(resId = R.id.item_title,bindMethod = "setText",bindType = CharSequence.class)
    private String title;
    private double vote_average;

    public Belong getBelongs_to_collection() {
        return belongs_to_collection;
    }

    public void setBelongs_to_collection(Belong belongs_to_collection) {
        this.belongs_to_collection = belongs_to_collection;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public List<Category> getGenres() {
        return genres;
    }

    public void setGenres(List<Category> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public List<Company> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<Company> production_companies) {
        this.production_companies = production_companies;
    }

    public List<Country> getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(List<Country> production_countries) {
        this.production_countries = production_countries;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Language> getSpoken_languages() {
        return spoken_languages;
    }

    public void setSpoken_languages(List<Language> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public static void loadImage(MyImageView view,String img){
        Picasso.with(view.getContext())
                .load("http://image.tmdb.org/t/p/w185" + img)
                .config(Bitmap.Config.RGB_565)
                .placeholder(R.drawable.loading)
                .into(view);
//        view.setImageResource(R.drawable.loading );
    }
}
