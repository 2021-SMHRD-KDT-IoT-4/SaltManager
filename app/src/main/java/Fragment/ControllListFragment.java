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
        lv = view.findViewById(R.id.lv_controll_list);

        data = new ArrayList<Controller_VO>();

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        adapter = new Controll_list_Adapter((MainActivity) getContext(), R.layout.controll_list, data, mParam1);
        String url = "http://192.168.0.88:8087/Project/GetAllControl.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("response", response);
                            JSONObject json = new JSONObject(response);
                            JSONArray json2 = json.getJSONArray("data");
                            for (int i = 0; i < json2.length(); i++) {

                                JSONObject json3 = (JSONObject) json2.get(i);

                                int numbering = Integer.parseInt(json3.getString("numbering"));
                                int fan = Integer.parseInt(json3.getString("fan"));
                                int pump = Integer.parseInt(json3.getString("pump"));
                                int wire = Integer.parseInt(json3.getString("wire"));
                                int pusher = Integer.parseInt(json3.getString("pusher"));
                                int conveyer = Integer.parseInt(json3.getString("conveyer"));
                                int light = Integer.parseInt(json3.getString("light"));
                                int camera = Integer.parseInt(json3.getString("camera"));
                                int part = Integer.parseInt(json3.getString("part"));

                                if(mParam1.equals("z")){
                                    if(part == 0){
                                        Controller_VO vo = new Controller_VO(numbering, fan, pump, wire, pusher, conveyer, light, camera, part);
                                        data.add(vo);
                                    }
                                }else{
                                    if(part == 1){
                                        Controller_VO vo = new Controller_VO(numbering, fan, pump, wire, pusher, conveyer, light, camera, part);
                                        data.add(vo);
                                    }
                                }

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
        lv.setAdapter(adapter);
        return view;
    }


}