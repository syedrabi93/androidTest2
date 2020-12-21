package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;


public class CountryDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //object initalisation
    Spinner cntlist;
    EditText capital,amount,qty;
    ImageView flag;
    ListView lv;
    int price;
    SeekBar ticketcount;

    //array of countries to list in spinner
    String cntry[] = {"CANADA","USA","UK"};
    //array list of objects
    ArrayList <place> placeList = new ArrayList<>();
    //temp list of places for the selected country
    public static ArrayList<place>tempList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        fillobjects();
        cntlist = findViewById(R.id.country);
        capital = findViewById(R.id.capital);
        flag = findViewById(R.id.flag);
        lv = findViewById(R.id.list_view);
        amount = findViewById(R.id.total);
        ticketcount = findViewById(R.id.count);
        qty = findViewById(R.id.qty);


        //spinner initialisation and setting up the value
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cntry);
        cntlist.setAdapter(aa);
        cntlist.setOnItemSelectedListener(this);

        //when the listview item is clicked, it populates the selected value to the amount field
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                price = tempList.get(position).getPrice();
                ticketcount.setProgress(1);
                amount.setText(String.valueOf(price));
            }

        });

        //when seekbar value is changed, the ticket count is shown and the total ticket price is calculated
        ticketcount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress>=1)
                    qty.setText(String.valueOf(progress));
                else
                    qty.setText("1");

                //if the ticket count is more than 15, then apply 5% discount
                if (progress > 15 )
                    amount.setText(String.valueOf ((price * progress)-((price * progress)* 0.05)));
                else
                    amount.setText(String.valueOf (price * progress));

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }




    //when a country is selected in the spinner, the capital and the flag is displayed
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tempList.clear();
        for (int i=0;i<placeList.size();i++){
            if ( cntry[position].equals(placeList.get(i).getCountry())){
                capital.setText(placeList.get(i).getCapital());
                int image1 = flag.getResources().getIdentifier(placeList.get(i).getFlag(),"drawable",flag.getContext().getPackageName());
                flag.setImageResource(image1);
            }

        }
        fillTempList(cntry[position]);
        lv.setAdapter(new placeAdapter(tempList,this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //function to fill the place objects
    public void fillobjects(){

        placeList.add(new place("CANADA","Ottawa","Niagara falls", "canada", 100));
        placeList.add(new place("CANADA","Ottawa","CN Towers", "canada", 30));
        placeList.add(new place("CANADA","Ottawa","The Butchart Gardens", "canada", 30));
        placeList.add(new place("CANADA","Ottawa","Notre-Dame Basilica", "canada", 50));
        placeList.add(new place("USA","Washington DC","The Statue of Liberty", "usa", 90));
        placeList.add(new place("USA","Washington DC","The White House", "usa", 60));
        placeList.add(new place("USA","Washington DC","Times Square", "usa", 75));
        placeList.add(new place("UK","London","Big Ben", "uk", 30));
        placeList.add(new place("UK","London","Westminster Abbey", "uk", 25));
        placeList.add(new place("UK","London","Hyde Park", "uk", 15));

    }

    //function to fill temp objects for the selected country
    public void fillTempList(String cnt){
        for(int i=0;i<placeList.size();i++)
            if(cnt.equals(placeList.get(i).getCountry()))
                tempList.add(placeList.get(i));
    }


}