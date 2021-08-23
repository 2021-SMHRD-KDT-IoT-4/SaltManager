package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.saltmanager.MainActivity;
import com.example.saltmanager.R;

public class ControllFragment extends Fragment {

    Button btn_controll_z, btn_controll_k;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_controll, container, false);

        btn_controll_z =view.findViewById(R.id.btn_controll_z);
        btn_controll_k =view.findViewById(R.id.btn_controll_k);


        btn_controll_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFragment(new ControllListFragment(),"k");
            }
        });

        btn_controll_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).changeFragment(new ControllListFragment(),"z");
            }
        });
        return view;
    }
}