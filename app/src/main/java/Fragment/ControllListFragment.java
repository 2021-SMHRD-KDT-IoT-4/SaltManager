package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

import Adapter.Controll_list_Adapter;
import Adapter.Sensor_list_Adapter;
import Model.Controller_VO;
import Model.K_List_VO;
import Model.Z_List_VO;

public class ControllListFragment extends Fragment {

    private static final String ARG_PARAM1 = "ARG_PARAM1";
    Controll_list_Adapter adapter;
    ListView lv;
    private ArrayList<Controller_VO> data;
    //private ArrayList<String> numbering_data;

    RequestQueue requestQueue;
    int cnt = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controll_list, container, false);

        data = new ArrayList<Controller_VO>();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        if (mParam1.equals("z")){

            adapter = new Controll_list_Adapter((MainActivity)getContext(),R.layout.controll_list,data,mParam1);
            String url = "http://192.168.1.12:8084/Project/Get_All_Controll_Controller.do";
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

                                    int z_c_fan_now = Integer.parseInt(json3.getString("fan"));
                                    int z_c_pump_now = Integer.parseInt(json3.getString("pump_now"));
                                    int z_c_wire_now = Integer.parseInt(json3.getString("wire"));
                                    int z_c_pusher_now = Integer.parseInt(json3.getString("pusher"));
                                    int z_c_conveyer_now = Integer.parseInt(json3.getString("conveyer"));
                                    int z_c_light_now = Integer.parseInt(json3.getString("light"));
                                    int z_c_camera_now = Integer.parseInt(json3.getString("camera"));
                                    int z_c_part_now = Integer.parseInt(json3.getString("part"));


                                    Controller_VO vo = new Controller_VO(numbering,z_c_fan_now,z_c_pump_now,z_c_wire_now,z_c_pusher_now,z_c_conveyer_now,z_c_light_now,z_c_camera_now,z_c_part_now);
                                    data.add(vo);
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

            adapter = new Controll_list_Adapter((MainActivity)getContext(),R.layout.controll_list,data,mParam1);
            String url = "http://192.168.1.12:8084/Project/Get_All_Controll_Controller.do";
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

                                    int k_c_fan_now = Integer.parseInt(json3.getString("fan"));
                                    int k_c_pump_now = Integer.parseInt(json3.getString("pump"));
                                    int k_c_wire_now = Integer.parseInt(json3.getString("wire"));
                                    int k_c_pusher_now = Integer.parseInt(json3.getString("pusher"));
                                    int k_c_conveyer_now = Integer.parseInt(json3.getString("conveyer"));
                                    int k_c_light_now = Integer.parseInt(json3.getString("light"));
                                    int k_c_camera_now = Integer.parseInt(json3.getString("camera"));
                                    int k_c_part_now = Integer.parseInt(json3.getString("part"));

                                    Controller_VO vo = new Controller_VO(numbering,k_c_fan_now,k_c_pump_now,k_c_wire_now,k_c_pusher_now,k_c_conveyer_now,k_c_light_now,k_c_camera_now,k_c_part_now);
                                    data.add(vo);

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