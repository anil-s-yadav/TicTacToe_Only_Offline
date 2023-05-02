package com.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();
    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedboxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView TextViewplayerOneName, TextViewplayerTwoName;

    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TicTacToe");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         playerOneLayout = findViewById(R.id.playerOneLayout);
         playerTwoLayout = findViewById(R.id.playerTwoLayout);

         image1 = findViewById(R.id.image1);
         image2 = findViewById(R.id.image2);
         image3 = findViewById(R.id.image3);
         image4 = findViewById(R.id.image4);
         image5 = findViewById(R.id.image5);
         image6 = findViewById(R.id.image6);
         image7 = findViewById(R.id.image7);
         image8 = findViewById(R.id.image8);
         image9 = findViewById(R.id.image9);

         combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        TextViewplayerOneName = findViewById(R.id.tvplayerOneName);
        TextViewplayerTwoName = findViewById(R.id.tvplayerTwoName);

        final  String getPlayerOneName;
        final  String getPlayerTwoName;

        Intent intent = getIntent();
        getPlayerOneName = intent.getStringExtra("PlayerOne");
        getPlayerTwoName = intent.getStringExtra("PlayerTwo");

        TextViewplayerOneName.setText(getPlayerOneName);
        TextViewplayerTwoName.setText(getPlayerTwoName);


        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isBoxSelectable(0)){
                    performAction((ImageView)view,0);
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)){
                    performAction((ImageView)view,1);
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)){
                    performAction((ImageView)view,2);
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)){
                    performAction((ImageView)view,3);
                }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)){
                    performAction((ImageView)view,4);
                }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)){
                    performAction((ImageView)view,5);
                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)){
                    performAction((ImageView)view,6);
                }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)){
                    performAction((ImageView)view,7);
                }
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)){
                    performAction((ImageView)view,8);
                }
            }
        });

    }

    private void performAction(ImageView imageView, int selectedBoxPosition){

        final MediaPlayer mpwin = MediaPlayer.create(this,R.raw.win);
        final MediaPlayer mpdraw = MediaPlayer.create(this,R.raw.draw);
        final MediaPlayer mptap = MediaPlayer.create(this,R.raw.tap);
        final MediaPlayer mpdialog = MediaPlayer.create(this,R.raw.dialog);


        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.win_dialog_layout);
        dialog.setCancelable(false);

         TextView dialog_title = dialog.findViewById(R.id.dialogTitle);
         TextView dialog_massage = dialog.findViewById(R.id.dialogMassage);
        ImageView dialog_icon = dialog.findViewById(R.id.dialogIcon);
        Button startAgainBtn = dialog.findViewById(R.id.startAgainBtn);

        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               restartMatch();
                mpdialog.start();
                dialog.dismiss();
            }
        });

        boxPositions[selectedBoxPosition]=playerTurn;
        if(playerTurn == 1){
            imageView.setImageResource(R.drawable.cross);
            if(checkPlayerWin()){
                        dialog_icon.setImageResource(R.drawable.cross);
                        dialog_massage.setText(TextViewplayerOneName.getText().toString()+" has won the match");
                        dialog.show();
                        mpwin.start();
            }
            else if (totalSelectedboxes == 9){
                dialog_icon.setImageResource(R.drawable.draw);
                dialog_title.setText("Match is draw!");
                dialog_massage.setText("Restart The game");
                dialog.show();
                mpdraw.start();
            }
            else{
                changePlayerTurn(2);
                mptap.start();
                totalSelectedboxes++;
            }
        }
        else{
            imageView.setImageResource(R.drawable.zero);

            if (checkPlayerWin()){

                dialog_icon.setImageResource(R.drawable.zero);
                dialog_massage.setText(TextViewplayerOneName.getText().toString()+" has won the match");
                dialog.show();
                mpwin.start();
            }
            else if(selectedBoxPosition==9){
                dialog_icon.setImageResource(R.drawable.draw);
                dialog_title.setText("Match is draw!");
                dialog_massage.setText("Restart The game");
                dialog.show();
                mpdraw.start();
            }
            else{
                changePlayerTurn(1);
                mptap.start();
                totalSelectedboxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){

        playerTurn = currentPlayerTurn;

        if(playerTurn==1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin(){
        boolean response = false;
        for (int i=0;i<combinationList.size();i++){
            final int [] combination = combinationList.get(i);
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]]==playerTurn && boxPositions[combination[2]]== playerTurn){
                response=true;
            }
        }
       return response;
    }

    private boolean isBoxSelectable(int boxPositon){
        boolean response = false;

        if(boxPositions[boxPositon]==0){
            response = true;
        }
        return response;
    }

    public void restartMatch(){
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedboxes = 1;

        image1.setImageResource(R.drawable.transparent);
        image2.setImageResource(R.drawable.transparent);
        image3.setImageResource(R.drawable.transparent);
        image4.setImageResource(R.drawable.transparent);
        image5.setImageResource(R.drawable.transparent);
        image6.setImageResource(R.drawable.transparent);
        image7.setImageResource(R.drawable.transparent);
        image8.setImageResource(R.drawable.transparent);
        image9.setImageResource(R.drawable.transparent);

    }
}
