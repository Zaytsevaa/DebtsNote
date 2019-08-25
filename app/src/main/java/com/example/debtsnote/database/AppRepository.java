package com.example.debtsnote.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.debtsnote.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<UsersWithDebtsTotalEntity>> mUsersWithDebtsTotal;

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    public AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mUsersWithDebtsTotal = getUsersWithDebtsTotal();
    }

    public void addSampleData() {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insertAllUsers(SampleData.getUsers());
                mDb.operationDao().insertAllOperation(SampleData.getOperations());
            }
        });
    }

    private LiveData<List<UsersWithDebtsTotalEntity>> getUsersWithDebtsTotal() {
        return mDb.usersWithDebtsTotalDao().getUsersWithDebtsTotal();
    }

    public void deleteAll() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.operationDao().deleteAllOperations();
                mDb.userDao().deleteAllUsers();
            }
        });
    }

    public UserEntity getUserById(String userId) {
        return mDb.userDao().getUserById(userId);
    }

    public LiveData<List<OperationEntity>> getOperationsByUserId(String userId) {
        return mDb.operationDao().getOperationsByUserId(userId);
    }

    public void insertUser(final UserEntity user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insertUser(user);
            }
        });
    }

    public void updateUser(final UserEntity user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().updateUser(user);
            }
        });
    }

    public void insert(final OperationEntity operation) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.operationDao().insertOperation(operation);
            }
        });
    }

    public void deleteUserById(final String userId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().deleteUserById(userId);
            }
        });
    }

    public void deleteOperation(final OperationEntity operation) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.operationDao().deleteOperation(operation);
            }
        });
    }
}
