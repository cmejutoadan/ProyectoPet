/* hacer que la aplicaión guarde la última localización
http://developer.android.com/training/location/index.html

/*VER proyecto EjemploUbicación
* permisos en manifest
*  <meta-data android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" /> en el xml
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>

    y en module app
        compile 'com.google.android.gms:play-services:6.5.87'*/


/* */
package com.example.cmejuto.proyectopet;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hiloSql.start(); //utilizando hilo para conectar

    }

 /* hilo para conectarse a la bd
    Thread hiloSql = new Thread() {
        public void run() {


            // The newInstance() call is a work around for some broken Java implementations
            //con esto nos aseguramos de que se crean los recursos estaticos necesarios
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.160.169:3305/petfinder?user=root&password=root");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from petfinder.usuario");
                while (rs.next()) {
                    Log.d("SQL", rs.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }; */

    //inner class
    private class ConectaBDTask extends AsyncTask<String, Void, String> {


        @Override //es llamado desde execute()
        protected String doInBackground(String... urls) {//recibe el array de argumentos

            String response = "";
            // CONEXIÓN A LA BD
            //con esto nos aseguramos de que se crean los recursos estaticos necesarios
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection con = DriverManager.getConnection("jdbc:mysql://10.0.160.169:3305/petfinder?user=root&password=root");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from petfinder.usuario");
                while (rs.next()) {

                    Log.d("SQL", rs.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return response;
        }
    }
    public void botonCargaUbicacion(View view) { //llamamos a este método desde el layout
        ConectaBDTask task = new ConectaBDTask();
        task.execute(new String[] { "http://paxinas.danielcastelao.org/~damian/yahw.html" });

        //

    }
}
