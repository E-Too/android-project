package com.example.too.paperchaser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MEMBER_NAME="membername";
    public static final String MEMBER_ID="memberid";

    TextView textViewMembers2;
    TextView textViewMember;
    EditText editTextMember;

    Button buttonAddMember;
    ListView listViewMembers;

    DatabaseReference databaseMembers;

    List<com.example.too.paperchaser.Member> memberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseMembers= FirebaseDatabase.getInstance().getReference("member");

        textViewMembers2=(TextView) findViewById(R.id.textViewMembers2);

        textViewMember=(TextView) findViewById(R.id.textViewMember);
        editTextMember=(EditText) findViewById(R.id.editTextMember);

        buttonAddMember=(Button) findViewById(R.id.buttonAddMember);
        listViewMembers=(ListView) findViewById(R.id.listViewMembers);
        memberList=new ArrayList<>();

        buttonAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMember();

            }
        });

        listViewMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                com.example.too.paperchaser.Member member=memberList.get(i);

                Intent intent=new Intent(getApplicationContext(), AddTransactionActivity.class);

                intent.putExtra(MEMBER_ID, member.getMemberId());
                intent.putExtra(MEMBER_NAME, member.getMemberName());

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseMembers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                memberList.clear();
                for (DataSnapshot memberSnapshot: dataSnapshot.getChildren()){
                    com.example.too.paperchaser.Member member=memberSnapshot.getValue(com.example.too.paperchaser.Member.class);
                    memberList.add(member);
                }

                MemberList adapter=new MemberList(MainActivity.this, memberList);
                listViewMembers.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void addMember(){
        String name=editTextMember.getText().toString().trim();

        if(!TextUtils.isEmpty(name)){
            String id=databaseMembers.push().getKey();
            com.example.too.paperchaser.Member member=new com.example.too.paperchaser.Member(id, name);
            databaseMembers.child(id).setValue(member);
            Toast.makeText(this, "Member added", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }

    }

}
