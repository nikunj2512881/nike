package com.example.atos.myapplication.global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Utility class
 * Created by A662144 on 21-03-2017.
 */

public class Utils {

    private static final String TAG = "Utils";

    /**
     * Method used to check internet connection
     *
     * @param context context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Method used to create directory
     */
    public static void createRootDirectory() {
        String folder_main = "MaintenanceFriend";
        File file = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f1 = new File(Environment.getExternalStorageDirectory() + "/" + folder_main, "data");
        if (!f1.exists()) {
            f1.mkdirs();
        }
        Log.i(TAG, "StorageAbsolutePath: " + f1.getAbsolutePath());
        Log.i(TAG, "StoragePath: " + f1.getPath());
    }

    /**
     * Method used to get file from directory
     *
     * @param fileName file name
     */
    public static String getFileFromSDCard(String fileName) {
        String path = Environment.getExternalStorageDirectory().toString() + "/MaintenanceFriend/data/";
        Log.i(TAG, "RootPath: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.i(TAG, "Size: " + files.length);
        String filePath = "";
        for (int i = 0; i < files.length; i++) {
            Log.i(TAG, "FileName:" + files[i].getName());
            if (files[i].getName().equalsIgnoreCase(fileName)|| files[i].getName().contains(fileName)) {
                filePath = files[i].getAbsolutePath();
                Log.i(TAG, "FilePath:" + filePath);
                break;
            }
        }
        return filePath;
    }
}
