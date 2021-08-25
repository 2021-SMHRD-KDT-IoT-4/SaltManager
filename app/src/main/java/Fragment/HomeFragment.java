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

import Adapter.Daily_OutPut_Adapter;
import Model.OutPut_VO;


public class HomeFragment extends Fragment {

    Daily_OutPut_Adapter adapter;
    ListView lv;
    ArrayList<OutPut_VO> data;
    RequestQueue requestQueue;
    int numbering;
    String date_search;
    int prod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        data = new ArrayList<>();
        lv = view.findViewById(R.id.lv_daily_pord);

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        data = new ArrayList<OutPut_VO>();
        adapter = new Daily_OutPut_Adapter((MainActivity)getContext(),R.layout.prod_item,data);
        String url = "http://192.168.1.12:8084/Project/GetAll_K_Detail_Info.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray json2 = json.getJSONArray("data");
                            for (int i = 0; i < json2.length(); i++) {
                                JSONObject json3 = (JSONObject) json2.get(i);

                                numbering = json3.getInt("numbering");
                                date_search = json3.getString("k_harvest");
                                prod = json3.getInt("k_daily_prod");
                                OutPut_VO vo = new OutPut_VO(numbering,date_search,prod);
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
        lv.setAdapter(adapter);
        return view;
    }
}