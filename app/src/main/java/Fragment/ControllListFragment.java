package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class ControllListFragment extends Fragment {

    private static final String ARG_PARAM1 = "ARG_PARAM1";
    private ArrayList<Controller_VO> data;
    private ArrayList<String> numbering_data;
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

            numbering_data = NumberingSet();
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
            for (cnt=0; cnt<numbering_data.size(); cnt++){
                String url = "http://192.168.1.12:8084/Project/ControlGet.do";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.d("respone",response);
                                    JSONObject json = new JSONObject(response);
                                    Log.d("json",json+"");
                                    JSONArray json2 = json.getJSONArray("controll");

                                        JSONObject json3 = (JSONObject) json2.get(1);
                                        String numbering = json3.getString("numbering");


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
                        params.put("numbering", numbering_data.get(cnt));
                        return params;
                    }
                };
                requestQueue.add(request);
            }


        return view;
    }



    private ArrayList<String> NumberingSet(){
     ArrayList<String> result = new ArrayList<String>();
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }

        if(mParam1.equals("z")){
            String url = "http://192.168.1.12:8084/Project/GetAll_Z_Detail_Info.do";
            StringRequest request = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("response",response);
                                JSONObject json = new JSONObject(response);
                                JSONArray json2 = json.getJSONArray("zdata");
                                for (int i = 0; i < json2.length(); i++) {
                                    JSONObject json3 = (JSONObject) json2.get(i);
                                    String numbering = json3.getString("numbering");
                                    result.add(numbering);
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


        }else if(mParam1.equals("k")){
            String url = "http://192.168.1.20:8084/Project/GetAll_K_Detail_Info.do";
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
                                    String numbering = json3.getString("numbering");

                                    result.add(numbering);

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
        }
        return result;
    }


}