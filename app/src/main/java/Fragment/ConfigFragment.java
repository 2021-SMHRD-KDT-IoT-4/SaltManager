package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import Model.K_List_VO;
import Model.Setting_VO;
import Model.Z_List_VO;


public class ConfigFragment extends Fragment {

    RequestQueue requestQueue;
    Button btn_add_set;
    ListView lv_set;
    ArrayList<Setting_VO> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_config, container, false);

        btn_add_set = view.findViewById(R.id.btn_add_set);
        lv_set = view.findViewById(R.id.lv_set);

        btn_add_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFragment(new ConfigAddFragment(),"");
            }
        });




        return view;


    }

    public ArrayList<K_List_VO> K_List_VO(){


        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        ArrayList<K_List_VO> result = new ArrayList<K_List_VO>();
        String url = "http://project-student.ddns.net/Salt/GetAll_K_Detail_Info.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("response",response);
                            JSONObject json = new JSONObject(response);
                            JSONArray json2 = json.getJSONArray("kdata");
                            for (int i = 0; i < json2.length(); i++) {
                                JSONObject json3 = (JSONObject) json2.get(i);
                                int numbering = json3.getInt("numbering");
                                int k_daily_prod = json3.getInt("k_daily_prod");
                                int k_harvest = json3.getInt("k_harvest");
                                int k_place_size = json3.getInt("k_place_size");
                                int k_automode = json3.getInt("k_automode");
                                int node = json3.getInt("node");

                                int k_salinity = json3.getInt("k_salinity");
                                int k_indoor_temp = json3.getInt("k_indoor_temp");
                                int k_indoor_humid = json3.getInt("k_indoor_humid");
                                int k_water_temp = json3.getInt("k_water_temp");
                                int k_wire_temp = json3.getInt("k_wire_temp");
                                int k_water_high = json3.getInt("k_water_high");

                               // K_List_VO vo = new K_List_VO(numbering,k_salinity, k_indoor_temp,k_water_temp,k_wire_temp,k_water_high,k_daily_prod,k_harvest,k_place_size,k_indoor_humid,k_automode,node)
                                //result.add(vo);

                            }


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

        return result;

    }
}