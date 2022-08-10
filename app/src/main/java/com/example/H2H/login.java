package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.H2H.Utils.H2H_Common;
import com.example.H2H.Utils.H2H_SharedPrefManager;
import com.example.H2H.Utils.H2H_URL;
import com.example.H2H.Utils.H2H_User;
import com.example.H2H.Utils.H2H_Utils;
import com.example.Net.NetworkConnection;
import com.example.second.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements NetworkConnection.ConnectivityReceiverListener {

    LinearLayout error;
    RelativeLayout conneced;
    NetworkConnection networkConnection;
    IntentFilter intentFilter;
    public Context context;
    EditText UserName,password;
    TextView sigup, forgetpassword;
    Button loginbt;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        context=login.this;
        networkConnection=new NetworkConnection ( );
        intentFilter=new IntentFilter ( );
        intentFilter.addAction ( ConnectivityManager.CONNECTIVITY_ACTION );
        error=findViewById ( R.id.error);
        conneced=findViewById ( R.id.conneced );
        UserName=findViewById ( R.id.UserName );
        password=findViewById ( R.id.password );
        sigup=findViewById ( R.id.sigup );
        forgetpassword=findViewById( R.id.forgetpassword );
        loginbt=findViewById ( R.id.loginbt);
        forgetpassword.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick( View view ) {
                Intent intent=new Intent ( login.this ,forgetpassword.class );
                startActivity ( intent );
            }
        } );
        sigup.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick( View view ) {
                Intent intent=new Intent ( login.this ,create_account.class );
                startActivity ( intent );
            }
        } );






}



    @Override
    protected void onResume( ) {
        super.onResume ( );
        registerReceiver ( networkConnection ,intentFilter );
        networkConnection.setConnectivityReceiverListener ( this );
        NetworkConnection.isConnected ( this );

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
    private void callInternet( boolean isConnected )
    {
        Log.e ( "internet check" ,String.valueOf ( isConnected ) );
        if (isConnected) {

            error.setVisibility ( View.GONE );
            conneced.setVisibility ( View.VISIBLE );

        }
        else {
            error.setVisibility ( View.VISIBLE );
            conneced.setVisibility ( View.GONE );
        }
    }
    private < Login > void view_list_set()

    {
        StringRequest stringRequest = new StringRequest( Request.Method.POST,H2H_URL.POST_LOGIN,
         ServerResponse -> {
           JSONObject jsonResponse;


           try
          {
              //JSONObject jsonResponse1 = new JSONObject(ServerResponse);
              JSONObject jsonResponse1 = new JSONObject(ServerResponse);

              Log.d("fdsdfsdf",""+jsonResponse1);


              Log.d("dfghdfhbgd",""+ServerResponse);


              //boolean success = jsonResponse1.getBoolean("success");
              H2H_Common.status=jsonResponse1.getString("status");
               String message=jsonResponse1.getString(" ");
              if (H2H_Common.status.equalsIgnoreCase("1"))
              {
                  H2H_Common.user_id=jsonResponse1.getString("user_id");
                  H2H_Common.user_first_name=jsonResponse1.getString("first_name");
                  H2H_Common.user_last_name=jsonResponse1.getString("last_name");
                  H2H_Common.user_username=jsonResponse1.getString("username");
                  H2H_Common.user_email=jsonResponse1.getString("email");
                  H2H_Common.user_role_id=jsonResponse1.getString("user_role_id");
                  H2H_Common.user_designation=jsonResponse1.getString("designation");
                  H2H_Common.user_phone=jsonResponse1.getString("phone");
                  H2H_Common.user_company_id=jsonResponse1.getString("company_id");
                  H2H_Common.company_name=jsonResponse1.getString("company_name");
                  H2H_Common.user_office_id=jsonResponse1.getString("office_id");
                  H2H_Common.user_login_url=jsonResponse1.getString("login_url");
                  H2H_Common.user_profile_img=jsonResponse1.getString("profile_img");
                  H2H_Common.user_emp_id=jsonResponse1.getString("empid");
                  H2H_Common.user_incharge_name=jsonResponse1.getString("incharge_name");
                  H2H_Common.user_incharge_id=jsonResponse1.getString("incharge_id");
                  H2H_Common.user_incharge_id=jsonResponse1.getString("incharge_phone_number");

                  Log.d("sdfgsdfsd","sdgsdfgsdfg");
                  H2H_User user = new H2H_User (
                          jsonResponse1.getInt("user_id"),
                          jsonResponse1.getString("first_name"),
                          jsonResponse1.getString("last_name"),
                          jsonResponse1.getString("username"),
                          jsonResponse1.getString("email"),
                          jsonResponse1.getString("user_role_id"),
                          jsonResponse1.getString("designation"),
                          jsonResponse1.getString("phone"),
                          jsonResponse1.getString("company_name"),
                          jsonResponse1.getString("company_id"),
                          jsonResponse1.getString("office_id"),
                          jsonResponse1.getString("login_url"),
                          jsonResponse1.getString("profile_img"),
                          jsonResponse1.getString("incharge_name"),
                          jsonResponse1.getString("incharge_id"),
                          jsonResponse1.getString("incharge_phone_number"),
                          jsonResponse1.getString("empid")
                  );
                  // Log.d("fdgdf",""+email);
                  Log.d("fdgdf", "85" + user);
                  H2H_SharedPrefManager.getInstance( getApplicationContext()).userLogin( user);
                  //Log.d("fdgdf",""+name);




                  Intent intent = new Intent(login.this, home_page.class);
                  startActivity(intent);
                  //overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);

                  SharedPreferences sharedPreferences = getSharedPreferences("MyLogin.txt", Context.MODE_PRIVATE);
                  SharedPreferences.Editor editor = sharedPreferences.edit();
                  editor.putBoolean("FirstLogin", true);

                  Log.d("dsfgvdsgs", "" + editor.putBoolean("FirstLogin", true));

                  editor.apply();

                  SharedPreferences sharedPreferences1 = PreferenceManager
                          .getDefaultSharedPreferences( login.this);
                  SharedPreferences.Editor editor1 = sharedPreferences1.edit();
                  editor1.putString( H2H_Utils.USER_ID,H2H_Common.user_id);
                  editor1.putString(H2H_Utils.FIRST_NAME, H2H_Common.user_first_name);
                  editor1.putString(H2H_Utils.LAST_NAME, H2H_Common.user_last_name);
                  editor1.putString(H2H_Utils.USER_NAME, H2H_Common.user_username);
                  editor1.putString(H2H_Utils.USER_EMAIL, H2H_Common.user_email);
                  editor1.putString(H2H_Utils.USER_ROLE_ID, H2H_Common.user_role_id);
                  editor1.putString(H2H_Utils.USER_DESIGNATION, H2H_Common.user_designation);
                  editor1.putString(H2H_Utils.USER_PHONE, H2H_Common.user_phone);
                  editor1.putString(H2H_Utils.USER_COMPANY_NAME, H2H_Common.company_name);
                  editor1.putString(H2H_Utils.USER_COMPANY_ID, H2H_Common.user_company_id);
                  editor1.putString(H2H_Utils.USER_OFFICE_ID, H2H_Common.user_office_id);
                  editor1.putString(H2H_Utils.USER_LOGIN_URL, H2H_Common.user_login_url);
                  editor1.putString(H2H_Utils.USER_PROFILE_IMG, H2H_Common.user_profile_img);
                  editor1.putString(H2H_Utils.USER_INCHARGE_NAME, H2H_Common.user_incharge_name);
                  editor1.putString(H2H_Utils.USER_INCHARGE_PHONE, H2H_Common.user_incharge_phone);
                  editor1.putString(H2H_Utils.USER_EMP_ID, H2H_Common.user_emp_id);

                  editor1.apply();

              }
              else
              {

                  Log.d("fdghdfgdfgfd","hdfghfdg");
              }



          }
           catch (JSONException e)
           {
               e.printStackTrace();
               //Log.d("gdhdfhgdf",""+e);
           }

             //   Toast.makeText(getApplicationContext(), ServerResponse, Toast.LENGTH_LONG).show();
         },
                                                         volleyError -> {

                                                            // Hiding the progress dialog after all task complete.
                                                            // progressbar.setVisibility(View.GONE);

                                                            // Showing error message if something goes wrong.

                                                            String value=volleyError.toString();
//                        snackBar(value);



                                                            //Toast.makeText(getApplicationContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                                                        }) {
          
                                                              
            @Override
            protected Map<String, String> getParams()
            {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<> ();

                // Adding All values to Params.
                params.put("username", H2H_Common.User_Name);
                params.put("password", H2H_Common.Password);
                Log.d("dfgdfgdfg","Dfhdfh"+params);
                Log.d("dfgdfgdfg","Dfhdfh"+ H2H_URL.POST_LOGIN);
                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue( login.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick( View v ) {
        if (v.getId() == R.id.loginbt) {
            H2H_Common.User_Name = UserName.getText().toString().trim();
            H2H_Common.Password = password.getText().toString().trim();
            view_list_set();
        }
    }
}
