package com.example.android.stock_war;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class JsonTask extends AsyncTask<String, String, String[]>{

    Context ctx;

    JsonTask(Context ctx){
        this.ctx = ctx;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("preexecute", "CUTEF");
    }

    @Override
    protected String[] doInBackground(String... params) {
        HttpURLConnection connection = null;
        HttpURLConnection connection2 = null;
        Log.d("BEFRES", "something");
        BufferedReader reader = null;
        String [] strarray = new String[2];

        try {
            URL url = new URL(params[0]);
            URL url2 = new URL(params[1]);
            //HttpClient hhtpclient = new DefaultHttpClient();
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();



            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("Responsehopeful: ", "> " + line);   //here u ll get whole response...... :-)

            }

            connection.disconnect();
            connection2 = (HttpURLConnection) url2.openConnection();
            connection2.connect();

            InputStream stream2 = connection2.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream2));

            StringBuffer buffer2 = new StringBuffer();
            line = "";

            while ((line = reader.readLine()) != null) {
                buffer2.append(line+"\n");
                Log.d("Responsercurrrent: ", "> " + line);   //here u ll get whole response...... :-)

            }

            strarray[0] = buffer.toString();
            strarray[1] = buffer2.toString();
            //return buffer.toString();
            return strarray;

        } catch (MalformedURLException e) {
            Log.e("MalformedURL", e.getMessage());
        } catch (IOException e) {
            Log.e("IOExceptionbig", e.getMessage());
        } finally {
            //Log.d("what's the error", "some error machan");
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Log.e("IOException2", e.getMessage());
            }
        }
        return null;

    }

    @Override
    protected void onPostExecute(String[] result) {
        super.onPostExecute(result);
        /*if (pd.isShowing()){
            pd.dismiss();
        }*/
        //txtJson.setText(result);

        String filename = "prediction.json";
        String filename2 = "data.json";

        //File file = new File(this.getDir(), filename);



        //String fileContents = "Hello world!";
        FileOutputStream outputStream;
        FileOutputStream outputStream2;

        try {
            //JsonTask ctx = this;
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(result[0].getBytes());
            outputStream.close();
            String path =  ctx.getFilesDir().getAbsolutePath() + "/" + "prediction.json";
            Log.d("Hopefully", "JSON!!");
//            String yourFilePath = ctx.getFilesDir() + "/" + "profiles.json";
            Log.d("path is here", path);
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            Log.d("please", String.valueOf(sb));
            fis.close();

            outputStream2 = ctx.openFileOutput(filename2, Context.MODE_PRIVATE);
            outputStream2.write(result[1].getBytes());
            outputStream2.close();
            String path2 = ctx.getFilesDir().getAbsolutePath() + "/data.json";
            FileInputStream fis2 = new FileInputStream(path2);
            InputStreamReader isr2 = new InputStreamReader(fis2);
            BufferedReader bufferedReader2 = new BufferedReader(isr2);
            StringBuilder sb2 = new StringBuilder();
            line = "";
            while((line = bufferedReader2.readLine())!=null){
                sb2.append(line);
            }
            Log.d("please",String.valueOf(sb2));
            fis2.close();

        } catch (Exception e) {

            Log.e("Error here",e.getMessage());
        }

    }
}
