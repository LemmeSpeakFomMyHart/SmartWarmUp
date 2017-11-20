package com.icantstop.vikta.smartwarmup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

public class WarmUpActivity extends SingleFragmentActivity {

    private SharedPreferences mSharedPreferences;
    public static Music sSong;

    public static final String APP_PREFERENCES = "Settings";
    public static final String APP_SOUND_OFF = "music_off";

    @Override
    protected Fragment createFragment() {
        return new EntryFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = this.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        android.support.v7.preference.PreferenceManager.setDefaultValues(this,
                R.xml.settings, false);
        sSong = new Music(getApplicationContext());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Boolean mMusicOff = isSoundMuted();
        if (!mMusicOff) {
            sSong.stop();
        }
    }

    @NonNull
    private Boolean isSoundMuted() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return mSharedPreferences.getBoolean(APP_SOUND_OFF, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Boolean mMusicOff = isSoundMuted();
        if (!mMusicOff) {
            sSong.play();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sSong.release();
    }
}
