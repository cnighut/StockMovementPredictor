package com.example.android.stock_war;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.stock_war.adapter.FeedAdapter;
import com.example.android.stock_war.model.Feed;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedAdapter adapter;
    private List<Feed> feedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new JsonTask(this).execute("http://almat.almafiesta.com/chirag/predictionf.json", "http://almat.almafiesta.com/chirag/dataf.json");
        ArrayList<Integer> booljson= new ArrayList<>();
        ArrayList<Float> datajson = new ArrayList<>();
        datajson = getIndicators2();
        booljson = getIndicators();
        setContentView(R.layout.activity_main);

        feedList = new ArrayList<>();
        adapter = new FeedAdapter(this, feedList);

        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Feed feed = new Feed("BHEL", "Sect: Infrastucture", "O:" + datajson.get(0).toString(), "C: " + datajson.get(3).toString(), "L: "  + datajson.get(2).toString(), "H: " + datajson.get(1).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                 booljson.get(0), booljson.get(1), booljson.get(2), booljson.get(3), booljson.get(4));
        feedList.add(0, feed);

        feed = new Feed("Sun TV", "Sect: Media", "O:" + datajson.get(4).toString(), "C: " + datajson.get(7).toString(), "L: "  + datajson.get(6).toString(), "H: " + datajson.get(5).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(5), booljson.get(6), booljson.get(7), booljson.get(8), booljson.get(9));
        feedList.add(0, feed);
        feed = new Feed("ICICI bank", "Sect: Banking", "O:" + datajson.get(8).toString(), "C: " + datajson.get(11).toString(), "L: "  + datajson.get(10).toString(), "H: " + datajson.get(9).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(10), booljson.get(11), booljson.get(12), booljson.get(13), booljson.get(14));
        feedList.add(0, feed);
        feed = new Feed("Bharat Forging", "Sect: Casting", "O:" + datajson.get(12).toString(), "C: " + datajson.get(15).toString(), "L: "  + datajson.get(14).toString(), "H: " + datajson.get(13).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(15), booljson.get(16), booljson.get(17), booljson.get(18), booljson.get(19));
        feedList.add(0, feed);
        feed = new Feed("LTFH", "Sect: Finance", "O:" + datajson.get(16).toString(), "C: " + datajson.get(19).toString(), "L: "  + datajson.get(18).toString(), "H: " + datajson.get(17).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(20), booljson.get(21), booljson.get(22), booljson.get(23), booljson.get(24));
        feedList.add(0, feed);
        feed = new Feed("Reliance", "Sect: Refineries", "O:" + datajson.get(20).toString(), "C: " + datajson.get(23).toString(), "L: "  + datajson.get(22).toString(), "H: " + datajson.get(21).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(25), booljson.get(26), booljson.get(27), booljson.get(28), booljson.get(29));
        feedList.add(0, feed);
        feed = new Feed("Maruti", "Sect: Automobiles", "O:" + datajson.get(24).toString(), "C: " + datajson.get(27).toString(), "L: "  + datajson.get(26).toString(), "H: " + datajson.get(25).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(30), booljson.get(31), booljson.get(32), booljson.get(33), booljson.get(34));
        feedList.add(0, feed);
        feed = new Feed("Infosys", "Sect: Software", "O:" + datajson.get(28).toString(), "C: " + datajson.get(31).toString(), "L: "  + datajson.get(30).toString(), "H: " + datajson.get(29).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(35), booljson.get(36), booljson.get(37), booljson.get(38), booljson.get(39));
        feedList.add(0, feed);
        feed = new Feed("Asian Paints", "Sect: Paints", "O:" + datajson.get(32).toString(), "C: " + datajson.get(35).toString(), "L: "  + datajson.get(34).toString(), "H: " + datajson.get(33).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(40), booljson.get(41), booljson.get(42), booljson.get(43), booljson.get(44));
        feedList.add(0, feed);
        feed = new Feed("BPCL", "Sect: Refineries", "O:" + datajson.get(36).toString(), "C: " + datajson.get(39).toString(), "L: "  + datajson.get(38).toString(), "H: " + datajson.get(37).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(45), booljson.get(46), booljson.get(47), booljson.get(48), booljson.get(49));
        feedList.add(0, feed);
        feed = new Feed("Airtel", "Sect: Communications", "O:" + datajson.get(40).toString(), "C: " + datajson.get(43).toString(), "L: "  + datajson.get(42).toString(), "H: " + datajson.get(41).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(50), booljson.get(51), booljson.get(52), booljson.get(53), booljson.get(54));
        feedList.add(0, feed);
        feed = new Feed("Tata Steel", "Sect: Steel", "O:" + datajson.get(44).toString(), "C: " + datajson.get(47).toString(), "L: "  + datajson.get(46).toString(), "H: " + datajson.get(45).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(55), booljson.get(56), booljson.get(57), booljson.get(58), booljson.get(59));
        feedList.add(0, feed);
        feed = new Feed("ITC", "Sect: FMCG", "O:" + datajson.get(48).toString(), "C: " + datajson.get(51).toString(), "L: "  + datajson.get(50).toString(), "H: " + datajson.get(49).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(60), booljson.get(61), booljson.get(62), booljson.get(63), booljson.get(64));
        feedList.add(0, feed);
        feed = new Feed("Lupin", "Sect: Pharmacy", "O:" + datajson.get(52).toString(), "C: " + datajson.get(55).toString(), "L: "  + datajson.get(54).toString(), "H: " + datajson.get(53).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(65), booljson.get(66), booljson.get(67), booljson.get(68), booljson.get(69));
        feedList.add(0, feed);
        feed = new Feed("McDowell", "CAT: Breweries", "O:" + datajson.get(56).toString(), "C: " + datajson.get(59).toString(), "L: "  + datajson.get(58).toString(), "H: " + datajson.get(57).toString(), "https://s3.amazonaws.com/zaubatrademarks/11941166-0a1f-4ec0-872d-9e604ec9049c.png",
                booljson.get(70), booljson.get(71), booljson.get(72), booljson.get(73), booljson.get(74));
        feedList.add(0, feed);


        Collections.reverse(feedList);
        adapter.notifyDataSetChanged();

    }

    private ArrayList<Float> getIndicators2(){
        String json = null;
        InputStream is = null;
        StringBuilder sb = new StringBuilder();

        String path = this.getFilesDir().getAbsolutePath() + "/data.json";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while((line = bufferedReader.readLine())!= null){
                sb.append(line + "\n");
            }
            fis.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return str2arrdata(sb.toString());
    }

    private ArrayList<Float> str2arrdata(String s) {
        ArrayList<Float> flarr = new ArrayList<Float>();
        int count = 0;
        StringBuilder num = new StringBuilder();
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i) == '"') {
                count++;
                i++;
                Log.d("countrise", String.valueOf(count));
            }
            if(count%20 !=1 && count%20 != 2 && count%20 != 3 && count%20 !=4 && count %4 == 3){
                //i++;
                num.append(s.charAt(i));
            }
            if(count%20 !=1 && count%20 != 2 && count%20 != 3 && count%20 !=4 && count %4 == 0){
                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                try {
                    float number = decimalFormat.parse(num.toString()).floatValue();
                    flarr.add(number);
                    Log.d("numberfinally", String.valueOf(number));
                } catch (ParseException e) {
                    Log.d("numberfinally", "not a valid number"+ String.valueOf(num));
                }
                num = new StringBuilder();
            }

        }
        Log.d("flarrsize", String.valueOf(flarr.size()));
        return flarr;
    }

    private ArrayList<Integer> getIndicators() {

        String json = null;
        InputStream is=null;
        StringBuilder sb = new StringBuilder();

        try {
            String path =  this.getFilesDir().getAbsolutePath() + "/" + "prediction.json";
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
            fis.close();
            Log.d("change", "profile.json was changed here");

        }catch(Exception e){
            Log.e("errorbigone", e.getMessage());
        }
        Log.d("hereitisfucked", sb.toString());

        return(str2arrindicators(sb.toString()));

    }

    private ArrayList<Integer> str2arrindicators(String s) {

        ArrayList<Integer> indiarray = new ArrayList<Integer>();
        int cnt = 0;
        for(int i = 0 ;i <s.length(); i++){
            if(s.charAt(i) == ':'){
                indiarray.add(Character.getNumericValue(s.charAt(i+3)));Log.d("checkkk", Integer.toString(indiarray.get(cnt)));
                cnt++;
            }
        }
        return indiarray;

    }

}
