package com.example.atos.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.atos.myapplication.R;
import com.example.atos.myapplication.adapter.CustomListAdapter;
import com.example.atos.myapplication.customclasses.CustomHeader;
import com.example.atos.myapplication.global.DBHelper;
import com.example.atos.myapplication.model.User;

import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity implements CustomHeader.OnHeaderElementClickListener{

    static DBHelper db;
    CustomListAdapter adapter;
    ListView listView;
    String id;
    ArrayList<User> list = new ArrayList<User> ();
    CustomHeader header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //setting header
        ActionBar var = getSupportActionBar();
        header=new CustomHeader(this,this,var,"","plus","");
        header.setHeader();

        db = new DBHelper(this);
        String query = "create table if not exists contacts " + "(id integer primary key AUTOINCREMENT,  userName text,userId text,userPassword text)";
        db.createTableWithQueryAndTableName(query,"contacts");
        list = db.getAllRecordsFromTable("contacts");
       /* for(int i = 0; i<list.size(); i++){
            nameList.add(list.get(i).getUserName());
        }*/
        listView = (ListView) findViewById(R.id.listview);

        adapter = new CustomListAdapter(this, list);

        listView.setAdapter(adapter);

    }
    public void refereshList(int position){

        list.remove(position);
        adapter.notifyDataSetChanged();
    }
    public void callBackWhenCellClicked(User userObj,Boolean mode){


        Intent myIntent = new Intent(SqliteActivity.this, SaveInfoToDBActivity.class);
        //Create the bundle
        Bundle bundle = new Bundle();
        //Add your data to bundle
        bundle.putBoolean("operation",mode);
        //Add the bundle to the intent
        myIntent.putExtras(bundle);
        myIntent.putExtra("userObject",userObj);
      //  SqliteActivity.this.startActivity(myIntent);
        startActivityForResult(myIntent, 1);

    }
    public void deleteRecord(User userObj,int position,String tableName){
        db.deleteRecordbyId(userObj.getId(),tableName);
        refereshList(position);
    }
    public static DBHelper getDb(){
        return db;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                list = db.getAllRecordsFromTable("contacts");
                adapter.updateUserList(list);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    public void onLeftButtonClicked() {

        Intent myIntent = new Intent(SqliteActivity.this, ExpandableListActivity.class);
        SqliteActivity.this.startActivity(myIntent);
    }

    @Override
    public void onRightButtonClicked() {

        Intent myIntent = new Intent(SqliteActivity.this, SaveInfoToDBActivity.class);
        Bundle bundle = new Bundle();
        //Add your data to bundle
        bundle.putBoolean("operation",false);
        //Add the bundle to the intent
        myIntent.putExtras(bundle);
        // myIntent.putExtra("dbObject", (Serializable) db);
        // SqliteActivity.this.startActivity(myIntent);
        startActivityForResult(myIntent, 1);

    }


    /*public void resizeListViewAsPerCellCount() {
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = 100 * db.getNumberOfRows("contacts");
        listView.setLayoutParams(params);
        listView.requestLayout();

    }*/

}
