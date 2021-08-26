package com.example.saltmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edt_id, edt_pw;
    Button btn_loginCheck, btn_ToJoin;
    final int LOGIN_REQUEST = 2002;
    RequestQueue requestQueue;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_loginCheck = findViewById(R.id.btn_loginCheck);
        btn_ToJoin = findViewById(R.id.btn_ToJoin);
        SharedPreferences spf = getApplicationContext().getSharedPreferences("mySPF", Context.MODE_PRIVATE);
        String check_id = spf.getString("id",null);

        intent = new Intent(LoginActivity.this,MainActivity.class);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);

        }



        btn_ToJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

        btn_loginCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String et_id = edt_id.getText().toString();
                String et_pw = edt_pw.getText().toString();

                String url = "http://192.168.0.88:8087/Project/login.do";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    Toast.makeText(LoginActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = spf.edit();
                                    editor.putString("id",et_id);
                                    editor.commit();
                                    startActivity(intent);
                                    finish();

                                } else {

                                    Toast.makeText(LoginActivity.this, "실패실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("id", et_id);
                        params.put("pw", et_pw);
                        params.put("req", "1");


                        return params;
                    }

                };


                requestQueue.add(request);

            }
        });

    }
}