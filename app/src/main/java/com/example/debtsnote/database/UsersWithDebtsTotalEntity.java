package com.example.debtsnote.database;

public class UsersWithDebtsTotalEntity {

    private String userId;
    private String userName;
    private int userDebtsTotal;

    public UsersWithDebtsTotalEntity(String userId, String userName, int userDebtsTotal) {
        this.userId = userId;
        this.userName = userName;
        this.userDebtsTotal = userDebtsTotal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public int getUserDebtsTotal() {
        return userDebtsTotal;
    }

    public void setUserDebtsTotal(int userDebtsTotal) {
        this.userDebtsTotal = userDebtsTotal;
    }
}
