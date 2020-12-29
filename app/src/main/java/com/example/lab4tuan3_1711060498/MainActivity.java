package com.example.lab4tuan3_1711060498;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();
    RestauntAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button)findViewById(R.id.save); // tham chiếu đến Button
        save.setOnClickListener(onSave); // khai báo listener cho Button


    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();
            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type = (RadioGroup)findViewById(R.id.type);

            switch (type.getCheckedRadioButtonId())
            {
                case R.id.take_out: r.setType("Take out");
                    break;
                case R.id.sit_down: r.setType("Sit down");
                    break;
                case R.id.delivery: r.setType("De livery");
                    break;
            }
            ListView list = (ListView)findViewById((R.id.restaurants));
            adapter = new RestauntAdapter();
            list.setAdapter(adapter);
            String msg;
            msg =  r.getName().toString()+" " +r.getAddress().toString()+" "+r.getType().toString();
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
            restaurantList.add(r);
        }
    };
    class RestauntAdapter extends ArrayAdapter<Restaurant> {
        public RestauntAdapter(Context context, int textViewResourceld){
            super(context,textViewResourceld);
        }
        public RestauntAdapter(){
            super(MainActivity.this, android.R.layout.simple_list_item_1,restaurantList);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if (row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row,null);
            }
            Restaurant r = restaurantList.get(position);
            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon =(ImageView)row.findViewById(R.id.icon);
            String type = r.getType();
            if (type.equals("Take out"))
                icon.setImageResource(R.drawable.icon_t);
            else if (type.equals("Sit down"))
                icon.setImageResource(R.drawable.phong2);
            else
                icon.setImageResource(R.drawable.phong3);
            return row;
        }
    }



}