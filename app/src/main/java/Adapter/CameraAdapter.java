package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saltmanager.R;

import java.util.ArrayList;

import Model.CameraVO;

public class CameraAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<CameraVO> data;

    LayoutInflater inflater;

    public CameraAdapter(Context context, int layout, ArrayList<CameraVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(layout, null);
        }
        TextView tv_cctv_numbering = convertView.findViewById(R.id.tv_cctv_num);
        tv_cctv_numbering.setText(data.get(position).getCamera_numbering()+" 번 CCTV");



        return convertView;
    }
}
