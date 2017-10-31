package com.example.vikta.smartwarmup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExercisesFragment extends Fragment {

    private Button mBtnSquat;
    private Button mBtnBench;
    private Button mBtnDeadlift;
    private Button mBtnCalc;

    private EditText mEditTextSquat;
    private EditText mEditTextBench;
    private EditText mEditTextDeadlift;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_choose_exercise,container,false);

        mBtnSquat=(Button) v.findViewById(R.id.btn_squat);
        mBtnBench=(Button) v.findViewById(R.id.btn_bench);
        mBtnDeadlift=(Button) v.findViewById(R.id.btn_dl);
        mBtnCalc=(Button) v.findViewById(R.id.btn_calc);

        mEditTextSquat=(EditText) v.findViewById(R.id.et_squat);
        mEditTextBench=(EditText) v.findViewById(R.id.et_bench);
        mEditTextDeadlift=(EditText) v.findViewById(R.id.et_dl);

        mBtnSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnBench.setVisibility(View.INVISIBLE);
                mBtnDeadlift.setVisibility(View.INVISIBLE);
                mEditTextSquat.setVisibility(View.VISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
            }
        });

        mBtnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditTextSquat.getText().toString().length()>0 && mEditTextSquat.getText().toString()!="0"){
                    FragmentManager fm=getFragmentManager();
                    Fragment fragment=new ListWeights();
                    fm.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer,fragment).commit();
                }else {
                    Toast.makeText(getContext(), "Введите вес в поле!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
}
