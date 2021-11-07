package com.rana.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
 private TextView textviewquestion;
    private TextView textviewscore;
    private TextView textviewquestioncount;
    private TextView textviewcountdown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;
private ColorStateList textColorDefaultRb;
    private List<question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private question currentQuestion;
    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textviewquestion = findViewById(R.id.textView_question);
        textviewscore = findViewById(R.id.textView_score);
        textviewquestioncount = findViewById(R.id.textView_question_count);
        textviewcountdown = findViewById(R.id.textView_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radiobutton_1);
        rb2 = findViewById(R.id.radiobutton_2);
        rb3 = findViewById(R.id.radiobutton_3);
        buttonConfirmNext = findViewById(R.id.button);
        textColorDefaultRb = rb1.getTextColors();
        quizdphelper dbHelper = new quizdphelper(this);
        questionList = dbHelper.getAllquestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);
        showNextQuestion();


    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textviewquestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            questionCounter++;
            textviewquestioncount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }
    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerno()) {
            score++;
            textviewscore.setText("Score: " + score);
        }
        showSolution();
    }
    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerno()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textviewquestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textviewquestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textviewquestion.setText("Answer 3 is correct");
                break;
        }
        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }
    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);

        finish();
    }
        }

