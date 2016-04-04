package com.i000phone.videoplayer;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.i000phone.tools.network.NetworkLib;
import com.i000phone.tools.network.NetworkTask;
import com.i000phone.tools.viewutil.CommonAdapter;
import com.i000phone.videoplayer.entities.Movie;
import com.i000phone.videoplayer.entities.Response;
import com.i000phone.videoplayer.fragment.DetailFragment;
import com.i000phone.videoplayer.utils.MovieService;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NetworkTask.Callback<Response>, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private GridView gridView;
    private CommonAdapter<Movie> adapter;
    private MovieService service;
    private NetworkTask<Response> task;
    private int page = 1;
    private boolean isbottom;
    private FrameLayout container_detail;
    private boolean isHengping;
    private View loading;
    private boolean isLoading;
    private String l;
    private long exitTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading = findViewById(R.id.loading);
        gridView = ((GridView) findViewById(R.id.container_title));
        adapter = new CommonAdapter<Movie>(this, new ArrayList<Movie>(), Movie.class, R.layout.item_layout);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        gridView.setOnScrollListener(this);
        service = NetworkLib.getService(MovieService.class);
        String language = Locale.getDefault().toString();
        if (language.contains("zh")) {
            l = "zh";
        }else if(language.contains("en")){
            l = "en";
        }else if (language.contains("ja")){
            l = "ko";
        }
        task = service.getMovieList(page++, l);
        task.execute(this);
        container_detail = (FrameLayout) findViewById(R.id.container_detail);
        if (container_detail != null) {
            isHengping = true;
        } else {
            isHengping = false;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void doResponse(Response response) {
        loading.setVisibility(View.GONE);
        adapter.addAll(response.getResults());
        isLoading = false;
        if (isHengping) {
            long id = ((Movie) adapter.getItem(0)).getId();
            loadHMovieDetail(id);
            isHengping = false;
        }
    }

    @Override
    public void doFailure(Exception e) {
        Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = (Movie) adapter.getItem(position);
        long id1 = movie.getId();
        if (container_detail == null) {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", id1);
            startActivity(intent);
        } else {
            loadHMovieDetail(id1);
        }
    }

    private void loadHMovieDetail(long id1) {
        DetailFragment detailFragment = DetailFragment.newInstance(id1);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_detail, detailFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_collection:
                startActivity(new Intent(this,CollectionActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isbottom && !isLoading) {
            loading.setVisibility(View.VISIBLE);
            isLoading = true;
            task = service.getMovieList(page++, l);
            task.execute(this);
        } else {
            isbottom = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isbottom = true;
        } else {
            isbottom = false;
        }
    }
}
