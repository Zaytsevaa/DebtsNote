package com.example.debtsnote.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OperationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOperation(OperationEntity operationEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllOperation(List<OperationEntity> operations);

    @Delete
    void deleteOperation(OperationEntity operationEntity);

    @Query("SELECT * FROM operations WHERE id = :id")
    OperationEntity getOperationById(String id);

    @Query("SELECT * FROM operations WHERE userId = :userId")
    LiveData<List<OperationEntity>> getOperationsByUserId(String userId);

    @Query("SELECT * FROM operations ORDER BY creationDate DESC")
    LiveData<List<OperationEntity>> getAllOperations();

    @Query("DELETE FROM operations")
    int deleteAllOperations();

    @Query("SELECT COUNT(*) FROM operations")
    int getCountOperations();
}
