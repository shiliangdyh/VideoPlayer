package com.i000phone.videoplayer;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.i000phone.videoplayer.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        DetailFragment detailFragment = DetailFragment.newInstance(id);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragment,detailFragment);
        transaction.commit();
    }

}
