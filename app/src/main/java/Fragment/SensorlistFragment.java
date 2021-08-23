package Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.saltmanager.R;


public class SensorlistFragment extends Fragment {


    private static final String ARG_PARAM1 = "ARG_PARAM1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            Toast.makeText(getContext(), mParam1, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensorlist, container, false);





        return view;
    }
}