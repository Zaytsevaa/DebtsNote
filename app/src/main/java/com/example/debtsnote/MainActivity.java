package com.example.debtsnote;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debtsnote.database.UsersWithDebtsTotalEntity;
import com.example.debtsnote.ui.ContextMenuRecyclerView;
import com.example.debtsnote.ui.OffsetDividerItemDecorator;
import com.example.debtsnote.ui.UsersCardsAdapter;
import com.example.debtsnote.viewmodel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static com.example.debtsnote.database.CurrencyFormatter.toStringCurrency;
import static com.example.debtsnote.utilities.Constants.USER_ID_KEY;

public class MainActivity extends AppCompatActivity
        implements UsersCardsAdapter.OnItemClickListener {

    ContextMenuRecyclerView mRecyclerView;
    private UsersCardsAdapter mAdapter;
    private MainViewModel mViewModel;
    private TextView mTvTotal;

    private List<UsersWithDebtsTotalEntity> mUsersWithDebtsTotal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.rvUsersList);
        mTvTotal = findViewById(R.id.tvDebtTotal);

        initRecyclerView();
        initViewModel();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailTabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViewModel() {

        final Observer<List<UsersWithDebtsTotalEntity>> usersObserver =
                new Observer<List<UsersWithDebtsTotalEntity>>() {
                    @Override
                    public void onChanged(List<UsersWithDebtsTotalEntity> usersWithDebtsTotal) {
                        mUsersWithDebtsTotal.clear();
                        mUsersWithDebtsTotal.addAll(usersWithDebtsTotal);
                        int debtTotal = 0;
                        for (UsersWithDebtsTotalEntity user :
                                usersWithDebtsTotal) {
                            debtTotal += user.getUserDebtsTotal();
                        }

                        if (mAdapter == null) {
                            mAdapter = new UsersCardsAdapter(mUsersWithDebtsTotal);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);
                            registerForContextMenu(mRecyclerView);
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }
                        String string = getResources()
                                .getString(R.string.header_debts_total)
                                + toStringCurrency(debtTotal);
                        mTvTotal.setText(string);
                    }
                };

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.mUsersWithDebtsTotal.observe(this, usersObserver);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decorator = new OffsetDividerItemDecorator(MainActivity.this, layoutManager.getOrientation(), 72);
        mRecyclerView.addItemDecoration(decorator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if (id == R.id.action_delete_all) {
            deleteAllData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ContextMenuRecyclerView.RecyclerViewContextMenuInfo info =
                (ContextMenuRecyclerView.RecyclerViewContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete_user: {
                String userId = mAdapter.getCurrentUserId(info.position);
                mViewModel.deleteUserById(userId);
            }
        }
        return super.onContextItemSelected(item);
    }

    private void deleteAllData() {
        mViewModel.deleteAll();
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }


    @Override
    public void onItemClick(String userId) {
        Intent detailIntent = new Intent(this, DetailTabActivity.class);
        detailIntent.putExtra(USER_ID_KEY, userId);
        startActivity(detailIntent);
    }
}
