package com.example.debtsnote;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debtsnote.database.OperationEntity;
import com.example.debtsnote.ui.ContextMenuRecyclerView;
import com.example.debtsnote.ui.OffsetDividerItemDecorator;
import com.example.debtsnote.ui.OperationsCardAdapter;
import com.example.debtsnote.viewmodel.OperationsViewModel;
import com.example.debtsnote.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailOperationsFragment extends Fragment {

    private SharedViewModel mSharedViewModel;
    private OperationsViewModel mViewModel;
    private OperationsCardAdapter mAdapter;
    private ContextMenuRecyclerView mRecyclerView;
    private List<OperationEntity> mOperations = new ArrayList<>();

    public DetailOperationsFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        DetailOperationsFragment fragment = new DetailOperationsFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_operations, container, false);

        initRecyclerView(view);

        return view;
    }

    private void initSharedViewModel() {
        mSharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    private void initViewModel(String userId) {
        final Observer<List<OperationEntity>> operationsObserver =
                new Observer<List<OperationEntity>>() {
                    @Override
                    public void onChanged(List<OperationEntity> operations) {
                        mOperations.clear();
                        mOperations.addAll(operations);

                        if (mAdapter == null) {
                            mAdapter = new OperationsCardAdapter(mOperations);
                            mRecyclerView.setAdapter(mAdapter);
//                            mAdapter.setOnItemClickListener(MainActivity.this);
                            registerForContextMenu(mRecyclerView);
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                };

        mViewModel = ViewModelProviders.of(this).get(OperationsViewModel.class);
        mViewModel.getOperationsByUserId(userId).observe(this, operationsObserver);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initSharedViewModel();
        initViewModel(mSharedViewModel.getUserId().getValue());
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.rvUserOperations);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decorator = new OffsetDividerItemDecorator(getContext(), layoutManager.getOrientation(), 72);
        mRecyclerView.addItemDecoration(decorator);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.operations_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        ContextMenuRecyclerView.RecyclerViewContextMenuInfo menuInfo =
                (ContextMenuRecyclerView.RecyclerViewContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_delete_operation: {
                OperationEntity operation = mOperations.get(menuInfo.position);
                mViewModel.deleteOperation(operation);
            }
        }

        return super.onContextItemSelected(item);
    }
}
