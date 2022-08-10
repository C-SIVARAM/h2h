package com.example.H2H.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.H2H.pojo.H2H_Pojo;
import com.example.second.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class H2H_list_adapter extends RecyclerView.Adapter<H2H_list_adapter.MyViewHolder> {

    private List< H2H_Pojo> H2H_list_Adapter;

    public H2H_list_adapter( List< H2H_Pojo > H2H_list_Adapter ) {

      this.H2H_list_Adapter=H2H_list_Adapter;
    }


    @NonNull
    @Override
    public H2H_list_adapter.MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent ,int viewType ) {
        LayoutInflater layoutInflater=LayoutInflater.from ( parent.getContext () );
        View listItem=layoutInflater.inflate( R.layout.food_list,parent,false );
        return new MyViewHolder( listItem );
    }

    @Override
    public void onBindViewHolder( @NonNull H2H_list_adapter.MyViewHolder holder ,int position ) {
        holder.name.setText ( H2H_list_Adapter.get ( position ).getFirst_name ().toString ());
        Log.e ("sss",H2H_list_Adapter.get ( position ).getFirst_name ().toString ());


        Picasso.get()
                .load(H2H_list_Adapter.get(position).getProfile_img().toString())
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent p = new Intent(Menupage_activity.this, speakers_content.class);
                // getconstartActivity(p);

            }
        });

    }




    @Override
    public int getItemCount( ) {
        return H2H_list_Adapter.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public MyViewHolder( @NonNull View itemView ) {
            super ( itemView );
            name = itemView.findViewById(R.id.name);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
