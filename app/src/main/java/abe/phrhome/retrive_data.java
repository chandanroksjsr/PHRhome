package abe.phrhome;

/**
 * Created by P.Shankar on 5/29/2017.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class retrive_data extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrive_data);
        setTitle("Retrive Data");


        lv = (ListView) findViewById(R.id.lview);

        retrive();


        // Instanciating an array list (you don't need to do this,
        // you already have yours).


    }


    public void retrive() {

        String tag_string_req = "req_fee";

        //   pDialog.setMessage("loading");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://wfta.in/workspace/cloud/retrive.php", new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                // Log.d(TAG, "Login Response: " + response.toString());
                //hideDialog();

                try {
                    // JSONObject jObj = new JSONObject(response);
                    //  boolean error = jObj.getBoolean("error");
                    JSONArray jsonArray = new JSONArray(response);
                    boolean error = false;
                    // Check for error node in json
                    if (!error) {


                        //  String errorMsg = jObj.getString("error_msg");
//
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
                        // user successfully logged in
                        // Create login session
                        //     session.setLogin(true);

                        // Now store the user in SQLite
                        // String uid = jObj.getString("folio_no");

                        // declare array List for all headers in list
                        //   ArrayList<String> headersArrayList = new ArrayList<String>();

                        // Declare Hash map for all headers and their corresponding values


//                        JSONArray user = jObj.getJSONArray("subjects");
//
//
//                        //    for(i=0;i<user.length();i++) {
//
                        List<String> your_array_list = new ArrayList<String>();

//                        //Toast.makeText(getApplicationContext(),"PERCENTAGE: " + subs.getString("Date"),Toast.LENGTH_SHORT).show();
//                        //    ArrayList results = new ArrayList<dataview>();
                        for (int index = 0; index < jsonArray.length(); index++) {
                            //  String name = user.getString(index);

                            JSONObject name = jsonArray.getJSONObject(index);


                            your_array_list.add(name.getString("name"));

                            //    Toast.makeText(getApplicationContext(),name.getString("name"),Toast.LENGTH_SHORT).show();

                            // This is the array adapter, it takes the context of the activity as a
                            // first parameter, the type of list view as a second parameter and your
                            // array as a third parameter.


                        }


                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, your_array_list);
//
                        lv.setAdapter(arrayAdapter);

                        //     }
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();


                    } else {
                        // Error in login. Get the error message
//                        String errorMsg = jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("name", "name");


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }
}