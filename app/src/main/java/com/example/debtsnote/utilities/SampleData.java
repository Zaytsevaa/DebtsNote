package com.example.debtsnote.utilities;

import com.example.debtsnote.database.OperationEntity;
import com.example.debtsnote.database.UserEntity;
import com.example.debtsnote.database.UsersWithDebtsTotalEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleData {

    private static final String userId1 = "eafe81ac-a944-11e9-a2a3-2a2ae2dbcce4";
    private static final String userId2 = "eafe8602-a944-11e9-a2a3-2a2ae2dbcce4";
    private static final String userId3 = "eafe8774-a944-11e9-a2a3-2a2ae2dbcce4";

    private static final String SAMPLE_NAME_1 = "Andrey";
    private static final String SAMPLE_NAME_2 = "Ivan";
    private static final String SAMPLE_NAME_3 = "Elena";

    private static final String SAMPLE_PHONE_1 = "+79271234567";
    private static final String SAMPLE_PHONE_2 = "+79271234568";
    private static final String SAMPLE_PHONE_3 = "+79271234569";

    private static final String SAMPLE_ADDRESS_1 = "Saratov1";
    private static final String SAMPLE_ADDRESS_2 = "Saratov2";
    private static final String SAMPLE_ADDRESS_3 = "Saratov3";

    private static final String SAMPLE_COMMENT_1 = "Comment1";
    private static final String SAMPLE_COMMENT_2 = "Comment2";
    private static final String SAMPLE_COMMENT_3 = "Comment3";

    private static final int SAMPLE_CURRENCY_1 = 100;
    private static final int SAMPLE_CURRENCY_2 = 200;
    private static final int SAMPLE_CURRENCY_3 = 500;

    private static final String SAMPLE_DESC_1 = "Debt1";
    private static final String SAMPLE_DESC_2 = "Debt2";
    private static final String SAMPLE_DESC_3 = "Debt3";

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List<UsersWithDebtsTotalEntity> getUsersWithDebtsTotal() {
        List<UsersWithDebtsTotalEntity> usersWithDebtsTotal = new ArrayList<>();
        usersWithDebtsTotal.add(
                new UsersWithDebtsTotalEntity(userId1, SAMPLE_NAME_1, SAMPLE_CURRENCY_1));
        usersWithDebtsTotal.add(
                new UsersWithDebtsTotalEntity(userId2, SAMPLE_NAME_2, SAMPLE_CURRENCY_2));
        usersWithDebtsTotal.add(
                new UsersWithDebtsTotalEntity(userId3, SAMPLE_NAME_3, SAMPLE_CURRENCY_3));
        return usersWithDebtsTotal;
    }

    public static List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<>();
        users.add(new UserEntity(userId1, SAMPLE_NAME_1, SAMPLE_PHONE_1, SAMPLE_ADDRESS_1, SAMPLE_COMMENT_1));
        users.add(new UserEntity(userId2, SAMPLE_NAME_2, SAMPLE_PHONE_2, SAMPLE_ADDRESS_2, SAMPLE_COMMENT_2));
        users.add(new UserEntity(userId3, SAMPLE_NAME_3, SAMPLE_PHONE_3, SAMPLE_ADDRESS_3, SAMPLE_COMMENT_3));
        return users;
    }

    public static List<OperationEntity> getOperations() {
        List<OperationEntity> operations = new ArrayList<>();
        operations.add(new OperationEntity(getDate(0), SAMPLE_CURRENCY_1, SAMPLE_DESC_1, userId1));
        operations.add(new OperationEntity(getDate(-1), SAMPLE_CURRENCY_2, SAMPLE_DESC_2, userId2));
        operations.add(new OperationEntity(getDate(-2), SAMPLE_CURRENCY_3, SAMPLE_DESC_3, userId2));
        return operations;
    }
}
