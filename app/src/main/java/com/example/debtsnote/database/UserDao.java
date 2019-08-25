package com.example.debtsnote.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);

    @Update
    void updateUser(UserEntity userEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUsers(List<UserEntity> users);

    @Delete
    void deleteUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE id = :id")
    UserEntity getUserById(String id);

    @Query("SELECT * FROM users ORDER BY name DESC")
    List<UserEntity> getAllUsers();

    @Query("DELETE FROM users")
    int deleteAllUsers();

    @Query("SELECT COUNT(*) FROM users")
    int getCountUsers();

    @Query("DELETE FROM users WHERE id = :userId")
    void deleteUserById(String userId);
}
