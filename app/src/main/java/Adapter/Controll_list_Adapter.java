package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(arg.equals("z")){

        }else if (arg.equals("k")){

        }


        return convertView;
    }
}
