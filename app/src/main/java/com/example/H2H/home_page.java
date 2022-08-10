package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.H2H.Adapter.H2H_list_adapter;
import com.example.H2H.Utils.H2H_Common;
import com.example.H2H.Utils.H2H_URL;
import com.example.H2H.pojo.H2H_Pojo;
import com.example.Net.NetworkConnection;
import com.example.second.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class home_page extends AppCompatActivity implements NetworkConnection.ConnectivityReceiverListener {
    LinearLayout neterror;
    RelativeLayout netconnection;
    NetworkConnection networkConnection;
    IntentFilter intentFilter;
    private Context context;
    ImageView move;
    RecyclerView mRecylerView;
    RecyclerView sRecylerView;
    H2H_list_adapter sH2H_Pojo_Adapters;
    //H2H_Pojo_Adapters_1 sH2H_Pojo_Adapters_1;


    private ArrayList< H2H_Pojo > H2H_list_Adapter;
//    private ArrayList<H2H_Pojo> H2H_list_Adapter1;
    

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home_page );
        context=home_page.this;
        networkConnection=new NetworkConnection ( );
        intentFilter=new IntentFilter ( );
        intentFilter.addAction ( ConnectivityManager.CONNECTIVITY_ACTION );
        neterror=findViewById ( R.id.neterror );
        netconnection=findViewById ( R.id.netconnection );

        move=findViewById ( R.id.move );
        move.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick( View view ) {
                Intent intent=new Intent ( home_page.this ,food_list.class );
                startActivity ( intent );
            }
        } );




//        context= MenuPage_activity1.this;
//
//        Eshop_User user = Eshop_SharedPrefManager.getInstance(this).getUser();
//
//        H2H_Common.user_id=String.valueOf(user.getuser_id());
//        H2H_Common.user_username=String.valueOf(user.getusername());
//        H2H_Common.user_email=String.valueOf(user.getemail());
//        H2H_Common.user_role_id=String.valueOf(user.getuser_role_id());
//        H2H_Common.user_designation=String.valueOf(user.getdesignation());
//        H2H_Common.user_phone=String.valueOf(user.getphone());
//        H2H_Common.user_company_id=String.valueOf(user.getcompany_id());
//        H2H_Common.user_emp_id=String.valueOf(user.getemp_id());
//        H2H_Common.user_first_name=String.valueOf(user.getfirst_name());
//        H2H_Common.user_last_name=String.valueOf(user.getlast_name());
//        H2H_Common.user_office_id=String.valueOf(user.getoffice_id());
//        H2H_Common.user_login_url=String.valueOf(user.getlogin_url());
//        H2H_Common.user_profile_img=String.valueOf(user.getprofile_img());
//        H2H_Common.user_incharge_name=String.valueOf(user.getincharge_name());
//        H2H_Common.user_incharge_id=String.valueOf(user.getincharge_id());
//        H2H_Common.user_incharge_phone=String.valueOf(user.getincharge_phone_number());

        H2H_list_Adapter = new ArrayList<> ();
//        H2H_list_Adapter1 = new ArrayList<>();
        mRecylerView=findViewById(R.id.food);
//        sRecylerView=findViewById(R.id.rv_main1);
        H2H_Pojo_Api();
//        H2H_Pojo_Api_1();


      //  mRecylerView.setOnClickListener(view -> {


        //    Intent a = new Intent(MenuPage_activity1.this, Next_menupage.class);
          //  startActivity(a);

       // }) ;






    }

