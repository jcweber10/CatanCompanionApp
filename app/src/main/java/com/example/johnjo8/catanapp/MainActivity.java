package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.gottheme);
        mediaPlayer.start();
        ((RadioButton) findViewById(R.id.twoP)).setChecked(true);
        GameModel model = new GameModel();
        GameModel.setNumPlayers(2);

    }
    public void onRadioButtonClicked(View view) {
        Log.v("Test","OnRadioButtonClicked is called");
        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        int numPlayers = Integer.parseInt(((RadioButton)findViewById(group.getCheckedRadioButtonId())).getText().toString());
        GameModel.setNumPlayers(numPlayers);
    }

    public void launchBase(View v){
        Log.v("TAG", "swapped new activity");
        mediaPlayer.stop();
        Intent i = new Intent(this,Base.class);
        startActivity(i);
    }

    public void launchCities(View v){
        Log.v("TAG", "swapped new activity");
        mediaPlayer.stop();
        Intent i = new Intent(this,Cities.class);
        startActivity(i);
    }



}
