package com.dzy.rcyviewbaseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dzy.baseadapterlib.CommonAdapter;
import com.dzy.baseadapterlib.ContentHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


    CommonAdapter mAdapter;
    RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        re = (RecyclerView) findViewById(R.id.review);

        re.setLayoutManager(new LinearLayoutManager(this));

        final List<String> list = new ArrayList<>();
        for(int i = 0 ;i<15;i++)
        {
            list.add(""+i);
        }

         mAdapter = new CommonAdapter<String>(list,R.layout.item_layout) {
            @Override
            public void bindView(ContentHolder holder, int position, String item)
            {
                holder.setText(R.id.tvTitle,"this is title "+position);
            }
        };

        mAdapter.setLoadMoreListener(new CommonAdapter.LoadMoreListener() {
            @Override
            public void loadMore()
            {
                re.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        for(int i = 15; i < 25; i++)
                        {
                            list.add(""+i);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },2000);
            }
        });

        mAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position)
            {
                Log.d("tag","onClick "+position);
            }
        });

        mAdapter.setEmptyLayoutId(R.layout.empty_view);
        mAdapter.setErrorLayoutId(R.layout.error_view);
        mAdapter.setLoadMoreLayoutId(R.layout.load_more);
        re.setAdapter(mAdapter);

    }

    public void EmptyClick(View v)
    {
        //Log.d("tag","parent height" + re.getHeight());
        mAdapter.showEmptyView();
    }

    public void ErrorClick(View v)
    {
        mAdapter.showErrorView();
    }
    public void NormalClick(View v)
    {
        mAdapter.showDataContent();
    }
}
