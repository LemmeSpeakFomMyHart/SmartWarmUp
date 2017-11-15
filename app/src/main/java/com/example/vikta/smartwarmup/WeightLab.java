package com.example.vikta.smartwarmup;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class WeightLab {

    private static WeightLab sWeightLab;

    private List<Weight> mWeights;

    public static WeightLab get(Context context) {
        if (sWeightLab == null) {
            sWeightLab = new WeightLab(context);
        }
        return sWeightLab;
    }

    private WeightLab(Context context) {
        mWeights = new ArrayList<>();

    }

    public void addWeight(Weight w) {
        mWeights.add(w);
    }

    public void clearWeights() {
        mWeights.clear();
    }

    public List<Weight> getWeights() {
        return mWeights;
    }

    public Weight getWeight(int set) {
        for (Weight weight : mWeights) {
            if (weight.getSet() == set) {
                return weight;
            }
        }
        return null;
    }
}
