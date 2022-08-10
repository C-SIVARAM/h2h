package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.Net.NetworkConnection;
import com.example.second.R;

public abstract class Order extends AppCompatActivity  implements NetworkConnection.ConnectivityReceiverListener {


    LinearLayout neterror;
    LinearLayout netconnection;
    NetworkConnection networkConnection;
    IntentFilter intentFilter;
    private Context context;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_order );
        context=Order.this;
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

        Log.e ( "internet check" ,String.valueOf ( isConnected ) );
        if (isConnected) {
            neterror.setVisibility ( View.GONE );
            netconnection.setVisibility ( View.VISIBLE );
        } else {
            neterror.setVisibility ( View.VISIBLE );
            netconnection.setVisibility ( View.GONE );
        }

    }
}



