package com.example.android.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    int locationOfCorrectAns;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;
    RelativeLayout gameRelativeLayout;

    Button playAgainButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    Random rand = new Random();
    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){


        score=0;
        numberOfQuestions=0;

        resultTextView.setText("");
        pointsTextView.setText("0/0");
        timerTextView.setText("30s");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setTextColor(-16777216);
                timerTextView.setText("0s");

            }
        }.start();



    }

    public void generateQuestion(){
        int a = rand.nextInt(21);
        int b =  rand.nextInt(21);

        int incorrectAnswer;

        sumTextView.setText(a + " + " +  b);

        locationOfCorrectAns = rand.nextInt(4);
        for(int i=0;i<4;i++){

            if(i==locationOfCorrectAns){

                answers.add(i, a+b);

            }else {

                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer==(a+b)){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(i, incorrectAnswer);

            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(playAgainButton);
    }

    public void chooseAnswer(View view){

        if(playAgainButton.getVisibility()==View.INVISIBLE) {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAns))) {

                score++;
                resultTextView.setText("Correct!");
                resultTextView.setTextColor(-16711936);
            } else {

                resultTextView.setText("Wrong!");
                resultTextView.setTextColor(-65536);
            }
            numberOfQuestions++;
            pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            generateQuestion();
            resultTextView.postDelayed(new Runnable() {
                public void run() {
                    resultTextView.setText("");
                }
            }, 500);
        }else {
            resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameRelativeLayout = (RelativeLayout)(findViewById(R.id.gameRelativeLayout));

        button = (Button) findViewById(R.id.startButton);


        sumTextView = (TextView) findViewById(R.id.sumTextview);
        resultTextView = (TextView) findViewById(R.id.resultTextview);
        pointsTextView = (TextView) findViewById(R.id.pointsTextview);
        timerTextView = (TextView) findViewById(R.id.timerTextview);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);




    }
}
