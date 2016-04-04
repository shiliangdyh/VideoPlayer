package com.i000phone.videoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.i000phone.videoplayer.entities.Movie;
import com.i000phone.videoplayer.utils.CollectionUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private ListView listView;
    private CollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        listView = ((ListView) findViewById(R.id.collection_list));
        List<Movie> allMovies = CollectionUtil.getAllMovies(this);
        adapter = new CollectionAdapter(this, allMovies);
        listView.setAdapter(adapter);
    }
}
