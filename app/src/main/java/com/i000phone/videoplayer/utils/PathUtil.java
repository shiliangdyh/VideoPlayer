package com.i000phone.videoplayer.utils;

/**
 * Created by Administrator on 2016/3/30.
 */
public class PathUtil {
    public static final String W92 = "w92";
    public static final String W154 = "w154";
    public static final String W185 = "w185";
    public static final String W342 = "w342";
    public static final String W500 = "w500";
    public static final String W780 = "w780";
    public static final String ORIGINAL = "original";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String MOVIES_INFO_URL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=5e602b9e54aca236a602eb300dab1436&page=%d&language=%s";

    private static final String MOVIES_DETAIL_URL = "https://api.themoviedb.org/3/movie/%d?api_key=5e602b9e54aca236a602eb300dab1436&language=%s";

    public static String getMoviesInfoUrl(int page,String language){
        return String.format(MOVIES_INFO_URL,page,language);
    }

    public static String getImageUrl(String size,String relativePath){
        return BASE_URL.concat(size).concat(relativePath);
    }

    public static String getMoviesDetailUrl(int id,String language){
        return String.format(MOVIES_DETAIL_URL,id,language);
    }
}
