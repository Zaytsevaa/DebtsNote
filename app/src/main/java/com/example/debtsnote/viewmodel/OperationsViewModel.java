package com.example.debtsnote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.debtsnote.database.AppRepository;
import com.example.debtsnote.database.OperationEntity;

import java.util.List;

public class OperationsViewModel extends AndroidViewModel {
    private final AppRepository mRepository;

    public OperationsViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public LiveData<List<OperationEntity>> getOperationsByUserId(String userId) {
        return mRepository.getOperationsByUserId(userId);
    }

    public void saveOperation(OperationEntity operation) {
        mRepository.insert(operation);
    }

    public void deleteOperation(OperationEntity operation) {
        mRepository.deleteOperation(operation);
    }
}
