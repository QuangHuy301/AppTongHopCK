package com.example.btgkapptonghop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public static final String TAG = DetailFragment.class.getName();

    // TODO: Rename and change types of parameters
    TextView detailname,detaildes;
    ImageView imgcharactor;
    ImageView buttonback;
    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        detailname = view.findViewById(R.id.detailname);
        detaildes = view.findViewById(R.id.detaildes);
        imgcharactor = view.findViewById(R.id.imgcharactor);
        buttonback= view.findViewById(R.id.buttonback);

        Bundle bundle = getArguments();
        if (bundle != null){
            Item item = (Item) bundle.getSerializable("character");
            if (item != null){
                detailname.setText(item.getHoTen());
                detaildes.setText(item.getGioiThieu());
                if (item.getLogo() == 0)
                {
                    imgcharactor.setImageURI(item.getLinkimg());
                }
                else {
                    imgcharactor.setImageResource(item.getLogo());
                }
            }
        }

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}