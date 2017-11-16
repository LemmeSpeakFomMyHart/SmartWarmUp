package com.example.vikta.smartwarmup;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ExercisesFragment extends Fragment {

    private int mWorkWeight;
    private int mMaxWeight;
    private String mEditTextInput;

    private Button mBtnSquat;
    private Button mBtnBench;
    private Button mBtnDeadlift;
    private Button mBtnCalc;

    private String TAG_KEY_WEIGHT = "tag_weight";
    private String TAG_PRESSED_BUTTON = "tag_pressed";
    private String mPressedButton;
    private String mMeasure;

    private TextView mTextView;
    private EditText mEditTextSquat;
    private EditText mEditTextBench;
    private EditText mEditTextDeadlift;
    private RelativeLayout mRelativeLayout;
    private SharedPreferences mSharedPreferences;

    public final static String APP_WEIGHT_MEASURE = "measure_type";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_exercise, container, false);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mMeasure = mSharedPreferences.getString(APP_WEIGHT_MEASURE, "");

        mRelativeLayout = (RelativeLayout) v.findViewById(R.id.exercise_rel_layout);
        mRelativeLayout.setBackground(getResources().getDrawable(R.drawable.exercises));

        mTextView = (TextView) v.findViewById(R.id.textView);

        mBtnSquat = (Button) v.findViewById(R.id.btn_squat);
        mBtnBench = (Button) v.findViewById(R.id.btn_bench);
        mBtnDeadlift = (Button) v.findViewById(R.id.btn_dl);
        mBtnCalc = (Button) v.findViewById(R.id.btn_calc);

        mEditTextSquat = (EditText) v.findViewById(R.id.et_squat);
        mEditTextBench = (EditText) v.findViewById(R.id.et_bench);
        mEditTextDeadlift = (EditText) v.findViewById(R.id.et_dl);

        mBtnSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnBench.setEnabled(true);
                mBtnDeadlift.setEnabled(true);
                mBtnSquat.setEnabled(false);
                mEditTextSquat.setVisibility(View.VISIBLE);
                mEditTextBench.setVisibility(View.INVISIBLE);
                mEditTextDeadlift.setVisibility(View.INVISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
                mEditTextSquat.requestFocus();
                mPressedButton = "squat";
                mRelativeLayout.setBackground(getResources()
                        .getDrawable(R.drawable.ray_williams_519x316));
                mTextView.setText(R.string.type_workweight);
            }
        });

        mBtnBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnBench.setEnabled(false);
                mBtnDeadlift.setEnabled(true);
                mBtnSquat.setEnabled(true);
                mEditTextSquat.setVisibility(View.INVISIBLE);
                mEditTextBench.setVisibility(View.VISIBLE);
                mEditTextDeadlift.setVisibility(View.INVISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
                mEditTextBench.requestFocus();
                mPressedButton = "bench";
                mRelativeLayout.setBackground(getResources()
                        .getDrawable(R.drawable.sarychev_593x395));
                mTextView.setText(R.string.type_workweight);
            }
        });
        mBtnDeadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtnBench.setEnabled(true);
                mBtnDeadlift.setEnabled(false);
                mBtnSquat.setEnabled(true);
                mEditTextSquat.setVisibility(View.INVISIBLE);
                mEditTextBench.setVisibility(View.INVISIBLE);
                mEditTextDeadlift.setVisibility(View.VISIBLE);
                mBtnCalc.setVisibility(View.VISIBLE);
                mEditTextDeadlift.requestFocus();
                mPressedButton = "deadlift";
                mRelativeLayout.setBackground(getResources()
                        .getDrawable(R.drawable.eddy_hall_593x395));
                mTextView.setText(R.string.type_workweight);
            }
        });

        mBtnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWorkWeight(mPressedButton);
                if (mWorkWeight > 0) {
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

    private void getWorkWeight(String exercise) {
        switch (exercise) {
            case "squat":
                mMaxWeight = 300;
                if (mMeasure.equals("lbs")) {
                    mMaxWeight = mMaxWeight * 22 / 10;
                }
                checkEditText(mEditTextSquat, mMaxWeight);
                break;
            case "bench":
                mMaxWeight = 250;
                if (mMeasure.equals("lbs")) {
                    mMaxWeight = mMaxWeight * 22 / 10;
                }
                checkEditText(mEditTextBench, mMaxWeight);
                break;
            case "deadlift":
                mMaxWeight = 300;
                if (mMeasure.equals("lbs")) {
                    mMaxWeight = mMaxWeight * 22 / 10;
                }
                checkEditText(mEditTextDeadlift, mMaxWeight);
                break;
        }
    }

    private void checkEditText(EditText editText, int maxWeight) {
        mWorkWeight = 0;
        mEditTextInput = editText.getText().toString();
        //проверяем на заполненность edittext и неравенство нулю
        if (mEditTextInput.length() == 0 || mEditTextInput.contentEquals("0")) {
            Snackbar.make(getView(), "Введите вес в поле!", Snackbar.LENGTH_LONG).show();
        } else if (mEditTextInput.length() > 0 && !mEditTextInput.contentEquals("0") &&
                Integer.parseInt(mEditTextInput) <= maxWeight) {
            mWorkWeight = Integer.parseInt(mEditTextInput);
        } else if (mEditTextInput.length() > 0 &&
                !mEditTextInput.contentEquals("0") && Integer.parseInt(mEditTextInput) > maxWeight) {
            Snackbar.make(getView(), "Эта программа " +
                    "пока не рассчитана" + " для весов в этом упражнении >" + maxWeight
                    + " " + mMeasure + " :)", Snackbar.LENGTH_LONG).show();
        }
    }
}
