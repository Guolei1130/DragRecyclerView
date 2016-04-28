package com.gl.dragrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;

import com.gl.base.CallbackWrap;
import com.gl.base.OnTouchListener;
import com.gl.base.RecyclerAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button list,grid;
    RecyclerView recyclerView;

    ArrayList<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        bindData();
    }

    private void bindData() {
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        RecyclerAdapter adapter = new RecyclerAdapter(this,mData);
        recyclerView.setAdapter(adapter);
        final ItemTouchHelper helper = new ItemTouchHelper(new CallbackWrap(adapter));
        helper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(new OnTouchListener(recyclerView) {
            @Override
            public void onLongClick(RecyclerView.ViewHolder vh) {
                if (vh.getLayoutPosition()!=mData.size()-1) {
                    helper.startDrag(vh);
                }
            }
        });
    }


    private void initView() {
        list = (Button) findViewById(R.id.list);
        grid = (Button) findViewById(R.id.grid);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

    }

    private void initData() {

        if (null == mData){
            mData = new ArrayList<String>();
        }else {
            mData.clear();
        }
        for (int i = 0; i < 15; i++) {
            mData.add("this"+i);
        }
    }

}
