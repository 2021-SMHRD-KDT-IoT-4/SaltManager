package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saltmanager.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Controller_VO;
import Model.K_List_VO;
import Model.Z_List_VO;


public class ControllActurator extends Fragment {

    private ArrayList<Controller_VO> data;
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
    Controller_VO cvo;

    public ControllActurator(Controller_VO cvo) {
        this.cvo = cvo;
    }

    TextView tv_control_all,tv_control_fan,tv_control_pump,
            tv_control_wire,tv_control_light,tv_control_pusher,tv_control_conveyer;
    Switch sc_control_all, sc_control_fan, sc_control_pump, sc_control_wire,
            sc_control_light,sc_control_pusher,sc_control_conveyer;
    TextView tv_state_all, tv_state_fan, tv_state_pump, tv_state_wire,
            tv_state_light,tv_state_pusher, tv_state_conveyer;
    Button btn_send_control_info;

    RequestQueue requestQueue;
    String numbering;

    int c_numbering = 0;
    int c_fan = 0;
    int c_pump = 0;
    int c_wire = 0;
    int c_pusher = 0;
    int c_conveyer = 0;
    int c_light = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controll_acturator, container, false);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        tv_control_all = view.findViewById(R.id.tv_control_all);
        tv_control_fan = view.findViewById(R.id.tv_control_fan);
        tv_control_pump = view.findViewById(R.id.tv_control_pump);
        tv_control_wire = view.findViewById(R.id.tv_control_wire);
        tv_control_light = view.findViewById(R.id.tv_control_light);
        tv_control_pusher = view.findViewById(R.id.tv_control_pusher);
        tv_control_conveyer = view.findViewById(R.id.tv_control_conveyer);

        sc_control_all = view.findViewById(R.id.sc_control_all);
        sc_control_fan = view.findViewById(R.id.sc_control_fan);
        sc_control_pump = view.findViewById(R.id.sc_control_pump);
        sc_control_wire = view.findViewById(R.id.sc_control_wire);
        sc_control_light = view.findViewById(R.id.sc_control_light);
        sc_control_pusher = view.findViewById(R.id.sc_control_pusher);
        sc_control_conveyer = view.findViewById(R.id.sc_control_conveyer);

        tv_state_all = view.findViewById(R.id.tv_state_all);
        tv_state_fan = view.findViewById(R.id.tv_state_fan);
        tv_state_pump = view.findViewById(R.id.tv_state_pump);
        tv_state_wire = view.findViewById(R.id.tv_state_wire);
        tv_state_light = view.findViewById(R.id.tv_state_light);
        tv_state_pusher = view.findViewById(R.id.tv_state_pusher);
        tv_state_conveyer = view.findViewById(R.id.tv_state_conveyer);

        btn_send_control_info = view.findViewById(R.id.btn_send_control_info);



        String url = "http://192.168.1.12:8084/Project/GetAllControl.do";
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
                                int c_fan_now = Integer.parseInt(json3.getString("fan"));
                                int c_pump_now = Integer.parseInt(json3.getString("pump"));
                                int c_wire_now = Integer.parseInt(json3.getString("wire"));
                                int c_pusher_now = Integer.parseInt(json3.getString("pusher"));
                                int c_conveyer_now = Integer.parseInt(json3.getString("conveyer"));
                                int c_light_now = Integer.parseInt(json3.getString("light"));
                                int c_camera_now = Integer.parseInt(json3.getString("camera"));
                                int c_part_now = Integer.parseInt(json3.getString("part"));


                                Controller_VO vo = new Controller_VO(numbering,c_fan_now,c_pump_now,c_wire_now,c_pusher_now,c_conveyer_now,c_light_now,c_camera_now,c_part_now);
                                data.add(vo);
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
                //params.put("req", "1");


                return params;
            }
        };
        requestQueue.add(request);

        return view;
    }
}