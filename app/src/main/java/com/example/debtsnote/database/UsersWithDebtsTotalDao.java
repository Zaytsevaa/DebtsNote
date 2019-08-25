package com.example.debtsnote.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsersWithDebtsTotalDao {
    @Query("SELECT users.id AS userId, name AS userName, SUM(debtValue) " +
            "AS userDebtsTotal FROM users " +
            "LEFT JOIN operations on operations.userId = users.id " +
            "GROUP BY users.id")
    LiveData<List<UsersWithDebtsTotalEntity>> getUsersWithDebtsTotal();
}
