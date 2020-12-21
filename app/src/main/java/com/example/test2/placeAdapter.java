package com.example.test2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class placeAdapter extends BaseAdapter {

    private ArrayList<place> cntryPlace;
    LayoutInflater inflater;

    //constructor
    public placeAdapter(ArrayList<place> cntryPlace, Context context) {
        this.cntryPlace = cntryPlace;
        inflater = LayoutInflater.from(context);;
    }


    //sets the numnber of sells in the list view
    @Override
    public int getCount() {
        return cntryPlace.size();
    }



    @Override
    public Object getItem(int position) {
        return cntryPlace.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (view == null) {
            view = inflater.inflate(R.layout.place, null);
            holder.placeName = view.findViewById(R.id.placeName);
            holder.placePrice = view.findViewById(R.id.placePrice);
            view.setTag(holder);
        }
        else
            view.getTag();

        //the list view is displayed with the place name and the cost details as per the country selected
        holder.placeName.setText(cntryPlace.get(position).getPlaces());
        holder.placePrice.setText(String.valueOf(cntryPlace.get(position).getPrice()));
        return view;
        }


    static class ViewHolder{
        TextView placeName,placePrice;
    }
}
