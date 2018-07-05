package com.example.qawbecrdteyf.st_war;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.R.*;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("SavedIn", "saved in list");
        outState.putParcelable("Savedlistview", listView.onSaveInstanceState());
        String str = listView.toString();
        Log.d("msg", str);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("useless", "notnull");
        if(savedInstanceState != null){
            Log.d("savedInstance", "notnull");
            ArrayList<Parcelable> listsaved = savedInstanceState.getParcelableArrayList("Savedlistview");
            String [] listed = new String[listsaved.size()];
            listed = listsaved.toArray(listed);
            //listsaved.toArray(listed);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, listed);
            listView.setAdapter(arrayAdapter);
        }else {
            Log.d("notsavedInstance", "its null");
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);

            listView = (ListView) findViewById(R.id.list);

            dataModels = new ArrayList<>();

            dataModels.add(new DataModel("BHEL", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("DMART", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("ICICIBANK", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("BANKBARODA", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("NTPC", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("PIDILITIND", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("SUNTV", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("WIPRO", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("DRREDDY", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("INFY", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("TCS", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("COALINDIA", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("TATAMOTORS", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("UCOBANK", "C: xx.xx O:xx.xx"));
            dataModels.add(new DataModel("RELIANCE", "C: xx.xx O:xx.xx"));

            adapter = new CustomAdapter(dataModels, getApplicationContext());

            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("why", "inList");
                final DataModel dataModel= dataModels.get(position);

                Button updbutton = (Button) view.findViewById(R.id.update);
                updbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("why", "inButton");
                        Toast.makeText(getApplicationContext(), "buttonClicked", Toast.LENGTH_LONG).show();
                        String stockname = dataModel.getName();
//                        Date c = Calender.getInstance().getTime();
                        String enddate  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        if (enddate.charAt(8)=='2' && enddate.charAt(9) == '9'){
                            enddate = enddate.substring(0, 9) + "8";
                        }
                        String remainend = enddate.substring(4, 10);
                        String stryear = Integer.toString(Integer.parseInt(enddate.substring(0,4))-1);
                        String startdate = stryear+remainend;


                        new LongDataDownload().execute(stockname,startdate, enddate);
                        //dataModels.add(new DataModel("RELIANCE", "C: 33.33 O:11.11"));
                        //adapter = new CustomAdapter(dataModels, getApplicationContext());
                        //listView.setAdapter(adapter);
                    }
                });

                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getData(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                Toast.makeText(getApplicationContext(), "outside clicked", Toast.LENGTH_LONG).show();
            }



        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.d("Hell", "onOptionsItemsSelected");
            Intent intent = NavUtils.getParentActivityIntent(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            NavUtils.navigateUpTo(this, intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
