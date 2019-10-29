package com.example.debtsnote;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.debtsnote.database.AppDatabase;
import com.example.debtsnote.database.OperationDao;
import com.example.debtsnote.database.OperationEntity;
import com.example.debtsnote.database.UserDao;
import com.example.debtsnote.database.UserEntity;
import com.example.debtsnote.database.UsersWithDebtsTotalDao;
import com.example.debtsnote.database.UsersWithDebtsTotalEntity;
import com.example.debtsnote.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DatabaseTest {
//    public static final String TAG = "Junit";
//    private AppDatabase mDb;
//    private UserDao mUserDao;
//    private OperationDao mOperationDao;
//    private UsersWithDebtsTotalDao mUserWithDebtsTotalDao;
//
//    @Before
//    public void createDb() {
//        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        mDb = Room.inMemoryDatabaseBuilder(context,
//                AppDatabase.class).build();
//        mUserDao = mDb.userDao();
//        mOperationDao = mDb.operationDao();
//        mUserWithDebtsTotalDao = mDb.usersWithDebtsTotalDao();
//        Log.i(TAG, "createDb");
//    }
//
//    @After
//    public void closeDb() {
//        mDb.close();
//        Log.i(TAG, "closeDb");
//    }
//
//    @Test
//    public void createAndRetrieveDebts() {
//        mUserDao.insertAllUsers(SampleData.getUsers());
//        int count = mUserDao.getCountUsers();
//        Log.i(TAG, "createAndRetrieveUsers: count=" + count);
//        assertEquals(SampleData.getUsers().size(), count);
//
//        mOperationDao.insertAllOperation(SampleData.getOperations());
//        count = mOperationDao.getCountOperations();
//        Log.i(TAG, "createAndRetrieveOperations: count=" + count);
//        assertEquals(SampleData.getOperations().size(), count);
//
//        List<UsersWithDebtsTotalEntity> list = new ArrayList<>();
//        list.addAll(mUserWithDebtsTotalDao.getUsersWithDebtsTotal());
//        count = list.size();
//        Log.i(TAG, "createAndRetrieveDebts: count=" + count);
//        assertEquals(2, count);
//    }
//
//    @Test
//    public void compareStrings() {
//        mUserDao.insertAllUsers(SampleData.getUsers());
//        mOperationDao.insertAllOperation(SampleData.getOperations());
//        UserEntity originalUser = SampleData.getUsers().get(0);
//        OperationEntity originalOperation = SampleData.getOperations().get(0);
//        int originalDebt = 100;
//
//        UserEntity fromDbUser = mUserDao.getUserById("eafe81ac-a944-11e9-a2a3-2a2ae2dbcce4");
//        assertEquals(originalUser.getName(),fromDbUser.getName());
//
//        OperationEntity fromDbOperation = mOperationDao.getOperationsByUserId("eafe81ac-a944-11e9-a2a3-2a2ae2dbcce4").get(0);
//        assertEquals(originalOperation.getDebtValue(), fromDbOperation.getDebtValue());
//
//        List<UsersWithDebtsTotalEntity> list = new ArrayList<>();
//        list.addAll(mUserWithDebtsTotalDao.getUsersWithDebtsTotal());
//        int fromDbDebt = list.get(0).getUserDebtsTotal();
//        assertEquals(originalDebt, fromDbDebt);
//
//    }
}
