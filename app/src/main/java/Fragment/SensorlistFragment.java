package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Adapter.Sensor_list_Adapter;
import Model.K_List_VO;
import Model.Z_List_VO;


public class SensorlistFragment extends Fragment {


    private static final String ARG_PARAM1 = "ARG_PARAM1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    Sensor_list_Adapter adapter;
    ListView lv;
    private ArrayList<K_List_VO> k_data;
    private ArrayList<Z_List_VO> z_data;
    RequestQueue requestQueue;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensorlist, container, false);
        lv = view.findViewById(R.id.lv_sensorlist);
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        if (mParam1.equals("z")){
            z_data = new ArrayList<Z_List_VO>();
            adapter = new Sensor_list_Adapter((MainActivity)getContext(),R.layout.detail_item,z_data,mParam1,1);
            String url = "http://192.168.1.12:8084/Project/GetAll_Z_Detail_Info.do";
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("test",response);
                                JSONObject json = new JSONObject(response);
                                JSONArray json2 = json.getJSONArray("data");
                                Log.d("data",json2+"");
                                for (int i = 0; i < json2.length(); i++) {
                                    JSONObject json3 = (JSONObject) json2.get(i);
                                    int numbering = Integer.parseInt(json3.getString("numbering"));
                                    int z_place_size_now = Integer.parseInt(json3.getString("z_place_size"));
                                    int z_pump_move_now = Integer.parseInt(json3.getString("z_pump_move"));
                                    int z_salinity_now = Integer.parseInt(json3.getString("z_salinity"));
                                    int z_indoor_temp_now = Integer.parseInt(json3.getString("z_indoor_temp"));
                                    int z_indoor_humid_now = Integer.parseInt(json3.getString("z_indoor_humid"));
                                    int z_water_temp_now = Integer.parseInt(json3.getString("z_water_temp"));
                                    int z_wire_temp_now = Integer.parseInt(json3.getString("z_wire_temp"));
                                    int z_water_high_now = Integer.parseInt(json3.getString("z_water_high"));

                                    Z_List_VO vo = new Z_List_VO(numbering,z_salinity_now,z_indoor_temp_now,z_indoor_humid_now,z_water_temp_now,z_wire_temp_now,z_water_high_now,z_place_size_now,z_pump_move_now);
                                    z_data.add(vo);
                                }

                                adapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();

                        }
                    }

            ) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("req", "1");


                    return params;
                }
            };
            requestQueue.add(request);
        }else if(mParam1.equals("k")){
            k_data = new ArrayList<K_List_VO>();
            adapter = new Sensor_list_Adapter((MainActivity)getContext(),R.layout.detail_item,k_data,mParam1);
            String url = "192.168.1.12:8084/Project/GetAll_K_Detail_Info.do";
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("response",response);
                                JSONObject json = new JSONObject(response);
                                JSONArray json2 = json.getJSONArray("data");
                                for (int i = 0; i < json2.length(); i++) {
                                    JSONObject json3 = (JSONObject) json2.get(i);
                                    int numbering = Integer.parseInt(json3.getString("numbering"));
                                    int k_dail_prod = Integer.parseInt(json3.getString("k_daily_prod"));
                                    String k_harvest = json3.getString("k_harvest");
                                    int k_place_size = Integer.parseInt(json3.getString("k_place_size"));
                                    int k_automode = Integer.parseInt(json3.getString("k_automode"));
                                    int node = Integer.parseInt(json3.getString("node"));

                                    int k_salinity_now = Integer.parseInt(json3.getString("k_salinity"));
                                    int k_indoor_temp_now = Integer.parseInt(json3.getString("k_indoor_temp"));
                                    int k_indoor_humid_now = Integer.parseInt(json3.getString("k_indoor_humid"));
                                    int k_water_temp_now = Integer.parseInt(json3.getString("k_water_temp"));
                                    int k_wire_temp_now = Integer.parseInt(json3.getString("k_wire_temp"));
                                    int k_water_high_now = Integer.parseInt(json3.getString("k_water_high"));

                                    K_List_VO vo = new K_List_VO(numbering,k_salinity_now,k_indoor_temp_now,k_water_temp_now,k_wire_temp_now,k_water_high_now,k_dail_prod,k_harvest,k_place_size,k_indoor_humid_now,k_automode,node);
                                    k_data.add(vo);

                                }

                                adapter.notifyDataSetChanged();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();

                        }
                    }

            ) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("req", "1");


                    return params;
                }
            };
            requestQueue.add(request);
        }

        lv.setAdapter(adapter);
        return view;
    }

}