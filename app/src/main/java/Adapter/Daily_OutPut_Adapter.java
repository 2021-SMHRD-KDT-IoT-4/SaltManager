package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saltmanager.R;

import java.util.List;

import Model.OutPut_VO;

public class Daily_OutPut_Adapter extends BaseAdapter {

    Context context;
    int layout;
    List<OutPut_VO> data;

    LayoutInflater inflater;



    public Daily_OutPut_Adapter(Context context, int layout, List<OutPut_VO> data) {
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
        TextView tv_prod_date = convertView.findViewById(R.id.tv_prod_date);
        TextView tv_dailly_output = convertView.findViewById(R.id.tv_dailly_output);


        tv_prod_date.setText(data.get(position).getDate());
        tv_dailly_output.setText(data.get(position).getProd()+"");


        return convertView;
    }
}
