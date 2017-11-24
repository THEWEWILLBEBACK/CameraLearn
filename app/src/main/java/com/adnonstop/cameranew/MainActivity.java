package com.adnonstop.cameranew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.adnonstop.cameranew.adapter.HomeAdapter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeAdapter.ItemClickListener {

    @BindView(R.id.home_recycle)
    RecyclerView mHomeRecycle;
    private HomeAdapter mAdapter;
    private HashMap<String, Class<? extends Activity>> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecycle();
        initMap();
    }

    private void initMap() {
        map.put("绘图", DrawViewActivity.class);
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHomeRecycle.setLayoutManager(layoutManager);
        mAdapter = new HomeAdapter(this);
        mHomeRecycle.setAdapter(mAdapter);
        mAdapter.setItemClickListener(this);
    }

    @Override
    public void onItemClickListener(int position, String type) {
        Class<? extends Activity> aClass = map.get(type);
        if (aClass != null) {
            startActivity(new Intent(this, aClass));
        }
    }
}
