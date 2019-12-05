package com.example.newhomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private List<Integer> list = new ArrayList<>(4);
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;


    private int iconId[]={R.drawable.aa,R.drawable.bb,R.drawable.cc,R.drawable.dd,R.drawable.ee,R.drawable.ff};
    private String nameList[]={"十股糖仁文創園區","國家歌劇院","傳統藝術中心","清水斷崖","奇美博物館","六福村"};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.Gridrecycler);
        layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        GridAdapter gridAdapter = new GridAdapter(MainActivity.this,iconId,nameList);
        recyclerView.setAdapter(gridAdapter);


        //存入圖片
        list.add(R.drawable.b1);
        list.add(R.drawable.b2);
        list.add(R.drawable.b3);
        list.add(R.drawable.b4);

        BannerAdapter adapter = new BannerAdapter(this,list);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final SmoothLinearLayoutManager layoutManager = new SmoothLinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(list.size()*10);

        //讓圖片一頁一頁滑過去
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


//加入自動輪播
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition()+1);
            }
        },2000,2000, TimeUnit.MILLISECONDS);


    }
}
