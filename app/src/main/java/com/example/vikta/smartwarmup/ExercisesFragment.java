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

    private int mWorkWeight;
    private int mMaxWeight;
    private String mEditTextInput;

    private Button mBtnSquat;
    private Button mBtnBench;
    private Button mBtnDeadlift;
    private Button mBtnCalc;

    private String TAG_KEY_WEIGHT="tag_weight";
    private String TAG_PRESSED_BUTTON="tag_pressed";
    private String mPressedButton;

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
                mPressedButton="squat";
            }
        });

        mBtnBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnSquat.setVisibility(View.GONE);
                mBtnDeadlift.setVisibility(View.INVISIBLE);
                mEditTextBench.setVisibility(View.VISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
                mPressedButton="bench";
            }
        });
        mBtnDeadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnBench.setVisibility(View.INVISIBLE);
                mBtnSquat.setVisibility(View.INVISIBLE);
                mEditTextDeadlift.setVisibility(View.VISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
                mPressedButton="deadlift";
            }
        });

        mBtnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWorkWeight(mPressedButton);
                if (mWorkWeight>0){
                    FragmentManager fm = getFragmentManager();
                    Fragment fragment = new ListWeights();
                    Bundle bundle = new Bundle();
                    bundle.putInt(TAG_KEY_WEIGHT, mWorkWeight);
                    bundle.putString(TAG_PRESSED_BUTTON, mPressedButton);
                    fragment.setArguments(bundle);
                    fm.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, fragment).commit();
                }
            }
        });
        return v;
    }

    private void getWorkWeight(String exercise){
        switch (exercise){
            case "squat":
                mMaxWeight=300;
                checkEditText(mEditTextSquat,mMaxWeight);
                break;
            case "bench":
                mMaxWeight=250;
                checkEditText(mEditTextBench,mMaxWeight);
                break;
            case "deadlift":
                mMaxWeight=300;
                checkEditText(mEditTextDeadlift,mMaxWeight);
                break;
        }
    }

    private void checkEditText(EditText editText, int maxWeight){
        mWorkWeight=0;
        mEditTextInput=editText.getText().toString();
        //проверяем на заполненность edittext и неравенство нулю
        if (mEditTextInput.length()==0 || mEditTextInput.contentEquals("0")) {
            Toast.makeText(getActivity(), "Введите вес в поле!", Toast.LENGTH_SHORT).show();
        }else if (mEditTextInput.length()>0 && !mEditTextInput.contentEquals("0")&&
                Integer.parseInt(mEditTextInput)<=maxWeight){
            mWorkWeight=Integer.parseInt(mEditTextInput);
        }else if (mEditTextInput.length()>0 &&
                !mEditTextInput.contentEquals("0")&& Integer.parseInt(mEditTextInput)>maxWeight){
            Toast.makeText(getContext(), "К сожалению" + ", эта программа " +
                    "пока не рассчитана" + " на таких сильных людей (для весов>"+maxWeight
                    + "кг) :)",Toast.LENGTH_LONG).show();
        }
    }
}
