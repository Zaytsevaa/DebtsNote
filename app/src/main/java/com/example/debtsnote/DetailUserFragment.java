package com.example.debtsnote;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.debtsnote.database.UserEntity;
import com.example.debtsnote.viewmodel.SharedViewModel;
import com.example.debtsnote.viewmodel.UsersViewModel;
import com.google.android.material.textfield.TextInputLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailUserFragment extends Fragment {

    private UsersViewModel mViewModel;
    private SharedViewModel mSharedViewModel;
    private EditText mTvName, mTvPhone, mTvAddress, mTvComment;
    private TextInputLayout mTvNameInputLayout, mTvPhoneInputLayout;

    public DetailUserFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        DetailUserFragment fragment = new DetailUserFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mTvName = view.findViewById(R.id.etvName);
        mTvNameInputLayout = view.findViewById(R.id.tilName);

        mTvPhone = view.findViewById(R.id.etvPhone);
        mTvPhoneInputLayout = view.findViewById(R.id.tilPhone);

        mTvAddress = view.findViewById(R.id.etvAddress);

        mTvComment = view.findViewById(R.id.etvComment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initSharedViewModel();
        initViewModel();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (!mSharedViewModel.isNewUser()) {
            saveAndReturn();
        }
    }

    private void saveAndReturn() {
        mViewModel.saveUserDetails(mSharedViewModel.getUserId().getValue(),
                mTvName.getText().toString(),
                mTvPhone.getText().toString(),
                mTvAddress.getText().toString(),
                mTvComment.getText().toString());
    }

    // TODO Not MVVM
    private void validateUserName() {
        String userName = mTvName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            mTvNameInputLayout.setError("Name cannot be blank");
            mSharedViewModel.setInputCorrect(false);
        } else {
            mTvNameInputLayout.setErrorEnabled(false);
            mSharedViewModel.setInputCorrect(true);
        }
    }

    private void validateUserPhone() {
        String userPhone = mTvPhone.getText().toString().trim();
        if (((userPhone.charAt(0) == '+') && (userPhone.length() == 12)) || ((userPhone.charAt(0) != '+') && (userPhone.length() == 11)) || (TextUtils.isEmpty(userPhone))) {
            mTvPhoneInputLayout.setErrorEnabled(false);
            mSharedViewModel.setInputCorrect(true);
        } else {
            mTvPhoneInputLayout.setError("Phone number incorrect");
            mSharedViewModel.setInputCorrect(false);
        }
    }

    private void initSharedViewModel() {
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        mSharedViewModel.getMenuButtonPressed().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean menuButtonPressed) {
                if (menuButtonPressed) {
                    validateUserName();
                    validateUserPhone();
                    mSharedViewModel.setMenuButtonPressed(false);
                }
            }
        });
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        mViewModel.mLiveUser.observe(getViewLifecycleOwner(), new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity user) {
                mTvName.setText(user.getName());
                mTvPhone.setText(user.getPhoneNumber());
                mTvAddress.setText(user.getAddress());
                mTvComment.setText(user.getComment());
            }
        });

        if (!mSharedViewModel.isNewUser()) {
            mViewModel.loadData(mSharedViewModel.getUserId().getValue());
        }
    }
}
