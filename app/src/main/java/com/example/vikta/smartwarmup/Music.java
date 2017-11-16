package com.example.vikta.smartwarmup;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class Music extends MediaPlayer {

    private MediaPlayer song;
    private int secToStop;

    public Music(Context context) {
        song = MediaPlayer.create(context, R.raw.vicockiy);
        song.setAudioStreamType(AudioManager.STREAM_MUSIC);
        song.setLooping(true);
    }

    public MediaPlayer getSong() {
        return song;
    }

    public void stop() {
        song.pause();
        secToStop = song.getCurrentPosition();
    }

    public void play() {
        song.seekTo(secToStop);
        song.start();
    }

}
