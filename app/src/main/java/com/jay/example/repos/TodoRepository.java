package com.jay.example.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jay.example.api.TodoApi;
import com.jay.example.model.Todo;
import com.jay.example.util.ApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository {
    private static final String TAG = "TodoRepository";
    private static final TodoRepository ourInstance = new TodoRepository();
    private TodoApi api;

    private MutableLiveData<List<Todo>> todoListLiveData = new MutableLiveData<>();
    private MutableLiveData<Todo> todoLiveData = new MutableLiveData<>();

    public static TodoRepository getInstance() {
        return ourInstance;
    }

    private TodoRepository() {
        api = ApiBuilder.create(TodoApi.class);
    }

    public LiveData<List<Todo>> getTodos() {
        api.getTodos().enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                todoListLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: failed to fetch todo list from server");
            }
        });
        return todoListLiveData;
    }

    public LiveData<Todo> getTodo(long id) {
        api.getTodo(id).enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(@NonNull Call<Todo> call, @NonNull Response<Todo> response) {
                todoLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Todo> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: failed to get todo");
            }
        });
        return todoLiveData;
    }
}
