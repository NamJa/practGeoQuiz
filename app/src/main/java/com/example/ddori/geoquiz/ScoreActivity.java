package com.example.ddori.geoquiz;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private static final String LANDTAG = "landtag";
    private static final String KEY_SCORE = "score";

    private TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreView = (TextView) findViewById(R.id.score_textview);

        scoreView.setText(QuizActivity.correctCount*10 + "점!");
    }
}
