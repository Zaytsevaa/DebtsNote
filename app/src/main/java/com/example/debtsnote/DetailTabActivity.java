package com.example.debtsnote;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.example.debtsnote.database.OperationEntity;
import com.example.debtsnote.ui.CustomViewPager;
import com.example.debtsnote.ui.DetailPagerAdapter;
import com.example.debtsnote.viewmodel.OperationsViewModel;
import com.example.debtsnote.viewmodel.SharedViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import static com.example.debtsnote.database.CurrencyFormatter.toIntCurrency;
import static com.example.debtsnote.utilities.Constants.USER_ID_KEY;

public class DetailTabActivity extends AppCompatActivity {

    private CustomViewPager mViewPager;
    private SharedViewModel mViewModel;
    private boolean mNewUser;
    private DetailPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton fab = view.findViewById(R.id.fab);
                if (mViewPager.getCurrentItem() == 0) {
//                    fab.setImageDrawable(R.drawable.);

                    displayOperationsDialog();
                } else {
//                    fab.setImageDrawable(R.drawable.);

//                    Snackbar.make(view, "User", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }
            }
        });

        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("User operations"));
        tabLayout.addTab(tabLayout.newTab().setText("User details"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new DetailPagerAdapter(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                tabLayout.getTabCount()
        );

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (!mNewUser) {
                    mViewPager.setCurrentItem(tab.getPosition());
                    mViewPager.setPagingEnabled(true);
                    if (mViewPager.getCurrentItem() == 0) {
                        fab.show();
                    } else {
                        fab.hide();
                    }
                } else {
                    mViewPager.setPagingEnabled(false);
                    tabLayout.setVisibility(View.GONE);
                    fab.hide();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initViewModel();
    }

    private void displayOperationsDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.operation_dialog, null);

        final EditText etvDebtValue = view.findViewById(R.id.etvDebtValue);
        final TextInputLayout tilDebtValue = view.findViewById(R.id.tilDebtValue);

        final EditText etvDescription = view.findViewById(R.id.etvDescription);
        final TextInputLayout tilDescription = view.findViewById(R.id.tilDescription);

        final AlertDialog dialog = new AlertDialog.Builder(DetailTabActivity.this)
                .setView(view)
                .setPositiveButton("Debt to me", null)
                .setNegativeButton("My debt", null)
                .create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button btnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button btnNegative = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validateValue(etvDebtValue, tilDebtValue)) {
                            String debtInput = etvDebtValue.getText().toString().trim();
                            String description = etvDescription.getText().toString();
                            saveValue(debtInput, description);
                            dialog.cancel();
                        }
                    }
                });

                btnNegative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (validateValue(etvDebtValue, tilDebtValue)) {
                            String debtInput = "-" + etvDebtValue.getText().toString().trim();
                            String description = etvDescription.getText().toString();
                            saveValue(debtInput, description);
                            dialog.cancel();
                        }
                    }
                });
            }
        });
        dialog.show();
    }

    private void saveValue(String debtInput, String description) {
        OperationEntity operation =
                new OperationEntity(
                        new Date(),
                        toIntCurrency(debtInput),
                        description,
                        mViewModel.getUserId().getValue());
        ViewModelProviders.of(DetailTabActivity.this)
                .get(OperationsViewModel.class)
                .saveOperation(operation);
    }

    // TODO Not MVVM
    private boolean validateValue(EditText etvDebtValue, TextInputLayout tilDebtValue) {
        String debtInput = etvDebtValue.getText().toString().trim();
        if (!debtInput.isEmpty()) {
            if (Float.valueOf(debtInput) > 0) {
                tilDebtValue.setErrorEnabled(false);
                return true;
            } else {
                tilDebtValue.setError("Debt must be more then 0");
                return false;
            }
        } else {
            tilDebtValue.setError("Please enter debt value");
            return false;
        }
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            setTitle(getResources().getString(R.string.new_user));
            mNewUser = true;
            mViewPager.setCurrentItem(1);
            mViewModel.setNewUserId();
        } else {
            setTitle(getResources().getString(R.string.user_details));
            String userId = extras.getString(USER_ID_KEY);
            mViewModel.setUserId(userId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mViewModel.setMenuButtonPressed(true);
//            DetailUserFragment current = (DetailUserFragment) mPagerAdapter.getItem(1);
//            current.validateUserName();
            if (mViewModel.isInputCorrect()) {
                mViewModel.setNewUser(false);
//                mViewModel.setMenuButtonPressed(false);
                finish();
                return true;
            } else {
//                mViewModel.setMenuButtonPressed(false);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    public void onBackPressed() {
//        saveAndReturn();
//    }
//
//    private void saveAndReturn() {
//
//    }
}