//    private void H2H_Pojo_Api_1() {
//
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, H2H_URL.POST_ATTENDENCE_LIST,
//                    response -> {
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            Log.d("cnbxbhxgd","fghdfhgfd"+obj);
//                            Log.d("getparams", "dfgfdg" + H2H_URL.POST_ATTENDENCE_LIST);
//
//
//                            if (obj.optString("status").equals("1")) {
//                                H2H_Common.previous_start_check=obj.optString("status");
//                                H2H_Common.school_leave_date_save=obj.optString("date");
//                                H2H_Common.total_leave=obj.optString("total");
//
//
//
//
//                                JSONArray dataArray = obj.getJSONArray("leave_list");
//                                if (dataArray.length()==0){
//                                    Log.d("Dfhdfgdfgd","dfhfgdhf");
//                                }
//                                //  H2H_list_Adapter.clear();
//                                for (int i = 0; i < dataArray.length(); i++) {
//                                    H2H_Pojo playerModel = new H2H_Pojo();
//                                    JSONObject product = dataArray.getJSONObject(i);
//                                    try {
//
//                                        Log.e("firstname",product.getString("first_name"));
//                                        playerModel.setoffice_id(product.getString("office_id"));
//                                        playerModel.setempid(product.getString("empid"));
//                                        playerModel.setFirst_name(product.getString("first_name"));
//                                        playerModel.setLast_name(product.getString("last_name"));
//                                        playerModel.setProfile_img(product.getString("profile_img"));
//                                        playerModel.setDesignation(product.getString("designation"));
//                                        playerModel.setEmail(product.getString("email"));
//                                       // playerModel.setUsername(product.getString("username"));
//                                        playerModel.setPhone(product.getString("phone"));
//                                        H2H_list_Adapter1.add(playerModel);
//
//
//
//
//
//                                    } catch (Exception ex) {
//                                        ex.printStackTrace();
//                                        Log.d("fgjhfdgjfdgjh","dfhdf"+ex);
//                                        ////  rv_main.setVisibility(View.GONE);
//                                        // txt_emptyview.setVisibility(View.VISIBLE);
//                                    }
//                                }
//
//                                if (H2H_list_Adapter1 != null) {
//                                    LinearLayoutManager mll_manager;
//
//                                    sH2H_Pojo_Adapters_1 = new H2H_Pojo_Adapters_1(H2H_list_Adapter1);
//                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
//                                    sRecylerView.setLayoutManager(linearLayoutManager);
//                                    sRecylerView.setAdapter(sH2H_Pojo_Adapters_1);
//
//                                    //H2H_Pojo_Adapters.visibleContentLayout();
//                                    Log.d("sdgsdgsd","sdgsd");
//                                }
//                            }
//                            else if(obj.optString("success").equals("2")){
//
//                                String message = obj.getString("message");
//
//
//
//                            }
//                            else{
//                                H2H_Common.previous_start_check=obj.optString("status");
//                                //   rv_main.setVisibility(View.GONE);
//                                //   txt_emptyview.setVisibility(View.VISIBLE);
//
//                            }
//                        } catch ( JSONException e) {
//                            e.printStackTrace();
//                            Log.d("fgjhfdgjfdgjh","dfhdf"+e);
//                        }
//                    }, error -> Log.d("dfhdfbfd","dfgfd")) {
//
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("office_id", "1");
//                    params.put("empid", "HSGT/SHQ/001/2018");
//
//                    Log.d("dfhsdhsdgsdgsd","sdgsfds"+params);
//                    Log.d("dfhsdhsdgsdgsd","sdgsfds"+H2H_URL.POST_ATTENDENCE_LIST);
//                    return params;
//                }
//            };
//            try {
//                RequestQueue requestQueue = Volley.newRequestQueue(this);
//                requestQueue.add(stringRequest);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//
//    }


    private void H2H_Pojo_Api() {

        StringRequest stringRequest = new StringRequest( Request.Method.POST,H2H_URL.POST_ATTENDENCE_LIST,
                                                         response -> {
                                                             try {
                                                                 JSONObject obj = new JSONObject( response);
                                                                 Log.d("cnbxbhxgd","fghdfhgfd"+obj);
                                                                 Log.d("getparams", "dfgfdg" + H2H_URL.POST_ATTENDENCE_LIST);


                                                                 if (obj.optString("status").equals("1")) {
                                                                     H2H_Common.previous_start_check=obj.optString( "status");
                                                                     H2H_Common.school_leave_date_save=obj.optString("date");
                                                                     H2H_Common.total_leave=obj.optString("total");




                                                                     JSONArray dataArray = obj.getJSONArray( "leave_list");
                                                                     if (dataArray.length()==0){
                                                                         Log.d("Dfhdfgdfgd","dfhfgdhf");
                                                                     }
                                                                     //  H2H_list_Adapter.clear();
                                                                     for (int i = 0; i < dataArray.length(); i++) {
                                                                         H2H_Pojo playerModel = new H2H_Pojo();
                                                                         JSONObject product = dataArray.getJSONObject(i);
                                                                         try {

                                                                             Log.e("firstname",product.getString("first_name"));
                                                                             playerModel.setoffice_id(product.getString("office_id"));
                                                                             playerModel.setempid(product.getString("empid"));
                                                                             playerModel.setFirst_name(product.getString("first_name"));
                                                                             playerModel.setLast_name(product.getString("last_name"));
                                                                             playerModel.setProfile_img(product.getString("profile_img"));
                                                                             playerModel.setDesignation(product.getString("designation"));
                                                                             playerModel.setEmail(product.getString("email"));
                                                                             playerModel.setPhone(product.getString("phone"));
                                                                             H2H_list_Adapter.add(playerModel);





                                                                         } catch (Exception ex) {
                                                                             ex.printStackTrace();
                                                                             Log.d("fgjhfdgjfdgjh","dfhdf"+ex);
                                                                             ////  rv_main.setVisibility(View.GONE);
                                                                             // txt_emptyview.setVisibility(View.VISIBLE);
                                                                         }
                                                                     }

                                                                     if (H2H_list_Adapter != null) {
                                                                         LinearLayoutManager mll_manager;

                                                                         sH2H_Pojo_Adapters = new H2H_list_adapter (H2H_list_Adapter);
                                                                         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                                                                         mRecylerView.setLayoutManager(linearLayoutManager);
                                                                         mRecylerView.setAdapter(sH2H_Pojo_Adapters);

                                                                         //H2H_Pojo_Adapters.visibleContentLayout();
                                                                         Log.d("sdgsdgsd","sdgsd");
                                                                     }
                                                                 }
                                                                 else if(obj.optString("success").equals("2")){

                                                                     String message = obj.getString("message");



                                                                 }
                                                                 else{
                                                                     H2H_Common.previous_start_check=obj.optString("status");
                                                                     //   rv_main.setVisibility(View.GONE);
                                                                     //   txt_emptyview.setVisibility(View.VISIBLE);

                                                                 }
                                                             } catch ( JSONException e) {
                                                                 e.printStackTrace();
                                                                 Log.d("fgjhfdgjfdgjh","dfhdf"+e);
                                                             }
                                                         },error -> Log.d("dfhdfbfd","dfgfd")) {

            class H2H_Pojo_Adapters {
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<> ();
                params.put("office_id", "1");
                params.put("empid", "HSGT/SHQ/001/2018");

                Log.d("dfhsdhsdgsdgsd","sdgsfds"+params);
                Log.d("dfhsdhsdgsdgsd","sdgsfds"+H2H_URL.POST_ATTENDENCE_LIST);
                return params;
            }
        };
        try {
            RequestQueue requestQueue = Volley.newRequestQueue( this);
            requestQueue.add(stringRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        }

        else {
            neterror.setVisibility ( View.VISIBLE );
            netconnection.setVisibility ( View.GONE );
        }

    }


}