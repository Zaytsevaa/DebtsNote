package com.example.debtsnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.debtsnote.database.AppRepository;
import com.example.debtsnote.database.UsersWithDebtsTotalEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private AppRepository mRepository;
    public LiveData<List<UsersWithDebtsTotalEntity>> mUsersWithDebtsTotal;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mUsersWithDebtsTotal = mRepository.mUsersWithDebtsTotal;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteUserById(String userId) {
        mRepository.deleteUserById(userId);
    }
}
