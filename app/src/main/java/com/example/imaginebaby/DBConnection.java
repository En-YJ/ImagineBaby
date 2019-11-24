package com.example.imaginebaby;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * MS SQL Azure
 */

public class DBConnection {

    @SuppressLint("NewApi")
    public Connection connectionClass()
    {
        String user="yeop@yeopdbcore";
        String database="YeopDBCore";
        String password="ekdh6@naver.com";
        String server="yeopdbcore.database.windows.net:1433;DatabaseName=YeopDBCore";

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL=null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL="jdbc:jtds:sqlserver://"+server+";"+"DatabaseName="+database+";user="+user+";password="+password+";"+"integratedSecurity=true;";
            connection= DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1:",se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2:",e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3:",e.getMessage());
        }
        return connection;
    }

    /*@SuppressLint("NewApi")
    public Connection connectionClass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnetionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnetionURL = "jdbc:jtds:sqlserver://yeopdbcore.database.windows.net:1433;DatabaseName=YeopDBCore;user=yeop@yeopdbcore;password=ekdh6@naver.com;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnetionURL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/
}
