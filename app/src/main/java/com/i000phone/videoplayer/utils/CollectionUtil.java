package com.i000phone.videoplayer.utils;

import android.content.Context;
import android.os.Environment;

import com.i000phone.videoplayer.entities.Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/4/4.
 */
public class CollectionUtil {

    public static void saveMovie(Movie movie, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(movie.getId() + ".txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(movie);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMovie(Movie movie, Context context) {
        File file = new File("/data/data/"+context.getPackageName()+"/files/", movie.getId() + ".txt");
        if (file.exists()) {
            file.delete();
        }
    }

    public static List<Movie> getAllMovies(Context context){
        ArrayList<Movie> movies = new ArrayList<>();
        String[] strings = context.fileList();
        for (String s : strings) {
            try {
                FileInputStream fis = context.openFileInput(s);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Movie movie = (Movie) ois.readObject();
                movies.add(movie);
                ois.close();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return movies;
    }

    public static boolean isCollectioned(long id,Context context){
        String name = id+".txt";
        String[] strings = context.fileList();
        for (String s : strings) {
            if (name.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
