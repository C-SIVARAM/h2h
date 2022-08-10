package com.example.H2H;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.second.R;

public class forgetpassword extends AppCompatActivity {
  EditText name,mail,pass,c1;
    Button ca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.forgetpasswork );
        name=findViewById(R.id.name);
        pass=findViewById(R.id.pass);
        c1=findViewById(R.id.c1);
        ca=findViewById(R.id.ca);

       ca.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (pass.getText().toString().equals("1")|| c1.getText().toString().equals("1"))
               {
                   Intent intent= new Intent( forgetpassword.this,home_page .class);
                   startActivity(intent);
                   finish();
                   Toast.makeText( forgetpassword.this,"Successfull",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText( forgetpassword.this,"password is not matched",Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}