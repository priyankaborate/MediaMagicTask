package com.priyanka.media_magic_task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public GridView listView;
    public ArrayList<itemModel> arrayList;

    public JSONArray jArray;

    public itemModel model;
    public ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (GridView) findViewById(R.id.listView);

        arrayList = new ArrayList<>();
        new fetchData().execute();
    }

    public class fetchData extends AsyncTask<String, String, String> {

        @Override
        public void onPreExecute() {
            super .onPreExecute();

            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Loading Images, please wait...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("https://picsum.photos/list");

            try {
                //cresting JsonArray
                jArray=new JSONArray(jsonStr);

                for(int i=0;i<jArray.length();i++)
                {
                    //JsonObject from jsonArray

                    JSONObject o=jArray.getJSONObject(i);

                    String id=o.getString("id");

                    String authorName=o.getString("author");

                    //creating imageUrl using given URL and id from json Response

                    String imageurlstr="https://picsum.photos/300/300?image="+id;

                    model = new itemModel();
                    model.setName(authorName);
                    model.setId(id);
                    model.setUrl(imageurlstr);

                    arrayList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result = null;

            return result;
        }

        @Override
        public void onPostExecute(String s) {
            super .onPostExecute(s);
            pDialog.dismiss();
            CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayList);
            listView.setAdapter(adapter);

        }
    }
}