package com.example.vikta.smartwarmup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.vikta.smartwarmup.ExercisesFragment.APP_WEIGHT_MEASURE;

public class ListWeights extends Fragment {

    private ArrayList<Integer> mPercentsWeightArray;
    private ArrayList<Integer> mWarmUpArray;
    private ArrayList<String> mNumSetArray;

    private double mWorkWeight;
    private int[] mPercentsArray;
    private String[] mSetsRepsArray;
    private String mMeasure;
    private SharedPreferences mSharedPreferences;

    private String TAG_KEY_WEIGHT = "tag_weight";
    private String TAG_PRESSED_BUTTON = "tag_pressed";

    private String mButtonPressed;

    private RecyclerView mWeightRecyclerView;
    private WeightAdapter mAdapter;
    private TextView mWorkWeightTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mSharedPreferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        mMeasure=mSharedPreferences.getString(APP_WEIGHT_MEASURE,"");

        mWeightRecyclerView = (RecyclerView) v.findViewById(R.id.weight_recycler_view);
        mWorkWeightTextView = (TextView) v.findViewById(R.id.work_set_text_view);
        mWeightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        creatingWeights();

        updateUI();

        return v;
    }

    private void creatingWeights() {
        WeightLab.get(getActivity()).clearWeights();
        Bundle bundle = getArguments();
        mWorkWeight = bundle.getInt(TAG_KEY_WEIGHT);
        mButtonPressed = bundle.getString(TAG_PRESSED_BUTTON);
        mWorkWeightTextView.setText(mWorkWeight + " " + mMeasure);
        updateArrays(mButtonPressed);

        mPercentsWeightArray = new ArrayList<>();
        for (int percent : mPercentsArray) {
            mPercentsWeightArray.add(percent);
        }
        mNumSetArray = new ArrayList<>(Arrays.asList(mSetsRepsArray));

        mWarmUpArray = Weight.calcWarmUp(mWorkWeight, mPercentsWeightArray, mButtonPressed);
        for (int i = 0; i < mWarmUpArray.size(); i++) {
            Weight mWeight = new Weight();
            mWeight.setSet(i + 1);
            mWeight.setWeight(mWarmUpArray.get(i));
            mWeight.setNumSets(mNumSetArray.get(i));
            WeightLab.get(getActivity()).addWeight(mWeight);
        }
    }

    private void updateArrays(String exercise) {
        switch (exercise) {
            case "squat":
                if ((mMeasure.equals("кг")&&mWorkWeight <= 100)||(mMeasure.equals("lbs")&&mWorkWeight <= 100*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.squat_percents_do_100kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.squat_sets_reps_do_100kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 100 && mWorkWeight <= 150)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 100*2.2 && mWorkWeight <= 150*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.squat_percents_ot_100kg_do_150kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.squat_sets_reps_ot_100kg_do_150kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 150 && mWorkWeight <= 200)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 150*2.2 && mWorkWeight <= 200*2.2)){
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.squat_percents_ot_150kg_do_200kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.squat_sets_reps_ot_150kg_do_200kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 200 && mWorkWeight <= 250)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 200*2.2 && mWorkWeight <= 250*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.squat_percents_ot_200kg_do_250kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.squat_sets_reps_ot_200kg_do_250kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 250 && mWorkWeight <= 300)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 250*2.2 && mWorkWeight <= 300*2.2)){
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.squat_percents_ot_250kg_do_300kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.squat_sets_reps_ot_250kg_do_300kg);
                }
                break;
            case "bench":
                if ((mMeasure.equals("кг")&&mWorkWeight <= 100)||(mMeasure.equals("lbs")&&mWorkWeight <= 100*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.bench_percents_do_100kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.bench_sets_reps_do_100kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 100 && mWorkWeight <= 150)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 100*2.2 && mWorkWeight <= 150*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.bench_percents_ot_100kg_do_150kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.bench_sets_reps_ot_100kg_do_150kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 150 && mWorkWeight <= 200)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 150*2.2 && mWorkWeight <= 200*2.2)){
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.bench_percents_ot_150kg_do_200kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.bench_sets_reps_ot_150kg_do_200kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 200 && mWorkWeight <= 250)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 200*2.2 && mWorkWeight <= 250*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.bench_percents_ot_200kg_do_250kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.bench_sets_reps_ot_200kg_do_250kg);
                }
                break;
            case "deadlift":
                if ((mMeasure.equals("кг")&&mWorkWeight <= 100)||(mMeasure.equals("lbs")&&mWorkWeight <= 100*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.deadlift_percents_do_100kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.deadlift_sets_reps_do_100kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 100 && mWorkWeight <= 150)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 100*2.2 && mWorkWeight <= 150*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.deadlift_percents_ot_100kg_do_150kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.deadlift_sets_reps_ot_100kg_do_150kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 150 && mWorkWeight <= 200)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 150*2.2 && mWorkWeight <= 200*2.2)){
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.deadlift_percents_ot_150kg_do_200kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.deadlift_sets_reps_ot_150kg_do_200kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 200 && mWorkWeight <= 250)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 200*2.2 && mWorkWeight <= 250*2.2)) {
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.deadlift_percents_ot_200kg_do_250kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.deadlift_sets_reps_ot_200kg_do_250kg);
                }
                if ((mMeasure.equals("кг")&&mWorkWeight > 250 && mWorkWeight <= 300)||
                        (mMeasure.equals("lbs")&&mWorkWeight > 250*2.2 && mWorkWeight <= 300*2.2)){
                    mPercentsArray = getActivity().getResources().getIntArray(R.array.deadlift_percents_ot_250kg_do_300kg);
                    mSetsRepsArray = getActivity().getResources().getStringArray(R.array.deadlift_sets_reps_ot_250kg_do_300kg);
                }
                break;
        }
    }

    private void updateUI() {
        WeightLab weightLab = WeightLab.get(getActivity());
        List<Weight> weights = weightLab.getWeights();
        mAdapter = new WeightAdapter(weights);
        mWeightRecyclerView.setAdapter(mAdapter);
    }

    private class WeightHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mWeightTextView;
        private TextView mSetTextView;
        private TextView mNumSetTextView;
        private ImageView mImageViewDone;
        private CardView mCardViewItem;

        public WeightHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mWeightTextView = (TextView) itemView.findViewById(R.id.weight_text_view);
            mSetTextView = (TextView) itemView.findViewById(R.id.set_text_view);
            mNumSetTextView = (TextView) itemView.findViewById(R.id.num_set_text_view);
            mImageViewDone=(ImageView) itemView.findViewById(R.id.image_view_done);
            mCardViewItem=(CardView) itemView.findViewById(R.id.card_view_item);
        }

        @Override
        public void onClick(View v) {
            if (mImageViewDone.getVisibility()==View.INVISIBLE) {
                mImageViewDone.setVisibility(View.VISIBLE);
                mCardViewItem.setCardBackgroundColor(getResources().getColor(R.color.card_pressed));
            }else {
                mImageViewDone.setVisibility(View.INVISIBLE);
                mCardViewItem.setCardBackgroundColor(getResources().getColor(R.color.white));
            }

        }

    }

    private class WeightAdapter extends RecyclerView.Adapter<WeightHolder> {

        private List<Weight> mWeights;

        public WeightAdapter(List<Weight> weights) {
            mWeights = weights;
        }

        @Override
        public WeightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);
            return new WeightHolder(view);
        }

        @Override
        public void onBindViewHolder(WeightHolder holder, int position) {
            Weight weight = mWeights.get(position);
            holder.mWeightTextView.setText(String.valueOf(weight.getWeight()) + " " + mMeasure);
            holder.mNumSetTextView.setText(String.valueOf(weight.getNumSets()));
            holder.mSetTextView.setText(String.valueOf(weight.getSet()) + " подход");
        }

        @Override
        public int getItemCount() {
            return mWeights.size();
        }
    }
}
