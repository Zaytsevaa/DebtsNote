package com.example.debtsnote.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.debtsnote.R;
import com.example.debtsnote.database.UsersWithDebtsTotalEntity;

import java.util.List;

import static com.example.debtsnote.database.CurrencyFormatter.toStringCurrency;

public class UsersCardsAdapter extends RecyclerView.Adapter<UsersCardsAdapter.ViewHolder> {

    private final List<UsersWithDebtsTotalEntity> mUsersWithDebtsTotal;
    private OnItemClickListener mListener;

    public String getCurrentUserId(int position) {
        return mUsersWithDebtsTotal.get(position).getUserId();
    }

    public interface OnItemClickListener {
        void onItemClick(String userId);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


    public UsersCardsAdapter(List<UsersWithDebtsTotalEntity> usersWithDebtsTotal) {
        this.mUsersWithDebtsTotal = usersWithDebtsTotal;
    }

    @NonNull
    @Override
    public UsersCardsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.users_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersCardsAdapter.ViewHolder holder, int position) {
        String userName = mUsersWithDebtsTotal.get(position).getUserName().toString();
        String userDebtTotal = toStringCurrency(
                mUsersWithDebtsTotal.get(position).getUserDebtsTotal());

        holder.tvUserName.setText(userName);
        holder.tvUserDebtTotal.setText(userDebtTotal);
    }

    @Override
    public int getItemCount() {
        return mUsersWithDebtsTotal.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvUserDebtTotal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserDebtTotal = itemView.findViewById(R.id.tvUserDebtTotal);
            itemView.setLongClickable(true);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        String userId = mUsersWithDebtsTotal.get(position).getUserId();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(userId);
                        }
                    }
                }
            });
        }
    }
}
