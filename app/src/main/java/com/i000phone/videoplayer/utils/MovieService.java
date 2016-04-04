package com.i000phone.videoplayer.utils;

import com.i000phone.tools.network.NetworkTask;
import com.i000phone.tools.network.UrlString;
import com.i000phone.videoplayer.entities.Movie;
import com.i000phone.videoplayer.entities.Response;

/**
 * Created by Administrator on 2016/4/1.
 */
public interface MovieService {
    @UrlString("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=5e602b9e54aca236a602eb300dab1436&page=%d&language=%s")
    NetworkTask<Response> getMovieList(int page,String  language);
    @UrlString("https://api.themoviedb.org/3/movie/%d?api_key=5e602b9e54aca236a602eb300dab1436&language=%s")
    NetworkTask<Movie> getMovie(long id,String language);

}
