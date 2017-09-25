package com.example.atos.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Atos on 18/07/17.
 */

public class User implements Serializable {

    int id;
    String userName;
    String userId;
    String userPassword;

    public User(){
        this.id = 0;
        this.userName = null;
        this.userId = null;
        this.userPassword = null;
    }
    public User(int id,String userId,String userName,String userPassword){
        this.id = id;
        this.userName = userName;
        this.userId = userId;
        this.userPassword = userPassword;
    }
    public int getId(){
        return id;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserPassword(){
        return userPassword;
    }

    public static class ExpandableListDataPump {
        public static HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> cricket = new ArrayList<String>();
            cricket.add("India");
            cricket.add("Pakistan");
            cricket.add("Australia");
            cricket.add("England");
            cricket.add("South Africa");

            List<String> football = new ArrayList<String>();
            football.add("Brazil");
            football.add("Spain");
            football.add("Germany");
            football.add("Netherlands");
            football.add("Italy");

            List<String> basketball = new ArrayList<String>();
            basketball.add("United States");
            basketball.add("Spain");
            basketball.add("Argentina");
            basketball.add("France");
            basketball.add("Russia");

            expandableListDetail.put("CRICKET TEAMS", cricket);
            expandableListDetail.put("FOOTBALL TEAMS", football);
            expandableListDetail.put("BASKETBALL TEAMS", basketball);
            return expandableListDetail;
        }
    }
}
