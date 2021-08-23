package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.saltmanager.MainActivity;
import com.example.saltmanager.R;


public class SensorFragment extends Fragment {

    Button btn_GoTO_Z,btn_GoTO_K;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);


        btn_GoTO_K = view.findViewById(R.id.btn_GoTO_K);

        btn_GoTO_K.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).changeFragment(new SensorlistFragment(),"K");
            }
        });



        return view;
    }
}