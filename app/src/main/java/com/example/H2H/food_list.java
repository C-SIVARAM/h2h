package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.Net.NetworkConnection;
import com.example.second.R;

public class food_list extends AppCompatActivity implements NetworkConnection.ConnectivityReceiverListener {
  Button order;
    LinearLayout neterror;
    LinearLayout netconnection;
    NetworkConnection networkConnection;
    IntentFilter intentFilter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_food_list);
        order=findViewById ( R.id.order );
        context=food_list.this;
        networkConnection=new NetworkConnection ( );
        intentFilter=new IntentFilter ( );
        intentFilter.addAction ( ConnectivityManager.CONNECTIVITY_ACTION );
        neterror=findViewById ( R.id.neterror );
        netconnection=findViewById ( R.id.netconnection );

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( food_list.this,Order.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void  onResume() {
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
    public void onNetworkConnectionChanged(boolean isConnected) {
        callInternet(isConnected);
    }

    @Override
    public void onClick( View v ) {

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