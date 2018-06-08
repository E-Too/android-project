package com.example.too.paperchaser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddTransactionActivity extends AppCompatActivity {

    TextView textViewMemberName;
    EditText editTextAmount;
    TextView textViewDate;
    EditText editTextDate;
    Button buttonAddTransaction;
    TextView textViewMembers;
    ListView listViewTransactions;

    DatabaseReference databaseTransactions;

    List<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        textViewMemberName=(TextView) findViewById(R.id.textViewMemberName);
        editTextAmount=(EditText) findViewById(R.id.editTextAmount);
        buttonAddTransaction=(Button) findViewById(R.id.buttonAddTransaction);
        textViewDate=(TextView) findViewById(R.id.textViewDate);
        editTextDate=(EditText) findViewById(R.id.editTextDate);
        textViewMembers=(TextView) findViewById(R.id.textViewMembers);
        listViewTransactions=(ListView) findViewById(R.id.listViewTransactions);

        Intent intent=getIntent();

        transactions=new ArrayList<>();

        String id = intent.getStringExtra(MainActivity.MEMBER_ID);
        String name = intent.getStringExtra(MainActivity.MEMBER_NAME);

        textViewMemberName.setText(name);

        databaseTransactions= FirebaseDatabase.getInstance().getReference("transactions").child(name);


        buttonAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTransaction();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTransactions.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                transactions.clear();

                for (DataSnapshot transactionSnapshot : dataSnapshot.getChildren()){
                    Transaction transaction=transactionSnapshot.getValue(Transaction.class);
                    transactions.add(transaction);
                }

                TransactionList transactionListAdapter=new TransactionList(AddTransactionActivity.this, transactions);
                listViewTransactions.setAdapter(transactionListAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addTransaction(){
        String transactionAmount=editTextAmount.getText().toString().trim();
        String transactionDate=editTextDate.getText().toString().trim();

        if(!TextUtils.isEmpty(transactionAmount)){
            String id=databaseTransactions.push().getKey();
            Transaction transaction=new Transaction(id, transactionAmount, transactionDate);
            databaseTransactions.child(id).setValue(transaction);

            Toast.makeText(this, "Transaction saved successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Transaction amount should not be empty", Toast.LENGTH_LONG).show();
        }
    }

}
