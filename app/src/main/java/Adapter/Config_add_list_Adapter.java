package Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saltmanager.R;

import java.util.List;

import Model.Setting_VO;

public class Config_add_list_Adapter extends BaseAdapter {

    Context context;
    int layout;
    List<Setting_VO> data;

    LayoutInflater inflater;

    public Config_add_list_Adapter(Context context, int layout, List<Setting_VO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(layout,null);
        }

        TextView tv_k_place_size = convertView.findViewById(R.id.tv_k_place_size);
        TextView tv_z_place_size = convertView.findViewById(R.id.tv_z_place_size);
        TextView tv_k_numbering = convertView.findViewById(R.id.tv_sensor_numbering);
        TextView tv_z_numbering = convertView.findViewById(R.id.tv_z_numbering);

        tv_k_place_size.setText(data.get(position).getK_place_size());
        tv_z_place_size.setText(data.get(position).getZ_place_size());
        tv_k_numbering.setText(data.get(position).getK_numbering());
        tv_z_numbering.setText(data.get(position).getZ_numbering());


        return convertView;
    }
}
