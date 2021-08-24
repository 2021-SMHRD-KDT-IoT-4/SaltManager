package Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saltmanager.LoginActivity;
import com.example.saltmanager.MainActivity;
import com.example.saltmanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Setting_VO;

public class ConfigAddFragment extends Fragment {

    EditText et_z_add_size, et_k_add_size;
    Button btn_add_config;

    String id;
    int z_place_size=0, k_place_size=0;
    RequestQueue requestQueue;
    ArrayList<Setting_VO> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_config_add, container, false);

        et_z_add_size = view.findViewById(R.id.et_z_add_size);
        et_k_add_size = view.findViewById(R.id.et_k_add_size);
        btn_add_config = view.findViewById(R.id.btn_add_cofig);


        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue((MainActivity)getActivity());

        }

        SharedPreferences spf = getActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = spf.edit();

        id = "admin";

        Setting_VO vo = new Setting_VO(id,z_place_size,k_place_size);

        btn_add_config.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String z_place_size1 = et_z_add_size.getText().toString();
                String k_place_size1 = et_k_add_size.getText().toString();

                String url = "http://192.168.1.9:8084/Project/InsertSaltern.do";
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    Toast.makeText((MainActivity)getActivity(), "전송성공", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText((MainActivity)getActivity(), "전송실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText((MainActivity)getActivity(), "실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("id", id);
                        params.put("z_place_size", z_place_size1);
                        params.put("k_place_size", k_place_size1) ;

                        return params;
                    }

                };


                requestQueue.add(request);

            }

        });


        return view;
    }
}