package com.digitaldealsolution.quizly.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.digitaldealsolution.quizly.Database.QuestionDatabase;
import com.digitaldealsolution.quizly.Database.QuestionModel;
import com.digitaldealsolution.quizly.Activities.MainActivity;
import com.digitaldealsolution.quizly.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class QuestionFragment extends Fragment {
    private Boolean timer;
    private CountDownTimer countDownTimer;
    private List<QuestionModel> questionModels;
    private TextView question,counter,questionNo;
    private CardView counterCard;
    private int currentQuestion =0,score;
    private ArrayList<Button> optionBtn;
    private Button option1,option2,option3,option4;
    //Just Change this value whenever you want to change number of question
    int totalQuestion =5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_question, container, false);
        timer = requireActivity().getIntent().getExtras().getBoolean("timer");
        score =0;
        questionModels = new ArrayList<>();
        questionNo = root.findViewById(R.id.qestion_No);
        counterCard = root.findViewById(R.id.counter_card);
        counter = root.findViewById(R.id.counter);
        question = root.findViewById(R.id.question_txt);
        option1 = root.findViewById(R.id.option1);
        option2 = root.findViewById(R.id.option2);
        option3 = root.findViewById(R.id.option3);
        option4 = root.findViewById(R.id.option4);
        optionBtn = new ArrayList<>(Arrays.asList(option1,option2,option3,option4));
        countDownTimer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000<5){
                    counterCard.setCardBackgroundColor(Color.RED);
                }
                else{
                    counterCard.setCardBackgroundColor(Color.GREEN);
                }
                counter.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                if(currentQuestion<questionModels.size()-1)
                { currentQuestion++;
                    getNewQuestion();}
                else{
                    endQuiz();
                }
            }
        };





        option1.setOnClickListener(v -> checkAnswer(1));
        option2.setOnClickListener(v -> checkAnswer(2));
        option3.setOnClickListener(v -> checkAnswer(3));
        option4.setOnClickListener(v -> checkAnswer(4));
        questionModels = getData();
        getNewQuestion();
        return root;
    }


    private List<QuestionModel> getData() {
        QuestionDatabase database = QuestionDatabase.getDbInstance(requireActivity().getApplicationContext());

        return getRandomElement(database.questionDOA().getAllQuestion());
    }

    public List<QuestionModel> getRandomElement(List<QuestionModel> list)
    {
        Random rand = new Random();


        List<QuestionModel> newList = new ArrayList<>();
        for (int i = 0; i < totalQuestion; i++) {

            int randomIndex = rand.nextInt(list.size());

            newList.add(list.get(randomIndex));
            list.remove(randomIndex);


        }
        return newList;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void getNewQuestion() {
        questionNo.setText("Question " + (currentQuestion + 1) + "/" + questionModels.size());
        question.setText(questionModels.get(currentQuestion).getQuestion());
        option1.setText(questionModels.get(currentQuestion).getOption1());
        option2.setText(questionModels.get(currentQuestion).getOption2());
        option3.setText(questionModels.get(currentQuestion).getOption3());
        option4.setText(questionModels.get(currentQuestion).getOption4());

        option1.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        option2.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        option3.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        option4.setBackground(getResources().getDrawable(R.drawable.btn_bg));
        if(timer){
            setTimer();
        }
        else{
            counterCard.setVisibility(View.INVISIBLE);
            counter.setVisibility(View.INVISIBLE);
        }
    }


    public void setTimer() {
        countDownTimer.start();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void checkAnswer(int x) {
        int correctAns = questionModels.get(currentQuestion).getAnswer();
        if(x == correctAns){
            optionBtn.get(x-1).setBackgroundColor(Color.GREEN);
            score++;
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                if(currentQuestion<questionModels.size()-1)
                { currentQuestion++;
                    getNewQuestion();}

                else{
                    endQuiz();
                }
            },200);
        }
        else{
            optionBtn.get(x-1).setBackgroundColor(Color.RED);
            optionBtn.get(correctAns-1).setBackgroundColor(Color.GREEN);
            Handler handler = new Handler();
            handler.postDelayed(() -> {

                optionBtn.get(x-1).setBackground(getResources().getDrawable(R.drawable.btn_bg));
                if(currentQuestion<questionModels.size()-1)
                { currentQuestion++;
                    getNewQuestion();}
                else{
                    endQuiz();
                }
            },200);
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void endQuiz() {
        countDownTimer.cancel();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View bottomSheetView = LayoutInflater.from(requireActivity().getApplicationContext()).inflate(R.layout.score_dialog, requireActivity().findViewById(R.id.score_dialog));
        TextView score_txt = bottomSheetView.findViewById(R.id.score);
        TextView result = bottomSheetView.findViewById(R.id.result);
        Button play_again = bottomSheetView.findViewById(R.id.play_again);
        Button exit = bottomSheetView.findViewById(R.id.exit);
        LottieAnimationView pass =bottomSheetView.findViewById(R.id.pass);
        LottieAnimationView fail = bottomSheetView.findViewById(R.id.fail);
        play_again.setOnClickListener(v -> {
            questionModels = getData();
            currentQuestion = 0;
            score = 0;
            fail.setVisibility(View.GONE);
            pass.setVisibility(View.GONE);
            option1.setBackground(getResources().getDrawable(R.drawable.btn_bg));
            option2.setBackground(getResources().getDrawable(R.drawable.btn_bg));
            option3.setBackground(getResources().getDrawable(R.drawable.btn_bg));
            option4.setBackground(getResources().getDrawable(R.drawable.btn_bg));
            bottomSheetDialog.hide();
            getNewQuestion();
        });
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
        score_txt.setText("Your score is "+ score + " out of " + questionModels.size());
        if(((float)score/questionModels.size())*100>33.33){
            result.setText("PASS");
            pass.setVisibility(View.VISIBLE);
        }
        else{
            fail.setVisibility(View.VISIBLE);
            result.setText("FAIL");
            result.setTextColor(Color.RED);
        }
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.setDismissWithAnimation(true);
        bottomSheetDialog.show();

    }
}