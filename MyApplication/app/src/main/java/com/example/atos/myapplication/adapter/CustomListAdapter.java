package com.example.atos.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.atos.myapplication.R;

import com.example.atos.myapplication.controller.SqliteActivity;
import com.example.atos.myapplication.model.User;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter{
    ArrayList<User> userlist = new ArrayList<User>();
    Context context;

    private static LayoutInflater inflater=null;
    public CustomListAdapter(Context activityObj, ArrayList<User> userlist) {
        // TODO Auto-generated constructor stub
        this.userlist  = userlist;
        context =    activityObj;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return userlist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageButton imgbtn;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.program_list, null);
        holder.tv=(TextView) rowView.findViewById(R.id.userNameTxtView);

        holder.imgbtn = (ImageButton) rowView.findViewById(R.id.deletebtn);

        final User userObj = userlist.get(position);
        holder.tv.setText(userObj.getUserName());

        holder.tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SqliteActivity sqliteObj = (SqliteActivity) context;
                sqliteObj.callBackWhenCellClicked(userObj, true);
            }
        });
     holder.imgbtn.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            SqliteActivity sqliteObj = (SqliteActivity) context;
            sqliteObj.deleteRecord(userObj,position,"contacts");
    }
});
        return rowView;
    }

    public void updateUserList(ArrayList<User> newlist) {
        this.userlist = newlist;
        this.notifyDataSetChanged();
    }



}