package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AddPlayers extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        getSupportActionBar().hide();
       // runmusic();

        final MediaPlayer mpwelcome = MediaPlayer.create(this,R.raw.welcome_new);
        final MediaPlayer mpempty = MediaPlayer.create(this,R.raw.draw);


        final EditText playerOne = findViewById(R.id.playerOneName);
        final EditText playerTwo = findViewById(R.id.playerTwoName);
        final Button startGameBtn = findViewById(R.id.startGameBtn);

        LinearLayout l1 = findViewById(R.id.shake1);
        LinearLayout l2 = findViewById(R.id.shake2);

        Animation anim1,anim2,anim3;
        anim1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaleup);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaledown);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                final String getPlayerOneName = playerOne.getText().toString();
                final String getPlayerTwoName = playerTwo.getText().toString();
                
                if(getPlayerOneName.isEmpty()||getPlayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayers.this,"Please Enter players names",Toast.LENGTH_SHORT).show();
                    l1.startAnimation(anim1);
                    l2.startAnimation(anim1);

                    mpempty.start();
                }
                else{
                    mpwelcome.start();
                    startGameBtn.startAnimation(anim2);
                    startGameBtn.startAnimation(anim3);
                    Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                    intent.putExtra("PlayerOne",getPlayerOneName);
                    intent.putExtra("PlayerTwo",getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });
    }
/*
    public void runmusic(){
        final MediaPlayer mpbgmusic = MediaPlayer.create(this,R.raw.background_music_two);
        mpbgmusic.start();
        mpbgmusic.setLooping(true);
    }
    public void stopmusic(){
        final MediaPlayer mpbgmusic = MediaPlayer.create(this,R.raw.background_music_two);
        mpbgmusic.stop();
        mpbgmusic.setLooping(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        runmusic();
    }

    @Override
    protected void onPause(){
        super.onPause();
        stopmusic();
    }
    @Override
    protected void onResume(){
        super.onResume();
        runmusic();
    }
   @Override
    protected void onStop(){
        super.onStop();
       stopmusic();
    }
    */
}
