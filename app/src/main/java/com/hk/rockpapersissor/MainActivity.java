package com.hk.rockpapersissor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hk.rockpapersissor.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int totalStage = 10;
    private int cStage = 0;
    private Random random;
    private int playerChoice = 0,computerChoice = 0;
    private int rock=1,paper=2, sissor =3;
    private int playerScore=0,computerScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        random = new Random();


        //guess player choice
        binding.rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = rock;
                play();
            }
        });

        binding.paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = paper;
                play();
            }
        });
        binding.sissorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerChoice = sissor;
                play();
            }
        });









    }

    public void play(){
        //guess computer choice
        cStage++;

        computerChoice = random.nextInt((3 - 1) + 1) + 1;
        if(playerChoice==rock&&computerChoice==rock){
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Draw!");
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==rock&&computerChoice==paper){
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==rock&&computerChoice==sissor){
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }


        else if(playerChoice==paper&&computerChoice==paper){
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Draw!");
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==paper&&computerChoice==rock){
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==paper&&computerChoice==sissor){
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }

        else if(playerChoice==sissor&&computerChoice==sissor){
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Draw!");

            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==sissor&&computerChoice==rock){
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }
        else if(playerChoice==sissor&&computerChoice==paper){
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore+"/"+cStage);
            binding.playerScoreTV.setText(playerScore+"/"+cStage);
        }


        if(cStage==totalStage){

            /*
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            if(playerScore>computerScore){
                builder.setTitle("Congratulation!");
                builder.setMessage("Yeh! You won the game.");
            }
            else if(computerScore>playerScore){
                builder.setTitle("OoPs!");
                builder.setMessage("You lost the game");
            }
            else{
                builder.setTitle("Oh!");
                builder.setMessage("Match has been Tied.");
            }
           builder.setPositiveButton("Retry",new DialogInterface.OnClickListener(){


               @Override
               public void onClick(DialogInterface dialog, int which) {


               }
           });

            builder.setPositiveButton("Exit",new DialogInterface.OnClickListener(){


                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });


            builder.create();
            builder.show();

*/


            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.result_dialoge, null);
            TextView status = view.findViewById(R.id.statusTV);
            TextView title = view.findViewById(R.id.dialogeTitle);
            TextView score = view.findViewById(R.id.scoreTV);
            TextView highScore = view.findViewById(R.id.bestScoreTV);

            ImageView retry = view.findViewById(R.id.restartBtn);
            ImageView exit = view.findViewById(R.id.exitBtn);

            if(playerScore>computerScore){
                title.setText("Victory!");
                status.setText("Congratulation!");
                score.setText(playerScore+"/"+cStage);
                highScore.setText("");
            }
            else if(computerScore>playerScore){
                title.setText("Game Over!");
                status.setText("You Lost!");
                score.setText(playerScore+"/"+cStage);
                highScore.setText("");
            }
            else{
                title.setText("OMG!");
                status.setText("Match Draw!");
                score.setText(playerScore+"/"+cStage);
                highScore.setText("");
            }


            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                }
            });

        }
    }
}
