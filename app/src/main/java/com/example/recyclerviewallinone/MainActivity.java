package com.example.recyclerviewallinone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Toolbar toolbar;
    SearchView searchView;
    ArrayList<BaseModelClass> arrayList = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};
    RecyclerviewMaker recyclerviewMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        toolbar = findViewById(R.id.toolbar);

        recyclerviewMaker = RecyclerviewMaker.getInstance(this, recyclerView,
                new MyAdapter(this, getData()),
                arrayList, BaseModelClass.MODEL_CLASS);

        recyclerviewMaker.setup(LinearLayoutManager.VERTICAL)
                .setSwipeToDeleteItem()
                .setSwipeRefresh(swipeRefreshLayout)
                .setDragDropItem();
    }

    private ArrayList<BaseModelClass> getData() {
        ModelClass modelClass;
        for (int i = 0; i < data.length; i++) {
            modelClass = new ModelClass(data[i]);
            modelClass.setRowSelected(false);
            arrayList.add(modelClass);
        }
        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        recyclerviewMaker.setupSearchView(menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        closeSearchViewOnBackPress();
        super.onBackPressed();
    }

    private void closeSearchViewOnBackPress() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
    }
}