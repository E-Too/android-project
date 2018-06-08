package com.example.too.paperchaser;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by too on 11/23/17.
 */

public class TransactionList extends ArrayAdapter<Transaction> {

    private Activity context;
    private List<Transaction> transactions;

    public TransactionList(Activity context, List<Transaction> transactions){
        super(context, R.layout.transaction_list, transactions);
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.transaction_list, null, true);

        TextView textViewTransactionDate=(TextView) listViewItem.findViewById(R.id.textViewTransactionDate);
        TextView textViewTransactionAmount=(TextView) listViewItem.findViewById(R.id.textViewTransactionAmount);

        Transaction transaction=transactions.get(position);

        textViewTransactionDate.setText(transaction.getTransactionDate());
        textViewTransactionAmount.setText(transaction.getTransactionAmount());


        return listViewItem;

    }

}
