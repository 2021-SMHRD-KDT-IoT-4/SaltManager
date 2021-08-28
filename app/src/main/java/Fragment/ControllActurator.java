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
import android.widget.CompoundButton;
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

    TextView tv_control_all, tv_control_fan, tv_control_pump,
            tv_control_wire, tv_control_light, tv_control_pusher, tv_control_conveyer;
    Switch sc_control_all, sc_control_fan, sc_control_pump, sc_control_wire,
            sc_control_light, sc_control_pusher, sc_control_conveyer;
    TextView tv_state_all, tv_state_fan, tv_state_pump, tv_state_wire,
            tv_state_light, tv_state_pusher, tv_state_conveyer;
    Button btn_send_control_info;

    RequestQueue requestQueue;
    boolean settingFlag = true;
    int numbering;
    int c_autoMode = 0;
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
        numbering = cvo.getC_numbering();
        c_fan = cvo.getC_fan();
        c_pump = cvo.getC_pump();
        c_wire = cvo.getC_wire();
        c_pusher = cvo.getC_pusher();
        c_conveyer = cvo.getC_conveyer();
        c_light = cvo.getC_light();
        c_autoMode = cvo.getAutoMode();

        sc_control_all = view.findViewById(R.id.sc_control_all);
        sc_control_fan = view.findViewById(R.id.sc_control_fan);
        sc_control_pump = view.findViewById(R.id.sc_control_pump);
        sc_control_wire = view.findViewById(R.id.sc_control_wire);
        sc_control_light = view.findViewById(R.id.sc_control_light);
        sc_control_pusher = view.findViewById(R.id.sc_control_pusher);
        sc_control_conveyer = view.findViewById(R.id.sc_control_conveyer);

        btn_send_control_info = view.findViewById(R.id.btn_send_control_info);


        if (cvo.getAutoMode() == 1) {
            sc_control_all.setChecked(true);
        } else {
            sc_control_all.setChecked(false);
        }

        if (cvo.getC_fan() == 1) {
            sc_control_fan.setChecked(true);
        } else {
            sc_control_fan.setChecked(false);
        }

        if (cvo.getC_pump() == 1) {
            sc_control_pump.setChecked(true);
        } else {
            sc_control_pump.setChecked(false);

        }

        if (cvo.getC_wire() == 1) {
            sc_control_wire.setChecked(true);
        } else {
            sc_control_wire.setChecked(false);
        }

        if (cvo.getC_light() == 1) {
            sc_control_light.setChecked(true);
        } else {
            sc_control_light.setChecked(false);
        }

        if (cvo.getC_pusher() == 1) {
            sc_control_pusher.setChecked(true);
        } else {
            sc_control_pusher.setChecked(false);
        }

        if (cvo.getC_conveyer() == 1) {
            sc_control_conveyer.setChecked(true);
        } else {
            sc_control_conveyer.setChecked(false);
        }

        if (cvo.getAutoMode() == 1) {
            sc_control_all.setChecked(true);
            Log.d("tttt?", cvo.getAutoMode() + "       sdfsdsdfsdfsdfsdfsdf");
        } else {
            sc_control_all.setChecked(false);
            Log.d("ffff?", cvo.getAutoMode() + "       sdfsdsdfsdfsdfsdfsdf");
        }


        sc_control_fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_fan = 1;
                } else {
                    c_fan = 0;
                }

            }
        });

        sc_control_pump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_pump = 1;
                } else {
                    c_pump = 0;
                }

            }
        });

        sc_control_wire.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_wire = 1;
                } else {
                    c_wire = 0;
                }

            }
        });

        sc_control_pusher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_pusher = 1;
                } else {
                    c_pusher = 0;
                }

            }
        });

        sc_control_conveyer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_conveyer = 1;
                } else {
                    c_conveyer = 0;
                }

            }
        });

        sc_control_light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    c_light = 1;
                } else {
                    c_light = 0;
                }

            }
        });


        sc_control_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true) {
                    c_autoMode = 1;
                } else {
                    c_autoMode = 0;
                }

                if (settingFlag) {
                    settingFlag = false;
                } else {
                    String url2 = "http://192.168.1.73:8087/Project/Update_Controll_AutoMode.do";
                    StringRequest request2 = new StringRequest(
                            Request.Method.POST,

                            url2,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("1")) {
                                        Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getContext(), "접속실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<>();
                            params.put("numbering", numbering + "");
                            params.put("autoMode", c_autoMode + "");


                            return params;
                        }

                    };

                    requestQueue.add(request2);
                }

            }
        });

        btn_send_control_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url2 = "http://192.168.1.73:8087/Project/Update_All_Controll.do";
                StringRequest request2 = new StringRequest(
                        Request.Method.POST,

                        url2,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("1")) {
                                    Toast.makeText(getContext(), response + "", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "접속실패", Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        params.put("numbering", numbering + "");
                        params.put("fan", c_fan + "");
                        params.put("pump", c_pump + "");
                        params.put("wire", c_wire + "");
                        params.put("pusher", c_pusher + "");
                        params.put("conveyer", c_conveyer + "");
                        params.put("light", c_light + "");
                        params.put("camera", "1");
                        params.put("req", "1");


                        return params;
                    }

                };

                requestQueue.add(request2);


            }
        });


        return view;
    }
}