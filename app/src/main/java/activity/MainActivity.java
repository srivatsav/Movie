package activity;

import android.app.ProgressDialog;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.GalleryAdapter;


public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private final String API_KEY = "c23ee66b3e454dd6bdd097571d8a5de7";
    private final String endpoint = "http://api.themoviedb.org/3/movie/popular?api_key="+API_KEY;
    private String poster_path = "http://image.tmdb.org/t/p/w185/";
    private ArrayList<Image> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(),images);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        fetchImages();

    }

    private void fetchImages(){
        pDialog.setMessage("Downloading Images..");
        pDialog.show();


        JsonObjectRequest request = new JsonObjectRequest(endpoint,new JSONObject(),
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            Log.d(TAG,response.getJSONArray("results").toString());
                            JSONArray results = response.getJSONArray("results");
                            for(int i=0;i<results.length();i++)
                            {
                                JSONObject object = results.getJSONObject(i);
                                String path = object.getJSONObject("poster_path").toString();
                                setImageProperties(path);
                            }
                        }
                        catch(JSONException e)
                        {

                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
    private void setImageProperties(String path)
    {
        poster_path+=path;
        //Include model package and Image class file.

    }
}
