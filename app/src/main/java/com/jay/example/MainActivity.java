package com.jay.example;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jay.example.adapter.TodoAdapter;
import com.jay.example.databinding.ActivityMainBinding;
import com.jay.example.model.Todo;
import com.jay.example.vm.MainActivityViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainActivityViewModel mViewModel;

    RecyclerView todoListRecyclerView;
    //ProgressBar loadingIndicator;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        activityMainBinding.setViewModel(mViewModel);

        todoListRecyclerView = findViewById(R.id.todo_recycler_view);
        //loadingIndicator = findViewById(R.id.todo_loading_indicator);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.app_name));

//        mViewModel.getIsLoading().observe(this, (Boolean isLoading) -> {
//            if (isLoading) loadingIndicator.setVisibility(View.VISIBLE);
//            else loadingIndicator.setVisibility(View.GONE);
//        });
//
        mViewModel.getTodoLiveData().observe(this, this::initRecyclerView);
    }

    private void initRecyclerView(List<Todo> todos) {
        TodoAdapter adapter = new TodoAdapter(this, todos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        todoListRecyclerView.setLayoutManager(layoutManager);
        todoListRecyclerView.setAdapter(adapter);
    }
}
