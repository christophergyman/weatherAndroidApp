package com.example.courseworkweatherapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //Initialise variables for use later
    String data1[], data2[];
    Context context;

    //Assing consturcter for the adapter
    public MyAdapter(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //On view create holder call it to its view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Bind the country that is set aswell as its temp(which is nothing by default)
        holder.myText1.setText(data1[position]);
        holder.mytext2.setText(data2[position]);


        //onclick listener set for when something is clicked
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read from shared preferences and set the country the user has selected
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("defaultCountry", data1[position]);
                editor.commit();

            }
        });

    }

//    return item count of the item selected
    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        //set view holder items with their ID's
        TextView myText1, mytext2;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.nameS1);
            mytext2= itemView.findViewById(R.id.nameS2);

            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
