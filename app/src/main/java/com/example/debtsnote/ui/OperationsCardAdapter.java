package com.example.debtsnote.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debtsnote.R;
import com.example.debtsnote.database.OperationEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.example.debtsnote.database.CurrencyFormatter.toStringCurrency;

public class OperationsCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ADD = 1;
    private static final int TYPE_REMOVE = 2;

    private List<OperationEntity> mUserOperations;

    public OperationsCardAdapter(List<OperationEntity> userOperations) {
        this.mUserOperations = userOperations;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ADD) {
            view = inflater.inflate(R.layout.operations_add_list_item, parent, false);
            return new MyDebtViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.operations_remove_list_item, parent, false);
            return new DebtToMeViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mUserOperations.get(position).getDebtValue() >= 0) {
            return TYPE_ADD;
        } else {
            return TYPE_REMOVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_ADD) {
            ((MyDebtViewHolder) holder).tvDebtValue.setText(
                    toStringCurrency(mUserOperations.get(position).getDebtValue()));
            Date date = mUserOperations.get(position).getCreationDate();
            String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
            ((MyDebtViewHolder) holder).tvMeta.setText(formattedDate);
            ((MyDebtViewHolder) holder).tvDebtDescription.setText(mUserOperations
                    .get(position).getDescription());

        } else {
            ((DebtToMeViewHolder) holder).tvDebtValue.setText(
                    toStringCurrency(mUserOperations.get(position).getDebtValue()));
            Date date = mUserOperations.get(position).getCreationDate();
            String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
            ((DebtToMeViewHolder) holder).tvMeta.setText(formattedDate);
            ((DebtToMeViewHolder) holder).tvDebtDescription.setText(mUserOperations
                    .get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mUserOperations.size();
    }

    public class MyDebtViewHolder extends RecyclerView.ViewHolder {

        TextView tvDebtValue, tvDebtDescription, tvMeta;

        public MyDebtViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDebtValue = itemView.findViewById(R.id.tv_debt_value);
            tvDebtDescription = itemView.findViewById(R.id.tv_debt_description);
            tvMeta = itemView.findViewById(R.id.tv_meta);

            itemView.setLongClickable(true);
        }
    }

    public class DebtToMeViewHolder extends RecyclerView.ViewHolder {

        TextView tvDebtValue, tvDebtDescription, tvMeta;

        public DebtToMeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDebtValue = itemView.findViewById(R.id.tv_debt_value);
            tvDebtDescription = itemView.findViewById(R.id.tv_debt_description);
            tvMeta = itemView.findViewById(R.id.tv_meta);

            itemView.setLongClickable(true);
        }
    }
}
