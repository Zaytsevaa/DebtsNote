package com.example.debtsnote.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String phoneNumber;
    private String address;
    private String comment;

    @Ignore
    public UserEntity(String name, String phoneNumber, String address, String comment) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.comment = comment;
    }

    public UserEntity(@NonNull String id, String name, String phoneNumber, String address, String comment) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.comment = comment;
    }

    @Ignore
    public UserEntity() {
    }

    public @NonNull
    String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
