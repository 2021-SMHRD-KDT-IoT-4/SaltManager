package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.saltmanager.R;

import java.util.ArrayList;

import Adapter.CameraAdapter;
import Model.CameraVO;

public class CctvFragment extends Fragment {

    ArrayList<CameraVO> data = new ArrayList<CameraVO>();
    ListView lv_cctv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cctv, container, false);

        lv_cctv = view.findViewById(R.id.lv_cctv_list);

        int[] intarr = {1,2,3,4};
        String[] stringArr = {"http://169.254.80.8:8081/", "http://169.254.80.8:8081/", "http://169.254.80.8:8081/","http://169.254.80.8:8081/"};
        for (int i = 0; i < intarr.length; i++){
            CameraVO vo = new CameraVO(intarr[i], stringArr[i]);
            data.add(vo);
        }

        CameraAdapter adapter = new CameraAdapter(getContext(),R.layout.cctv_list,data);
        lv_cctv.setAdapter(adapter);

        return view;
    }
}