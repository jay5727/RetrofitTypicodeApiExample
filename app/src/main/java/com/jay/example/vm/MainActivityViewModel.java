package com.jay.example.vm;


import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jay.example.model.Todo;
import com.jay.example.repos.TodoRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MainActivityViewModel";

    public ObservableBoolean isLoading = new ObservableBoolean(false);

    /* Needs to be public for Databinding */
    public void onRefresh() {
        isLoading.set(true);
        TodoRepository repository = TodoRepository.getInstance();
        todoLiveData = repository.getTodos();
        isLoading.set(false);
    }

    //private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private LiveData<List<Todo>> todoLiveData;

    public MainActivityViewModel() {
        super();
        onRefresh();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

//    public MutableLiveData<Boolean> getIsLoading() {
//        return isLoading;
//    }

    public LiveData<List<Todo>> getTodoLiveData() {
        //isLoading.set(false);
        return todoLiveData;
    }
}
