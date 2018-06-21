package com.example.dcf.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity implements View.OnClickListener{
     Button b5,b6;
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b6 = (Button)findViewById(R.id.button3);
        b5 = (Button)findViewById(R.id.login);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        pd = new ProgressDialog(this);
    }

    public void signin(){

        final EditText e1 = (EditText)findViewById(R.id.logmail);
        final EditText e2 = (EditText)findViewById(R.id.logpass);
        final String email = e1.getText().toString();
        final String pass = e2.getText().toString();
        if(email.matches(regex)){
            final String name ="";
            pd.setMessage("Logging in....");
            pd.show();
            StringRequest sr1 = new StringRequest(Request.Method.POST, "https://vammuvamsi64.000webhostapp.com", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                        e1.setText("");
                        e2.setText("");
                        pd.dismiss();
//                try {
//                    JSONObject o = new JSONObject(response);
//
//                    Toast.makeText(getApplicationContext(), o.getString("logg"), Toast.LENGTH_LONG).show();
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    if(response.equals("correct")){

                        Intent i = new Intent("gameactivity");
                        startActivity(i);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> p = new HashMap<>();
                    p.put("logemail",email);
                    p.put("logpass",pass);
                    p.put("name",name);
                    return p;
                }
            };

                    VolleySingleton.getInstance(this).addToRequestQueue(sr1);

//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(sr1);

        }else{

            Toast.makeText(getApplicationContext(),"invalid email",Toast.LENGTH_LONG).show();

        }







    }
    public void registerpage(){

        Intent i = new Intent("register");
        startActivity(i);

    }

    @Override
    public void onClick(View v) {
        if(v == b5){

            signin();

        }if(v == b6){
            registerpage();
        }


    }
}
