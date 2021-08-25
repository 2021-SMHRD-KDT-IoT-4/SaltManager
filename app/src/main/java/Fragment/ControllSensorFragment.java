package Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saltmanager.MainActivity;
import com.example.saltmanager.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Model.K_List_VO;
import Model.Z_List_VO;

public class ControllSensorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Z_List_VO zvo;
    K_List_VO kvo;

    public ControllSensorFragment(Z_List_VO zvo) {
        this.zvo = zvo;
    }

    public ControllSensorFragment(K_List_VO kvo) {
        this.kvo = kvo;
    }

    TextView tv_sensor_title;
    TextView tv_z_salinity_now, tv_z_indoor_temp_now, tv_z_indoor_humid_now, tv_z_water_temp_now,
            tv_z_wire_temp_now, tv_z_water_high_now;
    TextView tv_z_salinity_set, tv_z_indoor_temp_set, tv_z_indoor_humid_set, tv_z_water_temp_set,
            tv_z_wire_temp_set, tv_z_water_high_set;
    Button btn_z_salinity_p,btn_z_salinity_m,
            btn_z_indoor_humid_p,btn_z_indoor_humid_m, btn_z_water_temp_p,btn_z_water_temp_m,
            btn_z_wire_temp_p,btn_z_wire_temp_m, btn_z_water_high_p,btn_z_water_high_m;
    Button btn_sensor_info_send;
    RequestQueue requestQueue;
    String numbering;
    int salinity = 0;
    int indoor_humid = 0;
    int water_temp = 0;
    int wire_temp = 0;
    int water_high = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controll_sensor, container, false);

        tv_sensor_title = view.findViewById(R.id.tv_sensor_title);

        tv_z_salinity_now = view.findViewById(R.id.tv_z_salinity_now);
        tv_z_indoor_temp_now = view.findViewById(R.id.tv_z_indoor_temp_now);
        tv_z_indoor_humid_now = view.findViewById(R.id.tv_z_indoor_humid_now);
        tv_z_water_temp_now = view.findViewById(R.id.tv_z_water_temp_now);
        tv_z_wire_temp_now = view.findViewById(R.id.tv_z_wire_temp_now);
        tv_z_water_high_now = view.findViewById(R.id.tv_z_water_high_now);

        tv_z_salinity_set = view.findViewById(R.id.tv_z_salinity_set);
        tv_z_indoor_temp_set = view.findViewById(R.id.tv_z_indoor_temp_set);
        tv_z_indoor_humid_set = view.findViewById(R.id.tv_z_indoor_humid_set);
        tv_z_water_temp_set = view.findViewById(R.id.tv_z_water_temp_set);
        tv_z_wire_temp_set = view.findViewById(R.id.tv_z_wire_temp_set);
        tv_z_water_high_set = view.findViewById(R.id.tv_z_water_high_set);

        btn_z_salinity_p = view.findViewById(R.id.btn_z_salinity_p);
        btn_z_salinity_m = view.findViewById(R.id.btn_z_salinity_m);
        btn_z_indoor_humid_p = view.findViewById(R.id.btn_z_indoor_humi_p);
        btn_z_indoor_humid_m = view.findViewById(R.id.btn_z_indoor_humi_m);
        btn_z_water_temp_p = view.findViewById(R.id.btn_z_water_temp_p);
        btn_z_water_temp_m = view.findViewById(R.id.btn_z_water_temp_m);
        btn_z_wire_temp_p = view.findViewById(R.id.btn_z_wire_temp_p);
        btn_z_wire_temp_m = view.findViewById(R.id.btn_z_wire_temp_m);
        btn_z_water_high_p = view.findViewById(R.id.btn_z_water_high_p);
        btn_z_water_high_m = view.findViewById(R.id.btn_z_water_high_m);
        btn_sensor_info_send = view.findViewById(R.id.btn_send_sensor_info);

        if (zvo != null) {
            numbering = Integer.toString(zvo.getNumbering());
            tv_z_salinity_now.setText(zvo.getZ_salinity()+"");
            tv_z_indoor_temp_now.setText(zvo.getZ_indoor_temp()+"");
            tv_z_indoor_humid_now.setText(zvo.getZ_indoor_humid()+"");
            tv_z_water_temp_now.setText(zvo.getZ_water_temp()+"");
            tv_z_wire_temp_now.setText(zvo.getZ_wire_temp()+"");
            tv_z_water_high_now.setText(zvo.getZ_water_high()+"");

            tv_sensor_title.setText("증발지");
        }
        if(kvo != null){
            numbering = Integer.toString(kvo.getNumbering());
            tv_z_salinity_now.setText(kvo.getK_salinity()+"");
            tv_z_indoor_temp_now.setText(kvo.getK_indoor_temp()+"");
            tv_z_indoor_humid_now.setText(kvo.getK_indoor_humid()+"");
            tv_z_water_temp_now.setText(kvo.getK_water_temp()+"");
            tv_z_wire_temp_now.setText(kvo.getK_wire_temp()+"");
            tv_z_water_high_now.setText(kvo.getK_water_high()+"");
            tv_sensor_title.setText("결정지");
        }
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        }
        String url = "http://192.168.1.12:8084/Project/GetAuto_Running.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);
                            Toast.makeText(getContext(),json+"",Toast.LENGTH_SHORT).show();
                            indoor_humid = json.getInt("fan_run");
                            water_temp = json.getInt("wire_run");
                            salinity = json.getInt("waterhigh_run");
                            water_high = json.getInt("pump_run");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"접속실패",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("numbering", numbering);

                return params;
            }

        };
        requestQueue.add(request);



        tv_z_salinity_set.setText(Integer.toString(salinity));
        tv_z_indoor_temp_set.setText("xx");
        tv_z_indoor_humid_set.setText(Integer.toString(indoor_humid));
        tv_z_water_temp_set.setText(Integer.toString(water_temp));
        tv_z_wire_temp_set.setText(Integer.toString(wire_temp));
        tv_z_water_high_set.setText(Integer.toString(water_high));

        btn_z_salinity_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salinity++;
                tv_z_salinity_set.setText(Integer.toString(salinity));
            }
        });
        btn_z_salinity_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salinity--;
                tv_z_salinity_set.setText(Integer.toString(salinity));
            }
        });
        btn_z_indoor_humid_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indoor_humid++;
                tv_z_indoor_humid_set.setText(Integer.toString(indoor_humid));
            }
        });
        btn_z_indoor_humid_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indoor_humid--;
                tv_z_indoor_humid_set.setText(Integer.toString(indoor_humid));
            }
        });
        btn_z_water_temp_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water_temp++;
                tv_z_water_temp_set.setText(Integer.toString(water_temp));
            }
        });
        btn_z_water_temp_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water_temp--;
                tv_z_water_temp_set.setText(Integer.toString(water_temp));
            }
        });
        btn_z_wire_temp_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wire_temp++;
                tv_z_wire_temp_set.setText(Integer.toString(wire_temp));
            }
        });
        btn_z_wire_temp_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wire_temp--;
                tv_z_wire_temp_set.setText(Integer.toString(wire_temp));
            }
        });
        btn_z_water_high_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water_high++;
                tv_z_water_high_set.setText(Integer.toString(water_high));
            }
        });
        btn_z_water_high_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                water_high--;
                tv_z_water_high_set.setText(Integer.toString(water_high));
            }
        });

        btn_sensor_info_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url2 = "http://192.168.1.12:8084/Project/Update_Auto_Running.do";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url2,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),"접속실패",Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("numbering",numbering);
                        params.put("fan_run", indoor_humid+"");
                        params.put("wire_run", water_temp+"");
                        params.put("waterhigh_run", salinity+"");
                        params.put("pump_run", water_high+"");
                        params.put("light_run", "1");
                        params.put("stalinity_run", "1");

                        return params;
                    }

                };
                requestQueue.add(request);




            }
        });






        return view;
    }
}