package com.icantstop.vikta.smartwarmup;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.preference.PreferenceFragmentCompat;


import static com.icantstop.vikta.smartwarmup.WarmUpActivity.sSong;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String APP_SOUND_OFF = "music_off";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(APP_SOUND_OFF)) {
            if (sharedPreferences.getBoolean(key, false)) {
                sSong.stop();
            } else {
                sSong.play();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
