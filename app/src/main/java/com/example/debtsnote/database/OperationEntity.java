package com.example.debtsnote.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "operations",
        foreignKeys = @ForeignKey(entity = UserEntity.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = CASCADE))
public class OperationEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private Date creationDate;
    private int debtValue;
    private String description;
    @ColumnInfo(index = true)
    private String userId;

    @Ignore
    public OperationEntity(Date creationDate, int debtValue, String description, String userId) {
        this.id = UUID.randomUUID().toString();
        this.creationDate = creationDate;
        this.debtValue = debtValue;
        this.description = description;
        this.userId = userId;
    }

    public OperationEntity(@NonNull String id, Date creationDate, int debtValue, String description, String userId) {
        this.id = id;
        this.creationDate = creationDate;
        this.debtValue = debtValue;
        this.description = description;
        this.userId = userId;
    }

    @Ignore
    public OperationEntity() {
    }

    public @NonNull
    String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getDebtValue() {
        return debtValue;
    }

    public void setDebtValue(int debtValue) {
        this.debtValue = debtValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
