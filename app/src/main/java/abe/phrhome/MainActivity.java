package abe.phrhome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText nm;
        final EditText em;
        final EditText ph;
        final EditText bg;
        final CheckBox cb,su,di;
        String blp= "no";
        String dbt= "no";
        String sug= "no";
        Button store,retrive;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm=(EditText)findViewById(R.id.Name);
        em=(EditText)findViewById(R.id.email);
        ph=(EditText)findViewById(R.id.phone);
        bg=(EditText)findViewById(R.id.bg);
cb = (CheckBox)findViewById(R.id.bp);
su = (CheckBox)findViewById(R.id.su);
di = (CheckBox)findViewById(R.id.db);
        store = (Button)findViewById(R.id.store);
        retrive = (Button)findViewById(R.id.viewd);
if(cb.isChecked()){
    blp= "yes";
}

        if(su.isChecked()){
            sug= "yes";
        }

        if(di.isChecked()){
            dbt= "yes";
        }
        final String finalBlp = blp;
        final String finalSug = sug;
        final String finalDbt = dbt;
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nm.getText().toString();
                String email = em.getText().toString();
                String phone = ph.getText().toString();
                String bgr = bg.getText().toString();

                storedata(name,email,phone,bgr, finalBlp, finalSug, finalDbt);
            }
        });

        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,retrive_data.class);
                startActivity(intent);
            }
        });



    }


    public void storedata(final String name, final String email,final String phone,final String bgr,final String as,final String bp,final String di) {

       // String tag_string_req = "req_fee";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://hashbird.com/phome/store.php", new Response.Listener<String>() {



            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

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
//
//                        //Toast.makeText(getApplicationContext(),"PERCENTAGE: " + subs.getString("Date"),Toast.LENGTH_SHORT).show();
//                        //    ArrayList results = new ArrayList<dataview>();
//                        for (int index = 0; index < user.length(); index++) {
//                            //  String name = user.getString(index);
//                            JSONObject subs = jObj.getJSONObject(user.getString(index));
//
//
//                        }


                        //     }
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
            }
                    else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
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
        })

        {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
               // String imei = telephonyManager.getDeviceId();
                params.put("name", name);
                params.put("email", email);
                params.put("phone", phone);
                params.put("bg", bgr);
                params.put("bp", bp);
                params.put("asthama", as);
                params.put("diabities", di);
//params.put("enc",imei);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq);

    }


}

