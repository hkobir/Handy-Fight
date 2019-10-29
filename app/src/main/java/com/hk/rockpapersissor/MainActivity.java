package com.hk.rockpapersissor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hk.rockpapersissor.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences, sharedPreferences2;
    private boolean resumeState = false;
    private int totalStage = 10;
    private int cStage = 0;
    private Random random;
    private int playerChoice = 0, computerChoice = 0;
    private int rock = 1, paper = 2, sissor = 3;
    private int playerScore = 0, computerScore = 0, highScore = 0;
    private final String playerScoreKey = "plScore", computerScoreKey = "cScoreKey", playerChoiceKey = "pChoiceKey", computerChoiceKey = "cChoiceKey", currenStageKey = "cStageKey",resultKey = "resultKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //if has resume state : restore data before restarting the activity state
        sharedPreferences2 = getSharedPreferences("resumestate", MODE_PRIVATE);

        resumeState = sharedPreferences2.getBoolean("isResume", false);
        if (resumeState) {
            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.startup_dialoge, null);
            TextView resume = view.findViewById(R.id.resumeTV);
            TextView newGame = view.findViewById(R.id.newGameTV);
            resume.setOnClickListener(new View.OnClickListener() {              //start again from resume state
                @Override
                public void onClick(View v) {
                    playerScore = sharedPreferences2.getInt(playerScoreKey, 0);
                    computerScore = sharedPreferences2.getInt(computerScoreKey, 0);
                    playerChoice = sharedPreferences2.getInt(playerChoiceKey, 0);
                    computerChoice = sharedPreferences2.getInt(computerChoiceKey, 0);
                    cStage = sharedPreferences2.getInt(currenStageKey, 0);

                    //set into component

                   play();



                    dialog.dismiss();


                }
            });
            newGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharedPreferences.Editor edit = sharedPreferences2.edit();
                    edit.putBoolean("isResume", false);
                    edit.apply();
                    dialog.dismiss();      //no need to resume state just start from new state
                }
            });
            dialog.setView(view);
            dialog.show();
        }


        random = new Random();
        sharedPreferences = getSharedPreferences("highscore", MODE_PRIVATE);
        highScore = sharedPreferences.getInt("hScore", 0);


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



    public void play() {
        //guess computer choice
        binding.resultTV.setVisibility(View.VISIBLE);
        cStage++;

        computerChoice = random.nextInt((3 - 1) + 1) + 1;
        if (playerChoice == rock && computerChoice == rock) {
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Draw!");
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == rock && computerChoice == paper) {
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == rock && computerChoice == sissor) {
            binding.playerSelectIV.setImageResource(R.drawable.user_rock);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == paper && computerChoice == paper) {
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Draw!");
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == paper && computerChoice == rock) {
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == paper && computerChoice == sissor) {
            binding.playerSelectIV.setImageResource(R.drawable.user_paper);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == sissor && computerChoice == sissor) {
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_sissor);
            binding.resultTV.setText("Draw!");

            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == sissor && computerChoice == rock) {
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_rock);
            binding.resultTV.setText("Oops! You failed");

            computerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        } else if (playerChoice == sissor && computerChoice == paper) {
            binding.playerSelectIV.setImageResource(R.drawable.user_sissor);
            binding.computerSelectIV.setImageResource(R.drawable.computer_paper);
            binding.resultTV.setText("Well Done! You passed");

            playerScore++;
            binding.computerScoreTv.setText(computerScore + "/" + cStage);
            binding.playerScoreTV.setText(playerScore + "/" + cStage);
        }



        if (cStage == totalStage) {
            if (playerScore > highScore) {
                highScore = playerScore;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("hScore", highScore);
                editor.apply();
            }

            final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
            final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.result_dialoge, null);
            TextView status = view.findViewById(R.id.statusTV);
            TextView title = view.findViewById(R.id.dialogeTitle);
            TextView score = view.findViewById(R.id.scoreTV);
            TextView hScore = view.findViewById(R.id.bestScoreTV);

            ImageView retry = view.findViewById(R.id.restartBtn);
            ImageView exit = view.findViewById(R.id.exitBtn);

            if (playerScore > computerScore) {
                title.setText("Victory!");
                status.setText("Congratulation!");
                score.setText("Score: " + playerScore + "/" + cStage);
                hScore.setText("Best: " + highScore + "/" + totalStage);
            } else if (computerScore > playerScore) {
                title.setText("Game Over!");
                status.setText("You Lost!");
                score.setText("Score: " + playerScore + "/" + cStage);
                hScore.setText("Best: " + highScore + "/" + totalStage);
            } else {
                title.setText("OMG!");
                status.setText("Match Draw!");
                score.setText("Score: " + playerScore + "/" + cStage);
                hScore.setText("Best: " + highScore + "/" + totalStage);
            }


            retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cStage = 0;
                    playerScore = 0;
                    computerScore = 0;
                    binding.computerScoreTv.setText(computerScore + "/" + cStage);
                    binding.playerScoreTV.setText(playerScore + "/" + cStage);
                    binding.resultTV.setVisibility(View.GONE);

                    dialog.dismiss();
                }
            });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });

            dialog.setView(view);
            dialog.show();

        }
    }



    @Override
    public void onBackPressed() {
        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
        final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.exit_confirm_dialoge, null);
        TextView yes = view.findViewById(R.id.yesBtn);
        TextView no = view.findViewById(R.id.nosBtn);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sharedPreferences2.edit();

                if (cStage > 0) {
                    //when an unfinished stage are remaining

                    edit.putBoolean("isResume", true);
                    edit.putInt(playerScoreKey, playerScore);
                    edit.putInt(computerScoreKey, computerScore);

                    edit.putInt(playerChoiceKey, playerChoice);
                    edit.putInt(computerChoiceKey, computerChoice);

                    edit.putInt(currenStageKey, cStage);
                    edit.putString(resultKey,binding.resultTV.getText().toString());
                    edit.apply();
                }
                else{
                    edit.putBoolean("isResume", false);
                    edit.apply();
                }



                //terminate the apps
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);


            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setView(view);
        dialog.show();
    }




}
