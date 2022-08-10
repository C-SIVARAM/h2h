package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.H2H.Adapter.H2H_list_adapter;
import com.example.Net.NetworkConnection;
import com.example.second.R;

public abstract class MainActivity extends AppCompatActivity implements NetworkConnection.ConnectivityReceiverListener {
    LinearLayout neterror;
    LinearLayout netconnection;
    NetworkConnection networkConnection;


    IntentFilter intentFilter;
    private Context context;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        context=MainActivity.this;
        networkConnection=new NetworkConnection ( );
        intentFilter=new IntentFilter ( );
        intentFilter.addAction ( ConnectivityManager.CONNECTIVITY_ACTION );
        neterror=findViewById ( R.id.neterror );
        netconnection=findViewById ( R.id.netconnection );

    }

    @Override
    protected void onResume( ) {
        super.onResume ( );
        registerReceiver ( networkConnection ,intentFilter );
        networkConnection.setConnectivityReceiverListener ( this );
        networkConnection.isConnected ( this );

    }

    @Override
    protected void onDestroy( ) {
        super.onDestroy ( );
        try {
            unregisterReceiver ( networkConnection );
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace ( );
        }
    }

    public void onNetworkConnectionChanged( boolean isConnected ) {
        callInternet ( isConnected );
    }

    private void callInternet( boolean isConnected ) {
        if (isConnected) {
            neterror.setVisibility ( View.GONE );
            netconnection.setVisibility ( View.VISIBLE );
            Handler handler=new Handler ( );
            new Handler ( ).postDelayed ( new Runnable ( ) {
                @Override
                public void run( ) {
                    Intent intent=new Intent ( MainActivity.this ,login.class );
                    startActivity ( intent );
                    finish ( );
                }
            } ,1000 );
        } else {
            neterror.setVisibility ( View.VISIBLE );
            netconnection.setVisibility ( View.GONE );
        }


    }
}
