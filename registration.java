package com.example.dcf.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity implements View.OnClickListener {

    Button register;
    Button signinpage;
    ProgressDialog pd;
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        signinpage=(Button)findViewById(R.id.button);
        pd = new ProgressDialog(this);
        signinpage.setOnClickListener(this);
    }

    public void add() {


        final EditText name = (EditText) findViewById(R.id.regname);
        final EditText pass = (EditText) findViewById(R.id.regpass);
        final EditText confpass = (EditText) findViewById(R.id.confpass);
        final EditText email = (EditText) findViewById(R.id.regemail);
        TextView erroremail = (TextView) findViewById(R.id.erroremail);
        TextView errorpassword = (TextView) findViewById(R.id.errorpassword);
        TextView unequalpass = (TextView) findViewById(R.id.unequalpass);

        final String em = email.getText().toString();
        final String n = name.getText().toString();
        final String p = pass.getText().toString();
        final String cp = confpass.getText().toString();
        boolean b = em.matches(regex);
        if (b) {
            erroremail.setVisibility(View.INVISIBLE);
            if (p.equals(cp) && p.length() > 6) {
                pd.setMessage("Registering........");
                pd.show();
                errorpassword.setVisibility(View.INVISIBLE);
                unequalpass.setVisibility(View.INVISIBLE);
                StringRequest sr = new StringRequest(Request.Method.POST, "https://vammuvamsi64.000webhostapp.com", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        name.setText("");
                        pass.setText("");
                        confpass.setText("");
                        email.setText("");
                        try {
                            JSONObject j = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), j.getString("message"), Toast.LENGTH_LONG).show();
                        }catch (JSONException e){
                            e.printStackTrace();

                        }
                        }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", n);
                        params.put("email", em);
                        params.put("password", p);
                        return params;
                    }
                };

                VolleySingleton.getInstance(this).addToRequestQueue(sr);


            } else if (p.equals(cp) && p.length() <= 6) {

                errorpassword.setVisibility(View.VISIBLE);
                errorpassword.setText("Password length must be greater than 6");


            } else {
                unequalpass.setVisibility(View.VISIBLE);
                unequalpass.setText("Passwords donot match");

            }


        } else {
            erroremail.setVisibility(View.VISIBLE);
            erroremail.setText("Invalid email");
            errorpassword.setVisibility(View.INVISIBLE);
            unequalpass.setVisibility(View.INVISIBLE);
            if (p.equals(cp) && p.length() <= 6) {

                errorpassword.setVisibility(View.VISIBLE);
                errorpassword.setText("Password length must be greater than 6");


            } else {
                unequalpass.setVisibility(View.VISIBLE);
                unequalpass.setText("Passwords donot match");

            }

        }

    }
    public void gotosigninpage(){

        Intent i = new Intent("login");
        startActivity(i);

    }

    @Override
    public void onClick(View v) {

            if(v == register){

                add();
            }
            if(v == signinpage ){

                gotosigninpage();
            }

    }
}
