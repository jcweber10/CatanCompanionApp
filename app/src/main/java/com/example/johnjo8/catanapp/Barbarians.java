package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by johnjo8 on 6/8/2017.
 */

public class Barbarians extends Activity {
    TextView attack;
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barbarians_activity);
        sound = MediaPlayer.create(Barbarians.this,R.raw.bombsound);
        sound.start();
    }
    public void restore(View v){
        sound.stop();
        finish();
    }
}