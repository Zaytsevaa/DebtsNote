package com.example.debtsnote.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.debtsnote.database.AppRepository;
import com.example.debtsnote.database.UserEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UsersViewModel extends AndroidViewModel {

    public MutableLiveData<UserEntity> mLiveUser =
            new MutableLiveData<>();
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UsersViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
    }

    public void loadData(final String userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                UserEntity user = mRepository.getUserById(userId);
                mLiveUser.postValue(user);
            }
        });
    }

    public void saveUserDetails(String userId, String name, String phone, String address, String comment) {

        if (TextUtils.isEmpty(name.trim()) ||
                TextUtils.isEmpty(phone.trim()) ||
                TextUtils.isEmpty(address.trim()) ||
                TextUtils.isEmpty(comment.trim())) {

            return;

        } else {
            UserEntity user = new UserEntity(userId,
                    name.trim(),
                    phone.trim(),
                    address.trim(),
                    comment.trim());
            if (mLiveUser.getValue() == null) {
                mRepository.insertUser(user);
            } else {
                mRepository.updateUser(user);
            }
        }
    }
}
