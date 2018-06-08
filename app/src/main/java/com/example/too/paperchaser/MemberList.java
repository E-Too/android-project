package com.example.too.paperchaser;

import android.app.Activity;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by too on 11/21/17.
 */

public class MemberList extends ArrayAdapter<Member> {

    private Activity context;
    private List<Member> memberList;

    public MemberList(Activity context, List<Member> memberList){
        super(context, R.layout.list_layout, memberList);
        this.context = context;
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View listViewItem=inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName=(TextView) listViewItem.findViewById(R.id.textViewName);

        Member member=memberList.get(position);

        textViewName.setText(member.getMemberName());

        return listViewItem;

    }
}
