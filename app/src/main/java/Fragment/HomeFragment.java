package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
    TextView tv_weather;
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
        tv_weather = view.findViewById(R.id.tv_weather);
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        }
        data = new ArrayList<OutPut_VO>();
        adapter = new Daily_OutPut_Adapter((MainActivity)getContext(),R.layout.prod_item,data);

        String url = "http://192.168.1.73:8087/Project/GetOutPut.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("res@############",response);
                            JSONObject json = new JSONObject(response);
                            JSONArray json2 = json.getJSONArray("data");
                            for (int i = 0; i < json2.length(); i++) {
                                JSONObject json3 = (JSONObject) json2.get(i);

                                date_search = json3.getString("date_search");
                                prod = json3.getInt("prod");
                                OutPut_VO vo = new OutPut_VO(date_search,prod);
                                data.add(vo);


                            }
                            lv.setAdapter(adapter);
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


        url = "https://api.openweathermap.org/data/2.5/weather?lat=35.2053724&lon=126.3392459&appid=e5d8c3a021c78fea24e0228511195fe4&units=metric";
        StringRequest request2 = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response).getJSONObject("main");
                            String temp = jObject.getString("temp");
                            String temp_min = jObject.getString("temp_min");
                            String temp_max = jObject.getString("temp_max");
                            String humidity = jObject.getString("humidity");

                            String weather_info = "현재 기온 : " + temp + "º\n최저 기온 : " +  temp_min + "º\n최고 기온 : " + temp_max + "º\n현재 습도 : " + humidity + "%";
                            tv_weather.setText(weather_info);

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
                });
        requestQueue.add(request2);
        return view;
    }
}