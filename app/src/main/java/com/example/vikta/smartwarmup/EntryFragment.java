package com.example.vikta.smartwarmup;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EntryFragment extends Fragment {

    private Button mButtonNextFragment;

    public EntryFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_entry,container,false);
        mButtonNextFragment=(Button) v.findViewById(R.id.btn_next);
        mButtonNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getFragmentManager();
                Fragment fragment=new ExercisesFragment();
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,fragment).commit();
            }
        });
        return v;
    }
}
