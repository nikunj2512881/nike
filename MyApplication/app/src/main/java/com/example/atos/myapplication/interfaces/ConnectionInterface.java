package com.example.atos.myapplication.interfaces;

/**
 * Created by Atos on 19/07/17.
 */

public interface ConnectionInterface {
    public void successWithResponse (Object response);
    public void failedWithErrorMess(String message);
}
