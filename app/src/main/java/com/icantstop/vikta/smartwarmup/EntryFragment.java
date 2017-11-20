package com.icantstop.vikta.smartwarmup;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class EntryFragment extends Fragment {

    private ImageView mInfoImageView;
    private ImageView mSettingsImageView;
    private Button mButtonNextFragment;

    private static final String DIALOG_INFO = "DialogInfo";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        mButtonNextFragment = (Button) v.findViewById(R.id.btn_next);
        mButtonNextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Fragment fragment = new ExercisesFragment();
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, fragment).commit();
            }
        });

        mInfoImageView = (ImageView) v.findViewById(R.id.image_view_info);
        mInfoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        mSettingsImageView = (ImageView) v.findViewById(R.id.image_view_settings);
        mSettingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                SettingsFragment fragment = new SettingsFragment();
                fm.beginTransaction().addToBackStack(null).replace(R.id.fragmentContainer, fragment)
                        .commit();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showDialog() {
        DialogFragment dialog = new InfoDialogFragment();
        dialog.show(getFragmentManager(), DIALOG_INFO);
    }
}
    