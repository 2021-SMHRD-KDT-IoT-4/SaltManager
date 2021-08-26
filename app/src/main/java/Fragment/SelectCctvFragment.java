package Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.saltmanager.R;

import java.util.ArrayList;

import Model.CameraVO;


public class SelectCctvFragment extends Fragment {

    CameraVO data;
    WebView wv;
    public SelectCctvFragment(CameraVO data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_cctv, container, false);
        wv = view.findViewById(R.id.wv_cctv);
        SharedPreferences spf = getActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("adress",data.getCamera_address());

        String address =spf.getString("adress", "http://192.168.137.63:8081/");
        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(address);
        return view;
    }
}