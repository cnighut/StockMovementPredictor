package com.example.qawbecrdteyf.st_war;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.la4j.matrix.dense.Basic2DMatrix;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import joinery.DataFrame;

class LongDataDownload extends AsyncTask<String, Void, String>{

    @Override
    protected String doInBackground(String... params){

        String name = params[0];
        String startdate = params[1];
        String enddate  = params[2];
        String id = String.valueOf(R.string.BHEL);

        String url = "https://www.quandl.com/api/v3/datasets/NSE/" + id+ ".csv?api_key=ecRkbFUtJymLxAWUNzx5&start_date=" + startdate + "&end_date=" +enddate;

        JSONObject jObj = new JSONObject();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line,results = "";
            while((line = reader.readLine()) != null){
                results += line;
            }
            reader.close();

            jObj = new JSONObject(results);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (JSONException e){
            e.printStackTrace();
        }

        List<String> listo = new ArrayList<String>();
        List<String> listh = new ArrayList<String>();
        List<String> listl = new ArrayList<String>();
        List<String> listc = new ArrayList<String>();

        JSONArray array;
        try {
            array = jObj.getJSONArray("data");
            for(int i = 0 ; i < array.length() ; i++){
                listo.add(array.getJSONObject(i).getString("Open"));
                listh.add(array.getJSONObject(i).getString("High"));
                listl.add(array.getJSONObject(i).getString("Low"));
                listc.add(array.getJSONObject(i).getString("Close"));
                
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int length = listc.size();

        DataFrame df = new DataFrame();
        df.add(listo);
        df.add(listh);
        df.add(listl);
        df.add(listc);
        df.add(df.col(listc));

        return null;
    }
}
