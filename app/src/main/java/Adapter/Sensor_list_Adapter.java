package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.saltmanager.R;

import java.util.ArrayList;
import java.util.List;

import Model.K_List_VO;
import Model.Z_List_VO;

public class Sensor_list_Adapter extends BaseAdapter {

    Context context;
    int layout;
    List<K_List_VO> k_data;
    List<Z_List_VO> z_data;
    String arg;
    LayoutInflater inflater;

    public Sensor_list_Adapter(Context context, int layout, ArrayList<Z_List_VO> z_data, String arg,int i) {
        this.context = context;
        this.layout = layout;
        this.z_data = z_data;
        this.arg = arg;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Sensor_list_Adapter(Context context, int layout, ArrayList<K_List_VO> k_data, String arg) {
        this.context = context;
        this.layout = layout;
        this.k_data = k_data;
        this.arg = arg;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        int result = 0;
        if (arg.equals("z")){
            result = z_data.size();
        }else if(arg.equals("k")){
            result = k_data.size();
        }

        return result;
    }

    @Override
    public Object getItem(int i) {
        Object result = null;
        if (arg.equals("z")){
            result = z_data.get(i);
        }else if(arg.equals("k")){
            result = k_data.get(i);
        }
        return result;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(layout, null);
        }

        TextView tv_sensor_numbering = view.findViewById(R.id.tv_sensor_numbering);
        TextView tv_sensor_zk = view.findViewById(R.id.tv_sensor_zk);
        TextView tv_sensor_indoorTemp = view.findViewById(R.id.tv_sensor_indoor_temp);
        TextView tv_sensor_waterTemp = view.findViewById(R.id.tv_sensor_water_temp);
        Button btn_sensor_move = view.findViewById(R.id.btn_move_sen_);

        if (arg.equals("z")){
                tv_sensor_numbering.setText(z_data.get(i).getNumbering()+"");
                tv_sensor_zk.setText("증발지");
                tv_sensor_indoorTemp.setText(z_data.get(i).getZ_indoor_temp()+"");
                tv_sensor_waterTemp.setText(z_data.get(i).getZ_water_temp()+"");
        }else if(arg.equals("k")) {
                tv_sensor_numbering.setText(k_data.get(i).getNumbering()+"");
                tv_sensor_zk.setText("결정지");
                tv_sensor_indoorTemp.setText(k_data.get(i).getK_indoor_temp()+"");
                tv_sensor_waterTemp.setText(k_data.get(i).getK_water_temp()+"");
        }


        return view;
    }
}
