package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.saltmanager.MainActivity;
import com.example.saltmanager.R;

import java.util.List;

import Fragment.ControllFragment;
import Fragment.ControllSensorFragment;
import Model.Controller_VO;

public class Controll_list_Adapter extends BaseAdapter {

    Context context;
    int layout;
    List<Controller_VO> data;
    String arg;
    LayoutInflater inflater;



    public  Controll_list_Adapter(Context context, int layout, List<Controller_VO> data,String arg){
        this.context = context;
        this.layout = layout;
        this.data = data;
        this.arg = arg;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = inflater.inflate(layout, null);
        }

        TextView tv_controll_numbering = view.findViewById(R.id.tv_controll_numbering);
        TextView tv_controll_zk = view.findViewById(R.id.tv_controll_zk);

        Button btn_controll_move = view.findViewById(R.id.btn_controll_move);
        tv_controll_numbering.setText(data.get(i).getC_numbering()+"");
        if (arg.equals("z")){
            tv_controll_zk.setText("증발지");
        }else if(arg.equals("k")) {
            tv_controll_zk.setText("결정지");
        }




        return view;
    }
}
