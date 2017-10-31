package com.example.vikta.smartwarmup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListWeights extends Fragment {

    private RecyclerView mWeightRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_recycler_view,container,false);
        mWeightRecyclerView=(RecyclerView) v.findViewById(R.id.weight_recycler_view);
        mWeightRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    private class WeightHolder extends RecyclerView.ViewHolder {

        private TextView mWeightTextView;
        private TextView mSetTextView;
        private TextView mNumSetTextView;

        public WeightHolder(View itemView) {
            super(itemView);
            mWeightTextView=(TextView) itemView.findViewById(R.id.weight_text_view);
            mSetTextView=(TextView) itemView.findViewById(R.id.set_text_view);
            mNumSetTextView=(TextView) itemView.findViewById(R.id.num_set_text_view);
        }
    }

    private class WeightAdapter extends RecyclerView.Adapter<WeightHolder>{

        @Override
        public WeightHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.item_recycler_view,parent,false);
            return new WeightHolder(view);
        }

        @Override
        public void onBindViewHolder(WeightHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
