package com.example.debtsnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {UserEntity.class, OperationEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "DebtsNoteDatabase.db";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UserDao userDao();

    public abstract OperationDao operationDao();

    public abstract UsersWithDebtsTotalDao usersWithDebtsTotalDao();

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
